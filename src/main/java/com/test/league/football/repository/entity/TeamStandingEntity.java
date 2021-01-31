package com.test.league.football.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(TeamStandingEntityId.class)
public class TeamStandingEntity {

    @Id
    private Long countryId;

    @Id
    private Long leagueId;

    @Id
    private Long teamId;

    @Column
    private String countryName;

    @Column
    private String leagueName;

    @Column
    private String teamName;

    @Column
    private Long overallLeaguePosition;
}
