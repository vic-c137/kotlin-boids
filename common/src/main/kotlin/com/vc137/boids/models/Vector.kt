package com.vc137.boids.models

import kotlin.math.abs
import kotlin.math.sqrt

/**
 * A 3d vector with x, y and z components
 * @property x the x coordinate [Double] value
 * @property y the y coordinate [Double] value
 * @property z the z coordinate [Double] value
 * @constructor Creates a new vector with specified coordinates
 */
data class Vector(val x: Double, val y: Double, val z: Double)

/**
 * Calculates the L2 (Euclidean) distance to another [Vector]
 * @receiver the vector to calculate the distance from
 * @param end the other vector, to calculate distance to
 * @return the distance between receiver and [end]
 */
fun Vector.distance(end: Vector): Double {
    val dx = end.x - x
    val dy = end.y - y
    val dz = end.z - z
    return sqrt(dx*dx + dy*dy + dz*dz)
}

/**
 * Calculates the length of a [Vector]
 * @receiver the vector to calculate the length of
 * @return the calculated length
 */
fun Vector.magnitude(): Double {
    return sqrt(x * x + y * y + z * z)
}

/**
 * Truncate the length of a [Vector] to a given length
 * @receiver the vector to truncate
 * @param max the maximum length allowed after truncation
 * @return a vector having the same direction as receiver,
 * but having a length no greater than [max]
 */
fun Vector.truncate(max: Double): Vector {
    return if(max > 0.0 && magnitude() > max) this / (magnitude() / max) else this
}

/**
 * Wrap a [Vector] so that it is within the given bounds
 * @receiver the vector to wrap
 * @param min a vector containing the minimum allowed coordinates
 * @param max a vector containing the maximum allowed coordinates
 * @return a vector with coordinates that have been wrapped over
 * the allowed coordinate ranges provided by [min] and [max]
 */
fun Vector.wrap(min: Vector, max: Vector): Vector {
    return Vector(x.wrap(min.x..max.x), y.wrap(min.y..max.y), z.wrap(min.z..max.z))
}

/**
 * Wrap a [Double] modulo some range so that it is within that range
 * @receiver the double to wrap
 * @param range the [ClosedRange] over which to wrap the receiver
 * @return the new value wrapped modulo over the range
 */
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

/**
 * Add a [Vector] to another
 * @receiver the vector to be added to
 * @param it the vector to add
 * @return the sum of the vectors
 */
operator fun Vector.plus(it: Vector): Vector {
    return Vector(x + it.x, y + it.y, z + it.z)
}

/**
 * Subtract a [Vector] from another
 * @receiver the vector to be subtracted from
 * @param it the vector to subtract
 * @return the difference of the vectors
 */
operator fun Vector.minus(it: Vector): Vector {
    return Vector(x - it.x, y - it.y, z - it.z)
}

/**
 * Divide a [Vector] component-wise by a double
 * @receiver the vector to divide
 * @param a the double to divide by
 * @return the vector result of the division
 */
operator fun Vector.div(a: Double): Vector {
    return Vector(x / a, y / a, z / a)
}

/**
 * Multiply a [Vector] component-wise by a double
 * @receiver the vector to multiply
 * @param a the double to multiply by
 * @return the vector result of the multiplication
 */
operator fun Vector.times(a: Double): Vector {
    return Vector(x * a, y * a, z * a)
}