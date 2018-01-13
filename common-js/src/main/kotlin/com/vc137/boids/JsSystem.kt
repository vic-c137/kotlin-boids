package com.vc137.boids

import com.vc137.boids.models.Configuration
import org.w3c.dom.Document
import kotlin.browser.window
import kotlin.js.Date


actual fun currentSystemUnixTimestamp(): Long = Date().getTime().toLong()

actual fun currentSystemWorkingDirectory(): String = window.location.pathname.split('/').first()

actual fun overwriteSystemFile(filePath: String, data: String): Any = { } // TODO: fixme

actual fun awaitSystemCliCommand(command: String): Int = if(Document().execCommand(command)) 1 else 0

actual fun parseConfigurationJson(json: String): Configuration? = JSON.parse(json)