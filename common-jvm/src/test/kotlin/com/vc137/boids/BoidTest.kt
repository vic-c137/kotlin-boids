package com.vc137.boids

import kotlin.math.sqrt
import kotlin.test.Test
import kotlin.test.assertEquals

class BoidTest {

    @Test
    fun testDistanceReturnsExpectedResultCase1() {
        val p1 = Point(0.0, 0.0, 0.0)
        val p2 = Point(1.0, 1.0, 1.0)

        val dist = p1.distance(p2)

        assertEquals(sqrt(3.0), dist)
    }

    @Test
    fun testDistanceReturnsExpectedResultCase2() {
        val p1 = Point(0.0, 0.0, 0.0)
        val p2 = Point(1.0, 0.0, 0.0)

        val dist = p1.distance(p2)

        assertEquals(sqrt(1.0), dist)
    }

    @Test
    fun testDistanceReturnsExpectedResultCase3() {
        val p1 = Point(0.0, 0.0, 0.0)
        val p2 = Point(0.0, 0.0, 0.0)

        val dist = p1.distance(p2)

        assertEquals(0.0, dist)
    }
}