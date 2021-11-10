package io.kotest.provided

import io.kotest.core.config.AbstractProjectConfig
import io.micronaut.test.extensions.kotest.MicronautKotestExtension

object ProjectConfig : AbstractProjectConfig() {
    override fun listeners(): List<MicronautKotestExtension> = listOf(MicronautKotestExtension)
    override fun extensions(): List<MicronautKotestExtension> = listOf(MicronautKotestExtension)
}
