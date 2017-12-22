package com.vc137.boids

import kotlinx.serialization.Serializable

// test

/**
 * A swarm agent used in swarm intelligence simulations
 * @property id the unique ID of this agent
 * @property derivatives the kinematic derivatives of the agent
 * @property properties custom properties of the agent
 * @constructor Creates a new swarm agent
 */
@Serializable
data class Boid(val id: String,
                val derivatives: List<Vector>,
                val properties: Map<String, Any> = mapOf()) {

    /**
     * Constructor for explicit position, velocity, acceleration
     * @param position the spatial displacement of the agent
     * @param velocity the velocity of the agent
     * @param acceleration the acceleration of the agent
     * @param properties optional custom properties
     */
    constructor(id: String,
                position: Vector,
                velocity: Vector,
                acceleration: Vector,
                properties: Map<String, Any> = mapOf()) :
            this(
                    id,
                    listOf(position, velocity, acceleration),
                    properties
            )

    /**
     * @return The position of the agent
     */
    val position: Vector
        get() { return derivatives[0] }

    /**
     * @return The velocity of the agent
     */
    val velocity: Vector
        get() { return derivatives[1] }

    /**
     * @return The acceleration of the agent
     */
    val acceleration: Vector
        get() { return derivatives[2] }
}