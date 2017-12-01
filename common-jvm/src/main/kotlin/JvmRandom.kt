package com.vc137.boids

import java.security.SecureRandom
import java.util.*

actual class Random {
    actual fun uuid(): String = UUID.randomUUID().toString()
}

actual fun ClosedRange<Double>.random() = SecureRandom().nextDouble() * (endInclusive - start) + start
