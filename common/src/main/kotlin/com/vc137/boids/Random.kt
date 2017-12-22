package com.vc137.boids

/**
 * A random UUID string generator
 */
expect class Random() {

    /**
     * Generates a random UUID string
     * @return the UUID
     */
    fun uuid(): String
}

/**
 * Generates a random double in a closed range
 * @receiver the closed range of allowed values
 */
expect fun ClosedRange<Double>.random(): Double