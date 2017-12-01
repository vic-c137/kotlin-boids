package com.vc137.boids

import kotlin.test.Test
import kotlin.test.assertEquals

class SerialSimulatorTest {

    @Test
    fun testSerialSimulatorRunsForExpectedNumberOfIterationsWithExpectedSwarmSize() {

        // Setup
        val worldBounds = Pair(
                Point(0.0, 0.0, 0.0),
                Point(0.0, 0.0, 0.0))
        val properties = mapOf<String, Any>()
        val configuration = Configuration(100, 50, worldBounds, properties)
        val rules = listOf<Rule>()
        val simulator = SerialSimulator::simulate
        val swarmSource = RandomSwarmSource::source
        val simulation = Simulation(configuration, rules, simulator, swarmSource)

        // Run
        val result = simulation.run()

        // Assert
        assertEquals(result.last().iterationNumber, configuration.iterations)
        assertEquals(result.size.toLong(), configuration.iterations + 1)
        assertEquals(result.first().swarm.size.toLong(), configuration.swarmSize)
    }
}