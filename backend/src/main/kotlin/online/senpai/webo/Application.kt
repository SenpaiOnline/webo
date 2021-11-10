package online.senpai.webo

import io.micronaut.kotlin.runtime.startApplication
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info

@OpenAPIDefinition(
    info = Info(
        title = "Internal API",
        version = "0.1"
    )
)
object Application

fun main(args: Array<String>) {
    startApplication<Application>(*args) {
        packages("online.senpai.webo")
    }
}

