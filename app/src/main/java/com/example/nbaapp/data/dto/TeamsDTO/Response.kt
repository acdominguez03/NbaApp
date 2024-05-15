package com.example.nbaapp.data.dto.TeamsDTO

import com.example.nbaapp.domain.model.TeamStandingModel

data class Response(
    val country: Country,
    val description: String,
    val form: Any,
    val games: Games,
    val group: Group,
    val league: League,
    val points: Points,
    val position: Int,
    val stage: String,
    val team: Team
) {
    fun toMap(): TeamStandingModel {
        return TeamStandingModel(
            teamId = team.id,
            teamName = team.name,
            teamLogo = team.logo,
            wins = games.win.total,
            loses = games.lose.total,
            conference = group.name,
            position = position
        )
    }
}