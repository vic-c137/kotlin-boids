package com.vc137.boids.simulation.rules

import com.vc137.boids.data.*
import com.vc137.boids.simulation.Rule
import com.vc137.boids.simulation.Update

/**
 * Rule telling Boids to match velocity with their Knn
 */
class KnnAlignmentRule(override val priority: Int): Rule {

    /**
     * @see [Rule]@[apply]
     */
    override fun apply(target: Boid,
                       swarm: List<Boid>,
                       configuration: Configuration,
                       delta: List<Vector>): Update {
        val k = configuration.settings.getK()
        val alignment = configuration.settings.getAlignment()
        val knn = swarm.getKnn(k)

        var dv = Vector(0.0, 0.0, 0.0)

        knn.forEach {
            dv += it.velocity
        }

        dv /= alignment * knn.size

        return Update(target, listOf(delta[0], dv + delta[1]))
    }

}

