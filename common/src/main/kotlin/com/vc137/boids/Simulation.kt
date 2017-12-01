package com.vc137.boids

class Simulation(private val configuration: Configuration,
                 private val rules: List<Rule>,
                 private val simulator: (Configuration, List<Rule>, List<Boid>)->List<Boid>,
                 swarmSource: (Configuration) -> List<Boid> ) {
    private var history: LinkedHashSet<State> = LinkedHashSet()

    init {
        history.add(State(0, swarmSource.invoke(configuration)))
    }

    fun run(): List<State> {
        var complete = false
        while (!complete) {

            val currentState = history.last()

            val newSwarm = simulator.invoke(configuration, rules, currentState.swarm)

            val newState = State(currentState.iterationNumber + 1, newSwarm)

            history.add(newState)

            complete = history.last().iterationNumber == configuration.iterations
        }
        return history.toList()
    }
}