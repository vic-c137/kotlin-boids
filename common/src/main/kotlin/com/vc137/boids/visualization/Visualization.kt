package com.vc137.boids.visualization

import com.vc137.boids.data.Boid
import com.vc137.boids.data.Configuration
import com.vc137.boids.data.State
import com.vc137.boids.data.plus

/**
 * Enum signifying if a visualization is 2d or 3d
 * @param rank the rank of the visualization space
 */
@Suppress("unused")
enum class Rank(val rank: String) {
    R2D("2D"),
    R3D("3D")
}

/**
 * Create a gnuplot visualization script using a set of
 * default setting values for the output script
 * @param outputFile the name of the output file generated
 * @param configuration the simulation [Configuration]
 * @param data the simulation output data to visualize
 * @param appendln appends a line to the [StringBuilder]
 * @param rank the [Rank] of the visualization
 * @return the script to generate the output file
 */
fun createDefaultGnuplotScript(outputFile: String,
                               configuration: Configuration,
                               data: List<State>,
                               appendln: (StringBuilder) -> Unit,
                               rank: Rank = Rank.R3D): String {
    val b = configuration.worldBounds
    return createGnuplotScript(
            rank,
            5,
            outputFile,
            b.first.x.toInt()..b.second.x.toInt(),
            b.first.y.toInt()..b.second.y.toInt(),
            b.first.z.toInt()..b.second.z.toInt(),
            data,
            appendln,
            object : (Boid) -> String? {
                override fun invoke(p1: Boid): String? {
                    return "circle"
                }
            },
            object : (Boid) -> Double? {
                override fun invoke(p1: Boid): Double? {
                    return 0.0025
                }
            },
            object : (Boid) -> String? {
                override fun invoke(p1: Boid): String? {
                    return "navy"
                }
            })
}

/**
 * Create a gnuplot visualization script by specifying all
 * values to be used in the script
 * @param rank the [Rank] of the visualization
 * @param delay the delay between frames, in milliseconds
 * @param outputFile the file name of the output visualization
 * @param xRange the [ClosedRange] for the x coordinates
 * @param yRange the [ClosedRange] for the y coordinates
 * @param zRange the [ClosedRange] for the z coordinates
 * @param data the simulation data to visualize
 * @param appendln appends a line to the [StringBuilder]
 * @param boidShape a shape provider function for [Boid]s
 * @param boidSize a size provider function for [Boid]s
 * @param boidColor a color provider function for [Boid]s
 * @return the script to generate the output file
 */
fun createGnuplotScript(rank: Rank,
                        delay: Int,
                        outputFile: String,
                        xRange: ClosedRange<Int>,
                        yRange: ClosedRange<Int>,
                        zRange: ClosedRange<Int>,
                        data: List<State>,
                        appendln: (StringBuilder)->Unit,
                        boidShape: (Boid)->String?,
                        boidSize: (Boid)->Double?,
                        boidColor: (Boid)->String?): String {

    val builder = StringBuilder()

    builder.append("reset")
    appendln(builder)

    builder.append("set terminal gif animate delay $delay")
    appendln(builder)

    builder.append("set output '$outputFile.gif'")
    appendln(builder)

    builder.append("set xrange [${xRange.start}:${xRange.endInclusive}]")
    appendln(builder)

    builder.append("set yrange [${yRange.start}:${yRange.endInclusive}]")
    appendln(builder)

    builder.append("set zrange [${zRange.start}:${zRange.endInclusive}]")
    appendln(builder)

    val plot = when(rank) {
        Rank.R2D -> "plot"
        Rank.R3D -> "splot"
    }

    builder.append("$plot 0")
    appendln(builder)

    data.forEach {
        it.swarm.forEachIndexed { index, boid ->
            val p = boid.position
            val v = p + boid.velocity
            builder.append("set arrow ${index + 1} from ${p.x},${p.y},${p.z} to ${v.x},${v.y},${v.z}")
            appendln(builder)

            val shape = boidShape(boid) ?: "circle"
            val size = boidSize(boid) ?: 0.005
            val color = boidColor(boid) ?: "navy"
            builder.append("set object ${index + 1} $shape at ${p.x},${p.y},${p.z} size scr $size fc rgb \"$color\"")
            appendln(builder)
        }

        builder.append("replot")
        appendln(builder)
    }

    builder.append("set output")

    return builder.toString()
}