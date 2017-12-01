package com.vc137.boids

expect class Random() {
    fun uuid(): String
}

expect fun ClosedRange<Double>.random(): Double