package com.vc137.boids.implementation

import com.vc137.boids.data.Configuration
import com.vc137.boids.data.Vector
import com.vc137.boids.simulation.*

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

fun getBaselineRules(): List<Rule> {
    return listOf(
            SeparationRule(1),
            KnnCohesionRule(2),
            KnnAlignmentRule(3),
            UpdatePositionRule(4))
}