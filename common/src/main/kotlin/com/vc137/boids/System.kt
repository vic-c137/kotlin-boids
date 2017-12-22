package com.vc137.boids

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
expect fun overwriteSystemFile(filePath: String, data: String)