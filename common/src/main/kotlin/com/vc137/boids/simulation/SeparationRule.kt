package com.vc137.boids.simulation

import com.vc137.boids.data.*

/**
 * Repel Boids from one another with a delta velocity inversely proportional
 * to the square of the distance between the target and its neighbors when
 * that distance drops below a defined cutoff point
 */
class SeparationRule(override val priority: Int) : Rule {

    override fun apply(target: Boid,
                       swarm: List<Boid>,
                       configuration: Configuration,
                       delta: List<Vector>): Update {

        val k = configuration.settings.getK()
        val separation = configuration.settings.getSeparation()
        val cutoff = configuration.settings.getSeparationCutoff()
        val knn = swarm.getKnn(k)

        var dv = Vector(0.0, 0.0, 0.0)

        knn.forEach {
            val dist = it.position.distance(target.position)
            if(dist < cutoff && it.id !== target.id) {
                var dvTemp = it.position - target.position
                dvTemp /= if(dist > 0)
                    -(dist * dist) / separation
                else
                    -1.0
                dv += dvTemp
            }
        }
        return Update(target, listOf(delta[0], dv + delta[1]))
    }
}