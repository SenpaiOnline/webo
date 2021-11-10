package online.senpai

import io.kotest.core.spec.style.StringSpec
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.kotest.annotation.MicronautTest

@MicronautTest
class WeboTest(private val application: EmbeddedApplication<*>) : StringSpec({
    "test the server is running" {
        assert(application.isRunning)
    }
})
