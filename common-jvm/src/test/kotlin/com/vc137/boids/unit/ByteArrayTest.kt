package com.vc137.boids.unit

import com.vc137.boids.toHexString
import kotlin.test.Test
import kotlin.test.assertEquals

class ByteArrayTest {

    @Test
    fun testToHexStringReturnsExpectedValue() {
        val bytes = byteArrayOf(2, 10, 11, 15)
        val expected = "020a0b0f"

        val result = bytes.toHexString()

        assertEquals(expected, result)
    }
}