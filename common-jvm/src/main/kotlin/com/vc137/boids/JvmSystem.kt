package com.vc137.boids

import com.google.gson.Gson
import com.vc137.boids.models.Configuration
import java.io.File
import java.nio.file.Paths
import java.util.*

actual fun currentSystemUnixTimestamp(): Long = Date().time

actual fun currentSystemWorkingDirectory(): String = Paths.get("").toAbsolutePath().toString()

actual fun overwriteSystemFile(filePath: String, data: String): Any = File(filePath).printWriter().use { out -> return out.print(data) }

actual fun awaitSystemCliCommand(command: String): Int = Runtime.getRuntime().exec(command).waitFor()

actual fun parseConfigurationJson(json: String): Configuration? = Gson().fromJson(json, Configuration::class.java)
