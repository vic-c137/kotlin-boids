package com.vc137.boids

import kotlinx.serialization.Serializable
import kotlin.math.sqrt

@Serializable
data class Point(val x: Double, val y: Double, val z: Double)

fun Point.distance(end: Point): Double {
    val dx = end.x - x
    val dy = end.y - y
    val dz = end.z - z
    return sqrt(dx*dx + dy*dy + dz*dz)
}