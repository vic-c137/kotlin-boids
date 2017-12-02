package com.vc137.boids

import kotlin.test.Test
import kotlin.test.assertEquals

class ExampleSimulatorTest {

    @Test fun testSerialSimulator() {

        val worldBounds = Pair(
                Vector(0.0, 0.0, 0.0),
                Vector(0.0, 0.0, 0.0))
        val properties = mapOf<String, Any>()
        val configuration = Configuration(100, 50, worldBounds, properties)
        val rules = listOf<Rule>()
        val simulator = SerialSimulator::simulate
        val swarmSource = RandomSwarmSource::source

        val simulation = Simulation(configuration, rules, simulator, swarmSource)
        val result = simulation.run()
        assertEquals(result.last().iterationNumber, configuration.iterations)
    }
}