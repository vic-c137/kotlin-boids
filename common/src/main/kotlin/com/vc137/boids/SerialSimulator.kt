package com.vc137.boids

object SerialSimulator {
    fun simulate(configuration: Configuration,
                 rules: List<Rule>,
                 swarm: List<Boid>): List<Boid> {
        val newSwarm = arrayListOf<Boid>()

        swarm.forEach {
            var boid = it.copy()

            var delta = Pair(
                    Point(0.0, 0.0, 0.0),
                    Point(0.0, 0.0, 0.0))

            val sortedSwarm = swarm.sortedBy { boid.position.distance(it.position) }

            rules.sortedBy { it.priority }.forEach {
                val(target, d) = it.apply(boid, sortedSwarm, configuration, delta)
                boid = target
                delta = d
            }

            newSwarm.add(boid)
        }

        return newSwarm
    }
}