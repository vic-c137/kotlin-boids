package com.vc137.boids

import com.vc137.boids.implementation.getBaselineConfiguration
import com.vc137.boids.implementation.getBaselineRules
import com.vc137.boids.implementation.serialSimulator
import com.vc137.boids.implementation.randomSwarmSource
import com.vc137.boids.simulation.Simulation
import com.vc137.boids.simulation.isComplete
import kotlin.test.Test
import kotlin.test.assertEquals

@Suppress("unused")
class ExampleSimulatorTest {

    /**
     * Example test showing how to run and test a simulation
     */
    @Test
    fun testSerialSimulator() {
        // Setup
        val configuration = getBaselineConfiguration()
        val rules = getBaselineRules()
        val simulator = ::serialSimulator
        val swarmSource = ::randomSwarmSource
        val simulation = Simulation(configuration, rules, simulator, swarmSource)

        // Run
        val result = simulation.run({
            return@run simulation.isComplete()
        })

        // Assert
        assertEquals(result.last().timestamp, configuration.iterations)
    }
}