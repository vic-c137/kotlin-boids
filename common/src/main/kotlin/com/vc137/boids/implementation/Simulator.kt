package com.vc137.boids.implementation

import com.vc137.boids.Boid
import com.vc137.boids.Configuration
import com.vc137.boids.Vector
import com.vc137.boids.distance
import com.vc137.boids.simulation.Rule

/**
 * A simulator function that runs the simulation
 * serially using a single threaded model
 * @param configuration the simulation [Configuration]
 * @param rules the set of simulation [Rule]s to run
 * @param swarm the swarm configuration before the simulation
 * @return the swarm configuration after the simulation
 */
fun serialSimulator(configuration: Configuration,
                    rules: List<Rule>,
                    swarm: List<Boid>): List<Boid> {
    val newSwarm = arrayListOf<Boid>()

    swarm.forEach {
        var boid = it.copy()

        var delta = listOf(
                Vector(0.0, 0.0, 0.0),
                boid.velocity)

        val sortedSwarm = swarm.sortedBy {
            boid.position.distance(it.position)
        }

        rules.sortedBy {
            it.priority
        }.forEach {
            val(target, d) = it.apply(boid, sortedSwarm, configuration, delta)
            boid = target
            delta = d
        }

        newSwarm.add(boid)
    }

    return newSwarm
}
