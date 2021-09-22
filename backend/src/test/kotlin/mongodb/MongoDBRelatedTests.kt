package online.senpai.mongodb

/*@Disabled
@ExperimentalCoroutinesApi
@Testcontainers(disabledWithoutDocker = true)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class MongoDBRelatedTests {
    companion object {
        @JvmStatic
        @Container
        private val sharedMongoContainer = MongoDBContainer(MONGO_IMAGE_NAME)
        private fun randomUuid(): String = UUID.randomUUID().toString()
    }

    @Container
    private val mongoContainerFactory = MongoDBContainer(MONGO_IMAGE_NAME)

    @Test
    fun `getCollectionSafe ext should throw an exception when a collection doesn't exist`() {
        expectThat(sharedMongoContainer.isRunning).isTrue()
        val subject: Either<Throwable, CoroutineCollection<Document>> = runBlocking {
            KMongo
                .createClient(sharedMongoContainer.replicaSetUrl)
                .coroutine
                .getDatabase(randomUuid())
                .getCollectionIfExists("nonexistentCollection")
        }
        expectThat(subject)
            *//*.isLeft(IllegalArgumentException())*//*
            .isA<Either.Left<IllegalArgumentException>>() // TODO
    }

    @Test
    fun `getCollectionSafe ext should return Either_Right if a collection exists`() {
        expectThat(sharedMongoContainer.isRunning).isTrue()
        val subject: Either<Throwable, CoroutineCollection<Document>> = runBlocking {
            val collectionName: String = randomUuid()
            KMongo
                .createClient(sharedMongoContainer.replicaSetUrl)
                .coroutine
                .getDatabase(randomUuid())
                .run {
                    createCollection(collectionName)
                    getCollection<Document>(collectionName).insertOne(Document.parse("{some: 'data'}"))
                    getCollectionIfExists(collectionName)
                }
        }
        expectThat(subject).isRight()
    }

    @Test
    fun `getCollectionIfExists ext should return true only if a collection exists`() {
        expectThat(sharedMongoContainer.isRunning).isTrue()
        val subject: Boolean = runBlocking {
            val collectionName: String = randomUuid()
            KMongo
                .createClient(sharedMongoContainer.replicaSetUrl)
                .coroutine
                .getDatabase(randomUuid())
                .run {
                    createCollection(collectionName)
                    checkIfCollectionExists(collectionName)
                }
        }
        expectThat(subject).isTrue()
    }
}*/
