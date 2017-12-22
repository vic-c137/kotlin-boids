package com.vc137.boids

import java.io.File
import java.nio.file.Paths
import java.util.*

actual fun currentSystemUnixTimestamp(): Long = Date().time

actual fun currentSystemWorkingDirectory(): String = Paths.get("").toAbsolutePath().toString()

actual fun overwriteSystemFile(filePath: String, data: String) = File(filePath).printWriter().use { out -> out.print(data) }

actual fun awaitSystemCliCommand(command: String): Int = Runtime.getRuntime().exec(command).waitFor()
