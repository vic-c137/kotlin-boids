package com.vc137.boids.visualization

import com.vc137.boids.models.Boid
import com.vc137.boids.models.Configuration
import com.vc137.boids.models.State
import com.vc137.boids.models.plus

/**
 * Enum signifying if a visualization is 2d or 3d
 * @param rank the rank of the visualization space
 */
@Suppress("unused", "MemberVisibilityCanPrivate")
enum class Rank(val rank: String) {
    R2D("2D"),
    R3D("3D")
}

interface LineAppender {
    fun appendln(builder: StringBuilder)
}

interface ShapeProvider {
    fun shape(boid: Boid): String
}

interface SizeProvider {
    fun size(boid: Boid): Double
}

interface ColorProvider {
    fun color(boid: Boid): String
}

/**
 * Create a gnuplot visualization script using a set of
 * default setting values for the output script
 * @param outputFile the name of the output file generated
 * @param configuration the simulation [Configuration]
 * @param data the simulation output models to visualize
 * @param appendln appends a line to the [StringBuilder]
 * @param rank the [Rank] of the visualization
 * @return the script to generate the output file
 */
fun createDefaultGnuplotScript(outputFile: String,
                               configuration: Configuration,
                               data: List<State>,
                               appendln: LineAppender,
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
            appendln)
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
 * @param data the simulation models to visualize
 * @param appender appends a line to the [StringBuilder]
 * @param shapeProvider a shape provider function for [Boid]s
 * @param sizeProvider a size provider function for [Boid]s
 * @param colorProvider a color provider function for [Boid]s
 * @return the script to generate the output file
 */
fun createGnuplotScript(rank: Rank,
                        delay: Int,
                        outputFile: String,
                        xRange: ClosedRange<Int>,
                        yRange: ClosedRange<Int>,
                        zRange: ClosedRange<Int>,
                        data: List<State>,
                        appender: LineAppender,
                        shapeProvider: ShapeProvider? = null,
                        sizeProvider: SizeProvider? = null,
                        colorProvider: ColorProvider? = null): String {

    val builder = StringBuilder()

    builder.append("reset")
    appender.appendln(builder)

    builder.append("set terminal gif animate delay $delay")
    appender.appendln(builder)

    builder.append("set output '$outputFile.gif'")
    appender.appendln(builder)

    builder.append("set xrange [${xRange.start}:${xRange.endInclusive}]")
    appender.appendln(builder)

    builder.append("set yrange [${yRange.start}:${yRange.endInclusive}]")
    appender.appendln(builder)

    builder.append("set zrange [${zRange.start}:${zRange.endInclusive}]")
    appender.appendln(builder)

    val plot = when(rank) {
        Rank.R2D -> "plot"
        Rank.R3D -> "splot"
    }

    builder.append("$plot 0")
    appender.appendln(builder)

    data.forEach {
        it.swarm.forEachIndexed { index, boid ->
            val p = boid.position
            val v = p + boid.velocity
            builder.append("set arrow ${index + 1} from ${p.x},${p.y},${p.z} to ${v.x},${v.y},${v.z}")
            appender.appendln(builder)

            val shape = shapeProvider?.shape(boid) ?: "circle"
            val size = sizeProvider?.size(boid) ?: 0.0025
            val color = colorProvider?.color(boid) ?: "navy"
            builder.append("set object ${index + 1} $shape at ${p.x},${p.y},${p.z} size scr $size fc rgb \"$color\"")
            appender.appendln(builder)
        }

        builder.append("replot")
        appender.appendln(builder)
    }

    builder.append("set output")

    return builder.toString()
}