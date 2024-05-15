package com.example.nbaapp.data.dto.TeamDetailDTO

import com.example.nbaapp.data.dto.TeamsDTO.Country
import com.example.nbaapp.domain.model.TeamDetailModel

data class ResponseDetail(
    val country: Country,
    val games: GamesDetail,
    val league: LeagueDetail,
    val points: PointsDetail,
    val team: TeamDetail
) {
    fun toMap(): TeamDetailModel {
        return TeamDetailModel(
            name = team.name,
            logo = team.logo,
            gamesPlayedAtHome = games.played.home,
            gamesPlayedAway = games.played.away,
            gamesWinAtHome = games.wins.home.total,
            gamesLoseAtHome = games.loses.home.total,
            gamesWinAway = games.wins.away.total,
            gamesLoseAway = games.loses.away.total,
            totalWins = games.wins.all.total,
            totalLoses = games.loses.all.total,
            pointForAtHome = points.forPoints.total.home,
            pointForAway = points.forPoints.total.away,
            totalForPoint = points.forPoints.total.all,
            averagePointForAtHome = points.forPoints.average.home,
            averagePointForAway = points.forPoints.average.away,
            totalAveragePointFor = points.forPoints.average.all,
            pointAgainstAtHome = points.against.total.home,
            pointAgainstAway = points.against.total.away,
            totalAgainstPoint = points.against.total.all,
            averagePointAgainstAtHome = points.against.average.home,
            averagePointAgainstAway = points.against.average.away,
            totalAveragePointAgainst = points.against.average.all
        )
    }
}
