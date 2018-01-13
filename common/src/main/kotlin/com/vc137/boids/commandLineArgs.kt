package com.vc137.boids

import com.vc137.boids.models.Configuration


fun Array<String>.getOutputFile(): String? {
    return when {
        isNotEmpty() -> this[0]
        else -> null
    }
}

fun Array<String>.getRenderScriptFile(): String? {
    return when {
        size > 1 -> {
            if(!this[1].endsWith(".gp"))
                this[1] + ".gp"
            else
                this[1]
        }
        else -> null
    }
}

fun Array<String>.getConfiguration(onError: (Throwable) -> Unit): Configuration? {
    return when {
        size > 2 -> {
            try {
                parseConfigurationJson(this[2])
            } catch (ex: Throwable) {
                println("Unable to parse configuration string: ${this[2]}")
                onError(ex)
                null
            }
        }
        else -> null
    }
}