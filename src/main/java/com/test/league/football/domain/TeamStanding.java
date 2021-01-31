package com.test.league.football.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamStanding {

    private Long countryId;

    private String countryName;

    private Long leagueId;

    private String leagueName;

    private Long teamId;

    private String teamName;

    private Long overallLeaguePosition;
}
