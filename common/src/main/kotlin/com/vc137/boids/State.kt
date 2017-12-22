package com.vc137.boids

import kotlinx.serialization.Serializable

/**
 * The state of a [Boid] swarm at a given moment
 * @property timestamp the moment the state was captured
 * @property swarm the swarm state at that moment
 */
@Serializable
data class State(val timestamp: Long,
                 val swarm: List<Boid>)