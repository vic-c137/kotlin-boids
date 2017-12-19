package com.vc137.boids

import kotlinx.serialization.Serializable

@Serializable
data class Boid(val id: String,
                val derivatives: List<Vector>) {

    constructor(id: String,
                position: Vector,
                velocity: Vector,
                acceleration: Vector) :
            this(id, listOf(position, velocity, acceleration))

    val position: Vector
        get() { return derivatives[0] }
    val velocity: Vector
        get() { return derivatives[1] }
    val acceleration: Vector
        get() { return derivatives[2] }
}