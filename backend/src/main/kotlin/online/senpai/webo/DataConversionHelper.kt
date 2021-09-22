package online.senpai.webo

import java.lang.reflect.Type

inline fun <reified E : Enum<E>> decodeEnum(values: List<String>, type: Type): E = enumValues<E>()
    .first { it.name.toLowerCase() in values }

fun <E : Enum<E>> decodeEnum(values: List<String>, enumValues: () -> Array<E>): E = enumValues()
    .first { it.name.toLowerCase() in values }
