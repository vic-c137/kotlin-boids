package com.vc137.boids.simulation

import com.vc137.boids.data.Vector
import com.vc137.boids.data.Boid
import com.vc137.boids.data.Configuration

interface Rule {
    val priority: Int
    fun apply(target: Boid,
              swarm: List<Boid>,
              configuration: Configuration,
              delta: List<Vector>): Update {
        return Update(target, delta)
    }
}

data class Update(val boid: Boid, val delta: List<Vector>)
