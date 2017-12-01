package com.vc137.boids.rules

import com.vc137.boids.*

class KnnCenterOfMassRule(override val priority: Int,
                          override val settings: Map<String, Any>) : Rule {

    override fun apply(target: Boid, swarm: List<Boid>, configuration: Configuration, delta: Pair<Point, Point>): Update {

        val k = settings.getK() ?: throw IllegalArgumentException("K cannot be null")
        if(k < 0) throw IllegalArgumentException("K cannot be negative")

        val newDelta = delta.copy()
        val newTarget = target.copy()

        // TODO: pull target toward center of mass of k nearest neighbors

        return Update(newTarget, newDelta)
    }
}