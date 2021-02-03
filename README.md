# FootballAPI

This microservice provides API for fetching team standings in football leagues across countries.
The requests to the API is validatd using an api-key sent in authorization header - 9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978

The data for the API is loaded to in-memory h2 dtabase using src\main\resources\data.sql
The corresponding data is fetched from database on REST API calls to get the team standings.

Sample usage of the API:
GET http://localhost:8080/footballLeague/teamStandings?countryName=England&leagueName=PremierLeague&teamName=Barcelona

Sample response:
{
    "countryId": 24,
    "countryName": "ENGLAND",
    "leagueId": 148,
    "leagueName": "PREMIERLEAGUE",
    "teamId": 227,
    "teamName": "BARCELONA",
    "overallLeaguePosition": 2
}
