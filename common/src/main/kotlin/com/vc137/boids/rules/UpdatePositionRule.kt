package com.vc137.boids.rules

import com.vc137.boids.*
import kotlin.math.max

/**
 *  Rule to be executed last to update the position, velocity, etc. of the target
 */
class UpdatePositionRule(override val priority: Int) : Rule {

    override fun apply(target: Boid, swarm: List<Boid>, configuration: Configuration, delta: List<Vector>): Update {

        // Get target initial kinematic vectors
        var a = if(delta.size > 1) delta[1] else Vector(0.0, 0.0, 0.0)
        var v = target.velocity
        var p = target.position

        // Limit the acceleration, if necessary
        a = a.truncate(configuration.settings.getMaxAcceleration())

        // Update the velocity
        v += a

        // Limit the velocity, if necessary
        v = v.truncate(configuration.settings.getMaxVelocity())

        // Update the position
        p += v

        // Limit position by wrapping
        p = p.wrap(configuration.worldBounds.first, configuration.worldBounds.second)

        // Update target kinematic vectors
        return Update(Boid(target.id, p, v, a), listOf())
    }

}

val maxAccelerationSetting = "com.vc137.boids.rules.MAX_ACCELERATION_SETTING"

private fun Map<String, Any>.getMaxAcceleration(): Double {
    return max(get(maxAccelerationSetting) as? Double ?: 0.0, 0.0)
}

val maxVelocitySetting = "com.vc137.boids.rules.MAX_VELOCITY_SETTING"

private fun Map<String, Any>.getMaxVelocity(): Double {
    return max(get(maxVelocitySetting) as? Double ?: 0.0, 0.0)
}