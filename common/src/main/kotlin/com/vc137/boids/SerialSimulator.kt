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

            swarm.sortedBy { boid.position.distance(it.position) }

            rules.forEach {
                val(target, d) = it.apply(boid, swarm, configuration, delta)
                boid = target
                delta = d
            }

            newSwarm.add(boid)
        }

        return newSwarm
    }
}