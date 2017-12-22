package com.vc137.boids

import java.security.SecureRandom
import java.util.*

/**
 * JVM implementation of [Random]
 */
@Suppress("MemberVisibilityCanPrivate")
actual class Random {

    /**
     * @see [Random]@[uuid]
     */
    actual fun uuid(): String = UUID.randomUUID().toString()
}

/**
 * @see [Random]@[random]
 */
actual fun ClosedRange<Double>.random() = SecureRandom().nextDouble() * (endInclusive - start) + start
