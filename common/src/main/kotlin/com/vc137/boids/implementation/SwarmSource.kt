package com.vc137.boids.implementation

import com.vc137.boids.*
import com.vc137.boids.models.*
import com.vc137.boids.simulation.rules.getMaxVelocity

/**
 * Generates a random swarm configuration where each
 * agent has random position and velocity
 * @param configuration the simulation [Configuration]
 * @return the initial swarm configuration
 */
fun randomSwarmSource(configuration: Configuration): Swarm {
    val boids = arrayListOf<Boid>()
    (1..configuration.swarmSize).forEach {

        val startX = configuration.worldBounds.first.x
        val endX = configuration.worldBounds.second.x
        val startY = configuration.worldBounds.first.y
        val endY = configuration.worldBounds.second.y
        val startZ = configuration.worldBounds.first.z
        val endZ = configuration.worldBounds.second.z

        val id = Random().uuid()

        val b = 1
        val pos = Vector(
                (startX + b..endX - b).random(),
                (startY + b..endY - b).random(),
                (startZ + b..endZ - b).random())

        val vmax = configuration.settings.getMaxVelocity()
        val vel = Vector(
                (-100.0..100.0).random(),
                (-100.0..100.0).random(),
                (-100.0..100.0).random())
                .truncate(vmax)

        val acc = Vector(0.0, 0.0, 0.0)

        boids.add(Boid(id, pos, vel, acc))
    }
    return boids
}
