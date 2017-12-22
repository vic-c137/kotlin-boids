package com.vc137.boids.integration

import com.vc137.boids.awaitSystemCliCommand
import com.vc137.boids.currentSystemUnixTimestamp
import com.vc137.boids.currentSystemWorkingDirectory
import com.vc137.boids.implementation.*
import com.vc137.boids.overwriteSystemFile
import com.vc137.boids.simulation.*
import com.vc137.boids.visualization.createDefaultGnuplotScript
import kotlin.test.Test
import kotlin.test.assertEquals

class SerialSimulatorTest {

    /**
     * Live test that demonstrates rendering 3d visualization of
     * the swarm simulation generated for the run - requires that
     * gnuplot be installed on the test environment
     *
     * To install gnuplot with Homebrew:
     *
     *     brew install gnuplot
     *
     * To install Homebrew:
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
        val pwd = currentSystemWorkingDirectory()
        val outputFile = pwd + "/output/visualization_${currentSystemUnixTimestamp()}"
        val scriptFile = pwd + "/output/create_visualization.gp"

        // Run
        val result = simulation.run()

        // Visualize
        val script = createDefaultGnuplotScript(outputFile, configuration, result, { stringBuilder ->
            stringBuilder.append("\n")
        })

        try {
            overwriteSystemFile(scriptFile, script)
            "gnuplot $scriptFile".awaitSystemCliCommand()
        } catch (ex: Exception) {

        }

        // Assert
        assertEquals(result.last().timestamp, configuration.iterations)
        assertEquals(result.size.toLong(), configuration.iterations + 1)
        assertEquals(result.first().swarm.size.toLong(), configuration.swarmSize)
    }
}