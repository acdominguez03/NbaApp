package com.example.nbaapp.domain.model

data class TeamDetailModel(
    val name: String,
    val logo: String,
    val gamesPlayedAtHome: Int,
    val gamesPlayedAway: Int,

    val gamesWinAtHome: Int,
    val gamesLoseAtHome: Int,
    val gamesWinAway: Int,
    val gamesLoseAway: Int,
    val totalWins: Int,
    val totalLoses: Int,

    val pointForAtHome: Int,
    val pointForAway: Int,
    val totalForPoint: Int,
    val averagePointForAtHome: String,
    val averagePointForAway: String,
    val totalAveragePointFor: String,

    val pointAgainstAtHome: Int,
    val pointAgainstAway: Int,
    val totalAgainstPoint: Int,
    val averagePointAgainstAtHome: String,
    val averagePointAgainstAway: String,
    val totalAveragePointAgainst: String,
)