package com.vc137.boids

import com.vc137.boids.models.Configuration
import kotlin.experimental.and

/**
 * Synchronously execute a system command and return the exit code
 * @param command the CLI command to run on the system
 * @return the exit code of the process running the command
 */
expect fun awaitSystemCliCommand(command: String): Int

/**
 * Run [awaitSystemCliCommand] synchronously using a [String] receiver
 * @receiver the CLI command to run
 * @return the exit code of the process running the command
 */
fun String.awaitSystemCliCommand(): Int = awaitSystemCliCommand(this)

/**
 * Get the current Unix timestamp from the system clock
 * @return the current system timestamp
 */
expect fun currentSystemUnixTimestamp(): Long

/**
 * Get the current process working directory from the system
 * @return the current process working directory
 */
expect fun currentSystemWorkingDirectory(): String

/**
 * Overwrite the file at the specified path with new models,
 * creating the file if it does not already exists
 * @param filePath the path of the file including the name
 * @param data the models to write overwrite the file with
 */
expect fun overwriteSystemFile(filePath: String, data: String): Any

/**
 * Parse a JSON string into a [Configuration]
 * @param json the JSON string to parse
 * @return the configuration parsed, or null if none
 */
expect fun parseConfigurationJson(json: String): Configuration?



private val hex = charArrayOf(
        '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f')

fun ByteArray.toHexString(): String {
    val chars = CharArray(size * 2)
    for(i in indices) {
        val v = (this[i] and 0xFF.toByte()).toInt()
        chars[i * 2] = hex[v ushr 4]
        chars[i * 2 + 1] = hex[v and 0x0F]
    }
    var result = ""
    chars.forEach { result += it }
    return result
}