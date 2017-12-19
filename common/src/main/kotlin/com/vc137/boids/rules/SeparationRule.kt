package com.vc137.boids.rules

import com.vc137.boids.*
import kotlin.math.max

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
            if(it.position.distance(target.position) < cutoff) {
                val dist = it.position.distance(target.position)
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

val separationSetting = "com.vc137.boids.rules.SEPARATION_SETTING"

private fun Map<String, Any>.getSeparation(): Double {
    return max(get(separationSetting) as? Double ?: 0.0, 0.0)
}

val separationCutoffSetting = "com.vc137.boids.rules.SEPARATION_CUTOFF_SETTING"

private fun Map<String, Any>.getSeparationCutoff(): Double {
    return max(get(separationCutoffSetting) as? Double ?: 0.0, 0.0)
}