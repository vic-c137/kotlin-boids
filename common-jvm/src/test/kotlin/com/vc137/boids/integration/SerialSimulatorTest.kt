package com.vc137.boids.integration

import com.vc137.boids.implementation.*
import com.vc137.boids.simulation.*
import com.vc137.boids.visualization.createDefaultGnuplotScript
import java.io.File
import java.nio.file.Paths
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals

class SerialSimulatorTest {

    /**
     * Live test that demonstrates rendering 3d visualization of
     * the swarm simulation generated for the run - requires that
     * gnuplot be installed on the test environment
     *
     * To install gnuplot with brew:
     *
     *     brew install gnuplot
     *
     * To install brew:
     *
     *     /usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
     */
    @Test
    fun testSerialSimulatorRunsForExpectedDuration() {

        // Setup
        val configuration = getBaselineConfiguration()
        val rules = getBaselineRules()
        val simulator = ::serialSimulator
        val swarmSource = ::randomSwarmSource
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
        assertEquals(result.last().timestamp, configuration.iterations)
        assertEquals(result.size.toLong(), configuration.iterations + 1)
        assertEquals(result.first().swarm.size.toLong(), configuration.swarmSize)
    }
}