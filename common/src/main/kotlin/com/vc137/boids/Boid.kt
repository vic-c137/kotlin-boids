package com.vc137.boids

import kotlinx.serialization.Serializable

@Serializable
data class Boid(val id: String,
                val position: Point,
                val velocity: Point,
                val acceleration: Point)