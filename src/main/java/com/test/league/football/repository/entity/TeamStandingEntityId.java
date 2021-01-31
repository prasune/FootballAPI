package com.test.league.football.repository.entity;

import lombok.*;

import java.io.Serializable;

@Builder
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TeamStandingEntityId implements Serializable {

    private Long countryId;

    private Long leagueId;

    private Long teamId;
}
