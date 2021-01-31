package com.test.league.football;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = FootballApiApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude=SecurityAutoConfiguration.class)
// @TestPropertySource(locations = "classpath:application-integrationtest.properties")
//@AutoConfigureTestDatabase
public class FootballLeagueControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void validateMissingCountryNameError() throws Exception {

        mvc.perform(get("/footballLeague/teamStandings")
                .param("leagueName", "PremierLeague")
                .param("teamName", "Barcelona")
                .header("Authorization", "api-key 9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void validateMissingLeagueNameError() throws Exception {

        mvc.perform(get("/footballLeague/teamStandings")
                .param("countryName", "England")
                .param("teamName", "Barcelona")
                .header("Authorization", "api-key 9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void validateMissingTeamNameError() throws Exception {

        mvc.perform(get("/footballLeague/teamStandings")
                .param("countryName", "England")
                .param("leagueName", "PremierLeague")
                .header("Authorization", "api-key 9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void validateNoMatchingInformationFound() throws Exception {

        mvc.perform(get("/footballLeague/teamStandings")
                .param("countryName", "England")
                .param("leagueName", "PremierLeague")
                .param("teamName", "Barcelona123")
                .header("Authorization", "api-key 9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void validateWithMixedCaseRequestParams() throws Exception {

        mvc.perform(get("/footballLeague/teamStandings")
                .param("countryName", "England")
                .param("leagueName", "PremierLeague")
                .param("teamName", "Barcelona")
                .header("Authorization", "api-key 9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("countryName", is("ENGLAND")))
                .andExpect(jsonPath("leagueName", is("PREMIERLEAGUE")))
                .andExpect(jsonPath("teamName", is("BARCELONA")))
                .andExpect(jsonPath("overallLeaguePosition", is(2)));
    }

    @Test
    public void validateWithUpperCaseRequestParams() throws Exception {

        mvc.perform(get("/footballLeague/teamStandings")
                .param("countryName", "ENGLAND")
                .param("leagueName", "PREMIERLEAGUE")
                .param("teamName", "MANCHESTERUNITED")
                .header("Authorization", "api-key 9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("countryName", is("ENGLAND")))
                .andExpect(jsonPath("leagueName", is("PREMIERLEAGUE")))
                .andExpect(jsonPath("teamName", is("MANCHESTERUNITED")))
                .andExpect(jsonPath("overallLeaguePosition", is(1)));
    }

    @Test
    public void validateWithLowerCaseRequestParams() throws Exception {

        mvc.perform(get("/footballLeague/teamStandings")
                .param("countryName", "england")
                .param("leagueName", "championsleague")
                .param("teamName", "leicester")
                .header("Authorization", "api-key 9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("countryName", is("ENGLAND")))
                .andExpect(jsonPath("leagueName", is("CHAMPIONSLEAGUE")))
                .andExpect(jsonPath("teamName", is("LEICESTER")))
                .andExpect(jsonPath("overallLeaguePosition", is(2)));
    }
}
