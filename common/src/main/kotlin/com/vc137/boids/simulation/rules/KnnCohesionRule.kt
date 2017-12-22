package com.vc137.boids.simulation.rules

import com.vc137.boids.models.*
import com.vc137.boids.simulation.Rule
import com.vc137.boids.simulation.Update

/**
 * Rule telling Boids to cohere to the center of mass of their Knn
 */
class KnnCohesionRule(override val priority: Int) : Rule {

    /**
     * @see [Rule]@[apply]
     */
    override fun apply(target: Boid,
                       swarm: Swarm,
                       configuration: Configuration,
                       delta: List<Vector>): Update {

        val k = configuration.settings.getK()
        val cohesion = configuration.settings.getCohesion()
        val knn = swarm.getKnn(k)

        // Find the center of mass of the Knn
        var center = Vector(0.0, 0.0, 0.0)
        knn.forEach {
            center += it.position
        }
        center /= k.toDouble()

        // Move dv in that direction from the target position by a percentage
        // of the distance equal to cohesion
        var dv = delta[1]
        dv += (center - target.position) * cohesion / 100.0

        return Update(target, listOf(delta[0], dv))
    }
}




