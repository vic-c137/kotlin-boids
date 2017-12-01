package com.vc137.boids

interface Rule {
    val priority: Int
    val settings: Map<String, Any>
    fun apply(target: Boid,
              swarm: List<Boid>,
              configuration: Configuration,
              delta: Pair<Point, Point>): Update {
        return Update(target, delta)
    }
}

data class Update(val boid: Boid, val del: Pair<Point, Point>)
