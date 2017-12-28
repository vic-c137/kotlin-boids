package com.vc137.boids

import kotlin.browser.window
import kotlin.js.Date


actual fun currentSystemUnixTimestamp(): Long = Date().getTime().toLong()

actual fun currentSystemWorkingDirectory(): String = window.location.pathname.split('/').first()

actual fun overwriteSystemFile(filePath: String, data: String): Any = { } // TODO: fixme

actual fun awaitSystemCliCommand(command: String): Int = 0 // TODO: fixme
