package com.vc137.boids

import kotlinx.serialization.Serializable
import kotlin.math.abs
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

fun Vector.truncate(max: Double): Vector {
    return if(max > 0.0 && magnitude() > max) this / (magnitude() / max) else this
}

fun Vector.wrap(min: Vector, max: Vector): Vector {
    return Vector(x.wrap(min.x..max.x), y.wrap(min.y..max.y), z.wrap(min.z..max.z))
}

fun Double.wrap(range: ClosedRange<Double>): Double {
    return when {
        range.start >= range.endInclusive -> {
            // Invalid range, so don't wrap
            this
        }
        range.contains(this) -> {
            // The value is inside the range so no need to wrap
            this
        }
        this < range.start -> {
            // Value is below range so wrap from the upper end of the range
            range.endInclusive - (range.start - this ) % abs(range.endInclusive - range.start)
        }
        this > range.endInclusive -> {
            // Value is above range so wrap from the lower end of the range
            range.start + (this - range.endInclusive) % abs(range.endInclusive - range.start)
        }
        else -> this
    }
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