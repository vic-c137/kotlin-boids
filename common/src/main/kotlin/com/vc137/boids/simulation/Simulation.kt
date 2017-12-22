package com.vc137.boids.simulation

import com.vc137.boids.Boid
import com.vc137.boids.Configuration
import com.vc137.boids.State

/**
 * A swarm simulation to be run with a given configuration
 * @property configuration the simulation [Configuration]
 * @property rules the [Rule]s to run for the simulation
 * @property simulator the simulator to run the simulation
 * @constructor Creates a new [Simulation] to run
 */
class Simulation(val configuration: Configuration,
                 private val rules: List<Rule>,
                 private val simulator: (Configuration, List<Rule>, List<Boid>)->List<Boid>,
                 swarmSource: (Configuration) -> List<Boid> ) {

    /**
     * @property history the [State] history of the [Simulation]
     */
    var history: LinkedHashSet<State> = LinkedHashSet()

    init {
        history.add(State(0, swarmSource.invoke(configuration)))
    }

    /**
     * Runs the simulation
     * @param completion a function to determine if the simulation
     * has run to completion based on custom logic
     * @return the [State] history list of the completed simulation
     */
    fun run(completion: (()->Boolean)? = null): List<State> {
        var complete = false
        while (!complete) {

            val currentState = history.last()

            val newSwarm = simulator.invoke(configuration, rules, currentState.swarm)

            val newState = State(currentState.timestamp + 1, newSwarm)

            history.add(newState)

            complete = completion?.invoke() ?: isComplete()
        }
        return history.toList()
    }
}

/**
 * Checks if a simulation is complete by verifying that
 * it has run for the specified number of iterations
 * @receiver the [Simulation] to check for completion
 * @return true if the simulation has completed
 */
fun Simulation.isComplete(): Boolean {
    return history.last().timestamp == configuration.iterations
}