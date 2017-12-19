package com.vc137.boids

import kotlin.test.Test
import kotlin.test.assertEquals

class DoubleTest {

    @Test
    fun testWrapReturnsExpectedResultForBadRange() {
        val range = 1.0..-1.0
        val value = 0.0

        val result = value.wrap(range)

        assertEquals(0.0, result)
    }

    @Test
    fun testWrapReturnsExpectedResultForValueInsideValidRange() {
        val range = 0.0..100.0
        val value = 25.0

        val result = value.wrap(range)

        assertEquals(25.0, result)
    }

    @Test
    fun testWrapReturnsExpectedResultForNegativeValueBelowValidRange() {
        val range = -100.0..100.0
        val value = -125.0 - 200

        val result = value.wrap(range)

        assertEquals(75.0, result)
    }

    @Test
    fun testWrapReturnsExpectedResultForPositiveValueBelowValidRange() {
        val range = 100.0..200.0
        val value = 75.0

        val result = value.wrap(range)

        assertEquals(175.0, result)
    }

    @Test
    fun testWrapReturnsExpectedResultForPositiveValueAboveValidRange() {
        val range = -100.0..100.0
        val value = 125.0 + 200

        val result = value.wrap(range)

        assertEquals(-75.0, result)
    }

    @Test
    fun testWrapReturnsExpectedResultForNegativeValueAboveValidRange() {
        val range = -300.0..-100.0
        val value = -75.0

        val result = value.wrap(range)

        assertEquals(-275.0, result)
    }
}