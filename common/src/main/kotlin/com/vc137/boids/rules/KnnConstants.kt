package com.vc137.boids.rules

import com.vc137.boids.Boid
import kotlin.math.max

val k: String = "com.vc137.boids.rules.K_NEAREST_NEIGHBORS_SETTING"

fun Map<String, Any>.getK(): Int {
    return max(get(k) as? Int ?: 1, 1)
}

fun List<Boid>.getKnn(k: Int): List<Boid> {
    return subList(1, k+1)
}