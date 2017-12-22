package com.vc137.boids

import com.vc137.boids.implementation.RandomSwarmSource
import com.vc137.boids.implementation.SerialSimulator
import com.vc137.boids.implementation.getBaselineConfiguration
import com.vc137.boids.implementation.getBaselineRules
import com.vc137.boids.simulation.*
import com.vc137.boids.visualization.createDefaultGnuplotScript
import java.io.File
import java.nio.file.Paths
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals

class SerialSimulatorTest {

    @Test
    fun testSerialSimulatorRunsForExpectedNumberOfIterationsWithExpectedSwarmSize() {

        // Setup
        val configuration = getBaselineConfiguration()
        val rules = getBaselineRules()
        val simulator = SerialSimulator::simulate
        val swarmSource = RandomSwarmSource::source
        val simulation = Simulation(configuration, rules, simulator, swarmSource)

        // Run
        val result = simulation.run()

        val pwd = Paths.get("").toAbsolutePath().toString()
        val outputFile = pwd + "/output/visualization_${Date().time}"
        val scriptFile = pwd + "/output/create_visualization.gp"
        val script = createDefaultGnuplotScript(outputFile, configuration, result, { stringBuilder ->
            stringBuilder.append("\n")
        })

        try {
            File(scriptFile).printWriter().use { out ->
                out.print(script)
            }
            Runtime.getRuntime().exec("gnuplot $scriptFile")
        } catch (ex: Exception) {

        }

        // Assert
        assertEquals(result.last().iterationNumber, configuration.iterations)
        assertEquals(result.size.toLong(), configuration.iterations + 1)
        assertEquals(result.first().swarm.size.toLong(), configuration.swarmSize)
    }
}