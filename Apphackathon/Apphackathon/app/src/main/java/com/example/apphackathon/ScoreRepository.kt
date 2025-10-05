package com.example.apphackathon

data class PlayerScore(val name: String, val penalty: Int, val streak: Int, val fuelUsed: Float, val distanceKm: Float) {
    val efficiency: Float
        get() = if (distanceKm > 0) fuelUsed / distanceKm else 0f
}


object ScoreRepository {
    private val scores = mutableListOf<PlayerScore>()
    init {


        // Sample data for display

            scores.add(PlayerScore(name = "Sharan", penalty = -2, streak = 3, fuelUsed = 4.2f, distanceKm = 65f))
            scores.add(PlayerScore(name = "Aarav", penalty = -5, streak = 1, fuelUsed = 5.0f, distanceKm = 70f))
            scores.add(PlayerScore(name = "Liam", penalty = 0, streak = 5, fuelUsed = 3.8f, distanceKm = 60f))
            scores.add(PlayerScore(name = "Emma", penalty = -1, streak = 2, fuelUsed = 4.5f, distanceKm = 55f))
            scores.add(PlayerScore(name = "Noah", penalty = -7, streak = 0, fuelUsed = 6.0f, distanceKm = 80f))

    }

        fun addScore(name: String, penalty: Int, streak: Int,  fuelUsed: Float, distanceKm: Float) {
        scores.add(PlayerScore(name, penalty, streak, fuelUsed, distanceKm))
    }

    fun getAllScores(): List<PlayerScore> = scores
}