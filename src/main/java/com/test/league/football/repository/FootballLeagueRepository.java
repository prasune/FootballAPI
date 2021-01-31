package com.test.league.football.repository;

import com.test.league.football.repository.entity.TeamStandingEntity;
import com.test.league.football.repository.entity.TeamStandingEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FootballLeagueRepository extends JpaRepository<TeamStandingEntity, TeamStandingEntityId> {

    @Query("SELECT u FROM TeamStandingEntity u " +
            " WHERE u.countryName = :countryName " +
            " and u.leagueName = :leagueName" +
            " and u.teamName = :teamName")
    Optional<TeamStandingEntity> getTeamStanding(String countryName,
                                                 String leagueName,
                                                 String teamName);
}
