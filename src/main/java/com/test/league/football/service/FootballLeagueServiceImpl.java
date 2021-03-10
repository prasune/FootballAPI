package com.test.league.football.service;

import com.test.league.football.exception.ResourceNotFoundException;
import com.test.league.football.domain.TeamStanding;
import com.test.league.football.domain.converter.TeamStandingConverter;
import com.test.league.football.repository.FootballLeagueRepository;
import com.test.league.football.repository.entity.TeamStandingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FootballLeagueServiceImpl implements FootballLeagueService {

    @Autowired
    private FootballLeagueRepository footballLeagueRepository;

    @Override
    public TeamStanding getTeamStanding(String countryName,
                                        String leagueName,
                                        String teamName) {
        TeamStanding teamStanding = null;
        Optional<TeamStandingEntity> teamStandingEntityOptional =
                footballLeagueRepository.getTeamStanding(countryName.toUpperCase(),
                        leagueName.toUpperCase(), teamName.toUpperCase());
        if (teamStandingEntityOptional.isPresent()) {
            teamStanding = TeamStandingConverter.toTeamStanding(teamStandingEntityOptional.get());
        } else {
            throw new ResourceNotFoundException("TeamStanding");
        }
        return teamStanding;
    }
}
