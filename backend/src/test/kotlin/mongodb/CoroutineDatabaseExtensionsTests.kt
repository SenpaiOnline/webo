package online.senpai.mongodb

import arrow.core.Either
import io.kotest.assertions.arrow.either.shouldBeRight
import io.kotest.assertions.asClue
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.extensions.testcontainers.perSpec
import io.kotest.matchers.comparables.shouldBeEqualComparingTo
import io.kotest.matchers.longs.shouldBeExactly
import online.senpai.helpers.randomUuid
import online.senpai.webo.repository.checkIfCollectionExists
import online.senpai.webo.repository.getCollectionIfExists
import org.bson.Document
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.containers.Network
import kotlin.time.ExperimentalTime

@ExperimentalTime
class CoroutineDatabaseExtensionsTests : ExpectSpec({
    val network: Network = Network.newNetwork()
    val mongoPerSpecInstantiatedContainer: MongoDBContainer = MongoDBContainer(MONGO_IMAGE_NAME)
        .apply { withNetwork(network) }
        .also { listener(it.perSpec()) }
    /*val toxiproxy: ToxiproxyContainer = ToxiproxyContainer(TOXIPROXY_IMAGE_NAME)
        .apply {
            withNetwork(network)
            withNetworkAliases(TOXIPROXY_NETWORK_ALIAS)
        }
        .also { listener(it.perSpec()) }*/

    context("checkIfCollectionExists related tests") {
        val database: CoroutineDatabase = getDatabaseWithRandomName(mongoPerSpecInstantiatedContainer.replicaSetUrl)
        context("requested collection doesn't exist") {
            expect("checkIfCollectionExists to return Either.Right(false)") {
                database.checkIfCollectionExists("nonexistentCollection").shouldBeRight(false)
            }
        }
        context("requested collection exists") {
            database.createCollection("existentCollection")
            expect("checkIfCollectionExists to return Either.Right(true) because collection was created") {
                database.checkIfCollectionExists("existentCollection").shouldBeRight(true)
            }
        }
        context("requested collection exists but named in lower case") {
            database.createCollection("existentcollectionbutnamedinlowercase")
            expect("checkIfCollectionExists to return Either.Right(false) because it's case-sensitive") {
                database.checkIfCollectionExists("existentCollectionButNamedInLowerCase").shouldBeRight(false)
            }
        }
    }
    context("getCollectionIfExists related tests") {
        val database: CoroutineDatabase = getDatabaseWithRandomName(mongoPerSpecInstantiatedContainer.replicaSetUrl)
        context("requested collection doesn't exist") {
            expect("checkIfCollectionExists to return Either.Right(false)") {
                database
                    .getCollectionIfExists<Document>("nonexistentCollection")
                    .shouldBeRight(false)
            }
        }
        context("requested collection exists") {
            database.run {
                createCollection("existentCollection")
                getCollection<Document>("existentCollection")
                    .insertOne(Document.parse("{some: 'data'}"))
            }
            expect("getCollectionIfExists to return Either.Right with the given data") {
                database
                    .getCollectionIfExists<Document>("existentCollection")
                    .asClue { either: Either<Throwable, CoroutineCollection<Document>?> ->
                        either.shouldBeRight()
                        either.map { collection: CoroutineCollection<Document>? ->
                            collection?.countDocuments()?.shouldBeExactly(1)
                            collection?.findOne()?.getString("some")?.shouldBeEqualComparingTo("data")
                        }
                    }
            }
        }
    }
    /*context("mongoDB behind Toxiproxy") {
        val proxy: ToxiproxyContainer.ContainerProxy = toxiproxy.getProxy(mongoPerSpecInstantiatedContainer, 27017)
        val database: CoroutineDatabase =
            getDatabaseWithRandomName("mongodb://${proxy.containerIpAddress}:${proxy.proxyPort}/")
        context("test") {
            proxy.setConnectionCut(true)
            expect("kek").config(timeout = 30.seconds) {
                database.getCollectionIfExists<Document>("abc").shouldBeRight()
            }
        }
    }*/
})

private fun getDatabaseWithRandomName(connectionString: String): CoroutineDatabase =
    KMongo
        .createClient(connectionString)
        .coroutine
        .getDatabase(randomUuid())
