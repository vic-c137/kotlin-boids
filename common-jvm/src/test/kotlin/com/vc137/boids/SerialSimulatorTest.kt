package com.vc137.boids

import com.vc137.boids.rules.*
import com.vc137.boids.visualization.createDefault3DGnuplotScript
import java.io.File
import java.nio.file.Paths
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals

class SerialSimulatorTest {

    @Test
    fun testSerialSimulatorRunsForExpectedNumberOfIterationsWithExpectedSwarmSize() {

        // Setup
        val worldBounds = Pair(
                Vector(0.0, 0.0, 0.0),
                Vector(1000.0, 1000.0, 1000.0))
        val properties = mapOf(
                kSetting to 10,
                maxVelocitySetting to 10.0,
                separationSetting to 100.0,
                separationCutoffSetting to 20.0)
        val configuration = Configuration(100, 500, worldBounds, properties)
        val rules = listOf(
                //SeparationRule(1),
                UpdatePositionRule(2))
        val simulator = SerialSimulator::simulate
        val swarmSource = RandomSwarmSource::source
        val simulation = Simulation(configuration, rules, simulator, swarmSource)

        // Run
        val result = simulation.run()

        val pwd = Paths.get("").toAbsolutePath().toString()
        val outputFile = pwd + "/visualization_${Date().time}"
        val scriptFile = pwd + "/create_visualization.gp"
        val script = createDefault3DGnuplotScript(outputFile, configuration, result, { stringBuilder ->
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