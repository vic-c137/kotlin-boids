package com.vc137.boids

import kotlinx.serialization.Serializable

/**
 * A configuration used in swarm simulations
 * @property iterations the number of loops to run the simulation for
 * @property swarmSize the number of agents to use in the swarm
 * @property worldBounds the size of the 3d simulation space
 * @property settings a data store for custom configuration values
 * @constructor Creates a new configuration from provided data
 */
@Serializable
data class Configuration(val iterations: Long,
                         val swarmSize: Long,
                         val worldBounds: Pair<Vector, Vector>,
                         val settings: Map<String, Any>)
