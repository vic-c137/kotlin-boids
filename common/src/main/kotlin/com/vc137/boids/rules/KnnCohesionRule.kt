package com.vc137.boids.rules

import com.vc137.boids.*
import kotlin.math.max

/**
 * Rule telling Boids to cohere to the center of mass of their Knn
 */
class KnnCohesionRule(override val priority: Int) : Rule {

    override fun apply(target: Boid,
                       swarm: List<Boid>,
                       configuration: Configuration,
                       delta: List<Vector>): Update {

        val k = configuration.settings.getK()
        val cohesion = configuration.settings.getCohesion()
        val knn = swarm.getKnn(k)

        // Find the center of mass of the Knn
        var center = Vector(0.0,0.0,0.0)
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

val cohesionSetting = "com.vc137.boids.rules.COHESION_SETTING"

private fun Map<String, Any>.getCohesion(): Double {
    return max(get(cohesionSetting) as? Double ?: 0.0, 0.0)
}




