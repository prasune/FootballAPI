package com.test.league.football.service;

import com.test.league.football.domain.TeamStanding;

public interface FootballLeagueService {

    TeamStanding getTeamStanding(String countryName, String leagueName, String teamName);
}
