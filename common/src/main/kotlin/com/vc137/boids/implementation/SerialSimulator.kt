package com.vc137.boids.implementation

import com.vc137.boids.data.Boid
import com.vc137.boids.data.Configuration
import com.vc137.boids.data.Vector
import com.vc137.boids.data.distance
import com.vc137.boids.simulation.Rule

object SerialSimulator {
    fun simulate(configuration: Configuration,
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
}