package com.vc137.boids.implementation

import com.vc137.boids.models.Configuration
import com.vc137.boids.models.Vector
import com.vc137.boids.simulation.*
import com.vc137.boids.simulation.rules.*

/**
 * @return A useful baseline [Configuration] for simulations
 */
fun getBaselineConfiguration(): Configuration {
    return Configuration(
            500,
            50,
            Pair(Vector(0.0, 0.0, 0.0),
                    Vector(1000.0, 1000.0, 1000.0)),
            mapOf(
                    kSetting to 7,
                    maxVelocitySetting to 15.0,
                    maxAccelerationSetting to 2.00,
                    separationSetting to 32.0,
                    separationCutoffSetting to 50.0,
                    cohesionSetting to 1.0,
                    alignmentSetting to 1.0))
}

/**
 * @return A prioritized list of baseline [Rule]s for simulations
 */
fun getBaselineRules(): List<Rule> {
    return listOf(
            KnnSeparationRule(1),
            KnnCohesionRule(2),
            KnnAlignmentRule(3),
            UpdatePositionRule(4))
}