package com.test.league.football.controller;

import com.test.league.football.controller.utils.ValidationUtil;
import com.test.league.football.domain.TeamStanding;
import com.test.league.football.service.FootballLeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.constraints.*;

@RestController
@RequestMapping("/footballLeague")
public class FootballLeagueController {

    @Autowired
    private FootballLeagueService footballLeagueService;

    @GetMapping("/teamStandings")
    public TeamStanding getTeamStanding(@RequestParam("countryName") String countryName,
                                        @RequestParam("leagueName") String leagueName,
                                        @RequestParam("teamName") String teamName) {

        ValidationUtil.rejectIfEmpty("countryName", countryName);
        ValidationUtil.rejectIfEmpty("leagueName", leagueName);
        ValidationUtil.rejectIfEmpty("teamName", teamName);
        return footballLeagueService.getTeamStanding(countryName, leagueName, teamName);
    }
}
