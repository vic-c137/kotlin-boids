package com.vc137.boids.rules

import com.vc137.boids.*
import kotlin.math.max

/**
 * Rule telling Boids to match velocity with their Knn
 */
class KnnAlignmentRule(override val priority: Int): Rule {

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

        dv *= alignment / knn.size

        return Update(target, listOf(delta[0], dv + delta[1]))
    }

}

val alignmentSetting = "com.vc137.boids.ALIGNMENT_SETTING"

private fun Map<String, Any>.getAlignment(): Double {
    return max(get(alignmentSetting) as? Double ?: 0.0, 1.0)
}
