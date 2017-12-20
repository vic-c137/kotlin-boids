package com.vc137.boids.visualization

import com.vc137.boids.State
import com.vc137.boids.plus

fun create3DAnimationScript(delay: Int,
                            outputFile: String,
                            xRange: ClosedRange<Int>,
                            yRange: ClosedRange<Int>,
                            zRange: ClosedRange<Int>,
                            data: List<State>,
                            appendln: (StringBuilder)->Unit): String {

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

    builder.append("splot 0")
    appendln(builder)

    data.forEach {
        it.swarm.forEachIndexed { index, boid ->
            val p = boid.position
            val v = p + boid.velocity
            builder.append("set arrow $index from ${p.x},${p.y},${p.z} to ${v.x},${v.y},${v.z}")
        }
    }

    return builder.toString()
}