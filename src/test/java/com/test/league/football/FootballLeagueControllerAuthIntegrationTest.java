package com.test.league.football;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = FootballApiApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude=SecurityAutoConfiguration.class)
public class FootballLeagueControllerAuthIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void validateAuthErrorWithNoApiKey() throws Exception {

        mvc.perform(get("/footballLeague/teamStandings")
                .param("countryName", "England")
                .param("leagueName", "PremierLeague")
                .param("teamName", "Barcelona"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void validateAuthErrorWithInvalidApiKey() throws Exception {

        mvc.perform(get("/footballLeague/teamStandings")
                .param("countryName", "England")
                .param("leagueName", "PremierLeague")
                .param("teamName", "Barcelona")
                .header("Authorization", "api-key 9bb66184e0c8145384fd2cc0f7b914ada57b4e")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}
