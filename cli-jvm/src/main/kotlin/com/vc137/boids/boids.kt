package com.vc137.boids

import com.vc137.boids.implementation.getBaselineConfiguration
import com.vc137.boids.implementation.getBaselineRules
import com.vc137.boids.implementation.randomSwarmSource
import com.vc137.boids.implementation.serialSimulator
import com.vc137.boids.simulation.Simulation
import com.vc137.boids.visualization.LineAppender
import com.vc137.boids.visualization.createDefaultGnuplotScript

fun main(args: Array<String>) {
    System.out.println("Configuring simulation...")
    val configuration = args.getConfiguration({ t: Throwable -> t.printStackTrace() })
            ?: getBaselineConfiguration()
    val rules = getBaselineRules()
    val simulator = ::serialSimulator
    val swarmSource = ::randomSwarmSource
    val simulation = Simulation(configuration, rules, simulator, swarmSource)
    val pwd = currentSystemWorkingDirectory()

    val outputFile = args.getOutputFile() ?: pwd + "/visualization_${currentSystemUnixTimestamp()}"
    val scriptFile = args.getRenderScriptFile() ?: pwd + "/create_visualization.gp"

    System.out.println("Output file path: $outputFile.gif")
    System.out.println("Script file path: $scriptFile")

    try {
        // Run
        System.out.println("Running simulation...")
        val result = simulation.run()

        // Visualize
        System.out.println("Visualizing simulation...")
        val script = createDefaultGnuplotScript(outputFile, configuration, result, object : LineAppender {
            override fun appendln(builder: StringBuilder) {
                builder.append("\n")
            }
        })

        overwriteSystemFile(scriptFile, script)
        "gnuplot $scriptFile".awaitSystemCliCommand()
        System.out.println("Simulation completed successfully!")
    } catch (ex: Throwable) {
        System.out.println("Simulation failed with error:")
        ex.printStackTrace()
    }
}
