package com.vc137.boids

import kotlinx.serialization.Serializable

@Serializable
data class State(val iterationNumber: Long,
                 val swarm: List<Boid>)