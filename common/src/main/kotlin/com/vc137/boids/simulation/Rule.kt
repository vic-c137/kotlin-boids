package com.vc137.boids.simulation

import com.vc137.boids.data.Vector
import com.vc137.boids.data.Boid
import com.vc137.boids.data.Configuration

/**
 * Interface for a prioritized rule object to
 * be run on a swarm during simulations
 */
interface Rule {

    /**
     * @property priority the order in which the
     * rule should be run, smallest priority first
     */
    val priority: Int

    /**
     * Apply the rule to the target [Boid]
     * @param target the [Boid] to apply the rule to
     * @param swarm the swarm configuration before applying
     * @param configuration the simulation [Configuration]
     * @param delta kinematic derivative deltas for the
     * [target] in the current rule chain
     * @return the [Update] to be applied to [target] when
     * the chain of rules has run to completion
     */
    fun apply(target: Boid,
              swarm: List<Boid>,
              configuration: Configuration,
              delta: List<Vector>): Update {
        return Update(target, delta)
    }
}

/**
 * An update to be applied to a target [Boid]
 * @property boid the target [Boid] to be updated
 * @property delta the kinematic derivative delta to
 * be applied to the [boid] during the update
 */
data class Update(val boid: Boid, val delta: List<Vector>)
