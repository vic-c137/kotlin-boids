package com.vc137.boids.visualization

import com.vc137.boids.Boid
import com.vc137.boids.Configuration
import com.vc137.boids.State
import com.vc137.boids.plus

enum class Rank(val rank: String) {
    R2D("2D"),
    R3D("3D")
}

fun createDefault3DGnuplotScript(outputFile: String,
                                 configuration: Configuration,
                                 data: List<State>,
                                 appendln: (StringBuilder) -> Unit): String {
    val b = configuration.worldBounds
    return createGnuplotScript(
            Rank.R3D,
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