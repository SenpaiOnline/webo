package online.senpai.webo.repository.ext

import arrow.core.NonEmptyList
import arrow.core.Option
import org.litote.kmongo.coroutine.CoroutinePublisher

suspend fun <T : Any> CoroutinePublisher<T>.toSafeNonEmptyList(): Option<NonEmptyList<T>> =
    NonEmptyList.fromList(this.toList()) /* TODO rewrite when Arrow devs change the API, because Option is marked as
                                                 deprecated starting from 0.11 */
