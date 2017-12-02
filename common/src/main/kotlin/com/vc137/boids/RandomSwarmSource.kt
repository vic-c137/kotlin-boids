package com.vc137.boids

object RandomSwarmSource {

    fun source(configuration: Configuration): List<Boid> {
        val boids = arrayListOf<Boid>()
        (1..configuration.swarmSize).forEach {

            val startX = configuration.worldBounds.first.x
            val endX = configuration.worldBounds.second.x
            val startY = configuration.worldBounds.first.y
            val endY = configuration.worldBounds.second.y
            val startZ = configuration.worldBounds.first.z
            val endZ = configuration.worldBounds.second.z

            val id = Random().uuid()
            val pos = Vector(
                    (startX..endX).random(),
                    (startY..endY).random(),
                    (startZ..endZ).random())
            val vel = Vector(0.0, 0.0, 0.0)
            val acc = Vector(0.0, 0.0, 0.0)

            boids.add(Boid(id, pos, vel, acc))
        }
        return boids
    }
}