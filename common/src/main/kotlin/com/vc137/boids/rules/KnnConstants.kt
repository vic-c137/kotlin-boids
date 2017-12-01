package com.vc137.boids.rules

val K: String = "K_NEAREST_NEIGHBORS_SETTING"

fun Map<String, Any>.getK(): Int? {
    return get(K) as? Int
}