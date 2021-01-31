package com.test.league.football.domain.converter;

import com.test.league.football.domain.TeamStanding;
import com.test.league.football.repository.entity.TeamStandingEntity;

public class TeamStandingConverter {

    public static TeamStanding toTeamStanding(TeamStandingEntity teamStandingEntity) {
        return TeamStanding.builder()
                .countryId(teamStandingEntity.getCountryId())
                .countryName(teamStandingEntity.getCountryName())
                .leagueId(teamStandingEntity.getLeagueId())
                .leagueName(teamStandingEntity.getLeagueName())
                .teamId(teamStandingEntity.getTeamId())
                .teamName(teamStandingEntity.getTeamName())
                .overallLeaguePosition(teamStandingEntity.getOverallLeaguePosition())
                .build();
    }
}
