package com.vc137.boids

import com.vc137.boids.implementation.getBaselineConfiguration
import com.vc137.boids.implementation.getBaselineRules
import com.vc137.boids.implementation.serialSimulator
import com.vc137.boids.implementation.randomSwarmSource
import com.vc137.boids.simulation.Simulation
import com.vc137.boids.simulation.isComplete
import com.vc137.boids.visualization.LineAppender
import com.vc137.boids.visualization.createDefaultGnuplotScript
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
        val pwd = currentSystemWorkingDirectory()
        val outputFile = pwd + "/output/visualization_${currentSystemUnixTimestamp()}"
        val scriptFile = pwd + "/output/create_visualization.gp"

        // Run
        val result = simulation.run({
            return@run simulation.isComplete()
        })

        val script = createDefaultGnuplotScript(outputFile, configuration, result, object : LineAppender {
            override fun appendln(builder: StringBuilder) {
                builder.append("\n")
            }
        })

        try {
            overwriteSystemFile(scriptFile, script)
            "gnuplot $scriptFile".awaitSystemCliCommand()
        } catch (ex: Exception) {

        }

        // Assert
        assertEquals(result.last().timestamp, configuration.iterations)
    }
}