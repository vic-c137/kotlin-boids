package com.vc137.boids

import kotlin.js.Math

/**
 * JS implementation of [Random]
 */
@Suppress("MemberVisibilityCanPrivate")
actual class Random {

    /**
     * @see [Random]@[uuid]
     */
    actual fun uuid(): String {
        return "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(Regex("[xy]"), {
            result ->
            val r = (Math.random() * 16).toInt() or 0
            val v = if(result.value == "x") r else r and 0x3 or 0x8
            byteArrayOf(v.toByte()).toHexString()
        })
    }
}


/**
 * @see [Random]@[random]
 * NOTE: Math.random() **may not** provide secure PRNG
 * in all browser implementations
 */
actual fun ClosedRange<Double>.random() = Math.random() * (endInclusive - start) + start
