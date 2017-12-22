package com.vc137.boids.simulation.rules

import com.vc137.boids.models.Boid
import kotlin.math.max

/**
 * @property settingPrefix a unique package prefix for setting names
 */
val settingPrefix = "com.vc137.boids.simulation."

/**
 * @property kSetting setting for the constant [Int]
 * value K for KNN algorithm implementations
 */
val kSetting: String = "${settingPrefix}K_NEAREST_NEIGHBORS_SETTING"

/**
 * Get [kSetting] from a map of named values
 * @receiver the [Map] to search for settings
 * @return the [Int] value for [kSetting], minimum 1
 */
fun Map<String, Any>.getK(): Int {
    return max(get(kSetting) as? Int ?: 1, 1)
}

/**
 * Get the KNN for a *pre-sorted* list of [Boid]s
 * @receiver the *pre-sorted* list to search
 * @return the first K members of receiver starting
 * at index 1 (i.e. not including the first)
 */
fun List<Boid>.getKnn(k: Int): List<Boid> {
    return subList(1, k+1)
}

/**
 * @property maxAccelerationSetting setting for the
 * maximum acceleration [Double] value
 */
val maxAccelerationSetting = "${settingPrefix}MAX_ACCELERATION_SETTING"

/**
 * Get [maxAccelerationSetting] from a map of named values
 * @receiver the [Map] to search for settings
 * @return the [Double] value for [maxAccelerationSetting], minimum 0.0
 */
fun Map<String, Any>.getMaxAcceleration(): Double {
    return max(get(maxAccelerationSetting) as? Double ?: 0.0, 0.0)
}

/**
 * @property maxVelocitySetting setting for the
 * maximum velocity [Double] value
 */
val maxVelocitySetting = "${settingPrefix}MAX_VELOCITY_SETTING"

/**
 * Get [maxVelocitySetting] from a map of named values
 * @receiver the [Map] to search for settings
 * @return the [Double] value for [maxVelocitySetting], minimum 0.0
 */
fun Map<String, Any>.getMaxVelocity(): Double {
    return max(get(maxVelocitySetting) as? Double ?: 0.0, 0.0)
}

/**
 * @property alignmentSetting setting for the
 * alignment rule scale factor [Double] value
 */
val alignmentSetting = "${settingPrefix}ALIGNMENT_SETTING"

/**
 * Get [alignmentSetting] from a map of named values
 * @receiver the [Map] to search for settings
 * @return the [Double] value for [alignmentSetting], minimum 1.0
 */
fun Map<String, Any>.getAlignment(): Double {
    return max(get(alignmentSetting) as? Double ?: 1.0, 1.0)
}

/**
 * @property cohesionSetting setting for the
 * cohesion rule scale factor [Double] value
 */
val cohesionSetting = "${settingPrefix}COHESION_SETTING"

/**
 * Get [cohesionSetting] from a map of named values
 * @receiver the [Map] to search for settings
 * @return the [Double] value for [cohesionSetting], minimum 0.0
 */
fun Map<String, Any>.getCohesion(): Double {
    return max(get(cohesionSetting) as? Double ?: 0.0, 0.0)
}

/**
 * @property separationSetting setting for the
 * separation rule scale factor [Double] value
 */
val separationSetting = "${settingPrefix}SEPARATION_SETTING"

/**
 * Get [separationSetting] from a map of named values
 * @receiver the [Map] to search for settings
 * @return the [Double] value for [separationSetting], minimum 0.0
 */
fun Map<String, Any>.getSeparation(): Double {
    return max(get(separationSetting) as? Double ?: 0.0, 0.0)
}

/**
 * @property separationCutoffSetting setting for the
 * separation rule effect cutoff distance [Double] value
 */
val separationCutoffSetting = "${settingPrefix}SEPARATION_CUTOFF_SETTING"

/**
 * Get [separationCutoffSetting] from a map of named values
 * @receiver the [Map] to search for settings
 * @return the [Double] value for [separationCutoffSetting], minimum 0.0
 */
fun Map<String, Any>.getSeparationCutoff(): Double {
    return max(get(separationCutoffSetting) as? Double ?: 0.0, 0.0)
}