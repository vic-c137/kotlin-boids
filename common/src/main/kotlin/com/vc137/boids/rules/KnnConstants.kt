package com.vc137.boids.rules

import com.vc137.boids.Boid
import kotlin.math.max

val kSetting: String = "com.vc137.boids.rules.K_NEAREST_NEIGHBORS_SETTING"

fun Map<String, Any>.getK(): Int {
    return max(get(kSetting) as? Int ?: 1, 1)
}

fun List<Boid>.getKnn(k: Int): List<Boid> {
    return subList(1, k+1)
}

val maxAccelerationSetting = "com.vc137.boids.rules.MAX_ACCELERATION_SETTING"

fun Map<String, Any>.getMaxAcceleration(): Double {
    return max(get(maxAccelerationSetting) as? Double ?: 0.0, 0.0)
}

val maxVelocitySetting = "com.vc137.boids.rules.MAX_VELOCITY_SETTING"

fun Map<String, Any>.getMaxVelocity(): Double {
    return max(get(maxVelocitySetting) as? Double ?: 0.0, 0.0)
}

val alignmentSetting = "com.vc137.boids.ALIGNMENT_SETTING"

fun Map<String, Any>.getAlignment(): Double {
    return max(get(alignmentSetting) as? Double ?: 0.0, 1.0)
}

val cohesionSetting = "com.vc137.boids.rules.COHESION_SETTING"

fun Map<String, Any>.getCohesion(): Double {
    return max(get(cohesionSetting) as? Double ?: 0.0, 0.0)
}

val separationSetting = "com.vc137.boids.rules.SEPARATION_SETTING"

fun Map<String, Any>.getSeparation(): Double {
    return max(get(separationSetting) as? Double ?: 0.0, 0.0)
}

val separationCutoffSetting = "com.vc137.boids.rules.SEPARATION_CUTOFF_SETTING"

fun Map<String, Any>.getSeparationCutoff(): Double {
    return max(get(separationCutoffSetting) as? Double ?: 0.0, 0.0)
}