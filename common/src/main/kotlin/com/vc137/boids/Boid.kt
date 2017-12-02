package com.vc137.boids

import kotlinx.serialization.Serializable

@Serializable
data class Boid(val id: String,
                val position: Vector,
                val velocity: Vector,
                val acceleration: Vector)