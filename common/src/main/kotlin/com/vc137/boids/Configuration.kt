package com.vc137.boids

import kotlinx.serialization.Serializable

@Serializable
data class Configuration(val iterations: Long,
                         val swarmSize: Long,
                         val worldBounds: Pair<Point, Point>,
                         val properties: Map<String, Any>)
