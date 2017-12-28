package com.vc137.boids.models

/**
 * The state of a [Boid] swarm at a given moment
 * @property timestamp the moment the state was captured
 * @property swarm the swarm state at that moment
 */
data class State(val timestamp: Long,
                 val swarm: Swarm)