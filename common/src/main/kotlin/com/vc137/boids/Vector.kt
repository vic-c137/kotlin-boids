package com.vc137.boids

import kotlinx.serialization.Serializable
import kotlin.math.sqrt

@Serializable
data class Vector(val x: Double, val y: Double, val z: Double)

fun Vector.distance(end: Vector): Double {
    val dx = end.x - x
    val dy = end.y - y
    val dz = end.z - z
    return sqrt(dx*dx + dy*dy + dz*dz)
}

fun Vector.magnitude(): Double {
    return sqrt(x * x + y * y + z * z)
}

operator fun Vector.plus(it: Vector): Vector {
    return Vector(x + it.x, y + it.y, z + it.z)
}

operator fun Vector.minus(it: Vector): Vector {
    return Vector(x - it.x, y - it.y, z - it.z)
}

operator fun Vector.div(a: Double): Vector {
    return Vector(x / a, y / a, z / a)
}

operator fun Vector.times(a: Double): Vector {
    return Vector(x * a, y * a, z * a)
}