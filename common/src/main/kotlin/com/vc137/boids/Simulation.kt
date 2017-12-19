package com.vc137.boids

class Simulation(val configuration: Configuration,
                 val rules: List<Rule>,
                 private val simulator: (Configuration, List<Rule>, List<Boid>)->List<Boid>,
                 swarmSource: (Configuration) -> List<Boid> ) {
    var history: LinkedHashSet<State> = LinkedHashSet()

    init {
        history.add(State(0, swarmSource.invoke(configuration)))
    }

    fun run(completion: (()->Boolean)? = null): List<State> {
        var complete = false
        while (!complete) {

            val currentState = history.last()

            val newSwarm = simulator.invoke(configuration, rules, currentState.swarm)

            val newState = State(currentState.iterationNumber + 1, newSwarm)

            history.add(newState)

            complete = completion?.invoke() ?: isComplete()
        }
        return history.toList()
    }
}

fun Simulation.isComplete(): Boolean {
    return history.last().iterationNumber == configuration.iterations
}