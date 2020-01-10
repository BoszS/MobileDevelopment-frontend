package be.thomasmore.ezsports;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import be.thomasmore.ezsports.models.League;
import be.thomasmore.ezsports.models.Match;
import be.thomasmore.ezsports.models.Player;
import be.thomasmore.ezsports.models.Serie;
import be.thomasmore.ezsports.models.Team;
import be.thomasmore.ezsports.models.Tournament;

public class JsonHelper {

    public List<Player> getPlayers(String jsonTekst) {
        List<Player> players = new ArrayList<Player>();

        try {
            JSONArray jsonArray = new JSONArray(jsonTekst);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectPlayer = jsonArray.getJSONObject(i);

                Player player = new Player();

                player.setId(jsonObjectPlayer.getInt("id"));
                player.setName(jsonObjectPlayer.getString("name"));
                player.setFirst_name(jsonObjectPlayer.getString("first_name"));
                player.setLast_name(jsonObjectPlayer.getString("last_name"));
                player.setHometown(jsonObjectPlayer.getString("hometown"));
                player.setImage_url(jsonObjectPlayer.getString("image_url"));

                players.add(player);
            }

        }
        catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        return players;
    }


    public List<League> getLeagues(String jsonTekst) {
        List<League> leagues = new ArrayList<League>();

        try {
            JSONArray jsonArray = new JSONArray(jsonTekst);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectPlayer = jsonArray.getJSONObject(i);

                League league = new League();

                league.setId(jsonObjectPlayer.getInt("id"));
                league.setName(jsonObjectPlayer.getString("name"));
                league.setImage_url(jsonObjectPlayer.getString("image_url"));

                leagues.add(league);
            }

        }
        catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        return leagues;
    }

    public List<Match> getMatches(String jsonTekst) {
        List<Match> matches = new ArrayList<Match>();

        try {
            JSONArray jsonArray = new JSONArray(jsonTekst);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObjectMatch = jsonArray.getJSONObject(i);

                Match match = new Match();

                match.setId(jsonObjectMatch.getInt("id"));
                match.setName(jsonObjectMatch.getString("name"));
                match.setBegin_at(jsonObjectMatch.getString("begin_at"));


                List<Team> opponents = new ArrayList<Team>();
                JSONArray opponentsArray = jsonObjectMatch.getJSONArray("opponents");


                for (int x = 0; x < opponentsArray.length(); x++) {
                    JSONObject jsonObjectOpponent = opponentsArray.getJSONObject(x).getJSONObject("opponent");

                    Team opponent = new Team();

                    opponent.setId(jsonObjectOpponent.getInt("id"));
                    opponent.setName(jsonObjectOpponent.getString("name"));
                    opponent.setImage_url(jsonObjectOpponent.getString("image_url"));


                    opponents.add(opponent);
                }

                match.setOpponents(opponents);

                matches.add(match);

            }

        }
        catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        return matches;
    }


    public Match getMatch(String jsonTekst) {
        Match match = new Match();

        try {
            JSONArray jsonArray = new JSONArray(jsonTekst);


                JSONObject jsonObjectMatch = jsonArray.getJSONObject(0);

                match.setId(jsonObjectMatch.getInt("id"));
                match.setName(jsonObjectMatch.getString("name"));
                match.setBegin_at(jsonObjectMatch.getString("begin_at"));

                JSONObject jsonObjectLeague = jsonObjectMatch.getJSONObject("league");
                League league = new League();
                league.setImage_url(jsonObjectLeague.getString("image_url"));
                league.setName(jsonObjectLeague.getString("name"));
                match.setLeague(league);

                JSONObject jsonObjectSerie = jsonObjectMatch.getJSONObject("serie");
                Serie serie = new Serie();
                serie.setName(jsonObjectSerie.getString("name"));
                match.setSerie(serie);

                JSONObject jsonObjectTournament = jsonObjectMatch.getJSONObject("tournament");
                Tournament tournament = new Tournament();
                tournament.setName(jsonObjectTournament.getString("name"));
                match.setTournament(tournament);



                List<Team> opponents = new ArrayList<Team>();
                JSONArray opponentsArray = jsonObjectMatch.getJSONArray("opponents");


                for (int x = 0; x < opponentsArray.length(); x++) {
                    JSONObject jsonObjectOpponent = opponentsArray.getJSONObject(x).getJSONObject("opponent");

                    Team opponent = new Team();

                    opponent.setId(jsonObjectOpponent.getInt("id"));
                    opponent.setName(jsonObjectOpponent.getString("name"));
                    opponent.setImage_url(jsonObjectOpponent.getString("image_url"));


                    opponents.add(opponent);
                }

                match.setOpponents(opponents);



        }
        catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        return match;
    }



    public List<Team> getTeams(String jsonTekst) {
        List<Team> teams = new ArrayList<Team>();

        try {
            JSONArray jsonArray = new JSONArray(jsonTekst);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectTeam = jsonArray.getJSONObject(i);

                Team team = new Team();

                team.setId(jsonObjectTeam.getInt("id"));
                team.setName(jsonObjectTeam.getString("name"));
                team.setImage_url(jsonObjectTeam.getString("image_url"));
                team.setAcronym(jsonObjectTeam.getString("acronym"));

                teams.add(team);
            }

        }
        catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        return teams;
    }


    public Team getTeam(String jsonTekst) {
        Team team = new Team();
        List<Player> players = new ArrayList<Player>();

        try {
            JSONArray jsonArray = new JSONArray(jsonTekst);


                JSONObject jsonObjectTeam = jsonArray.getJSONObject(0);

                team.setId(jsonObjectTeam.getInt("id"));
                team.setName(jsonObjectTeam.getString("name"));
                team.setImage_url(jsonObjectTeam.getString("image_url"));
                team.setAcronym(jsonObjectTeam.getString("acronym"));

                JSONArray playersArray = jsonObjectTeam.getJSONArray("players");

                for (int i = 0; i < playersArray.length(); i++) {
                    JSONObject jsonObjectPlayer = playersArray.getJSONObject(i);

                    Player player = new Player();

                    player.setId(jsonObjectPlayer.getInt("id"));
                    player.setName(jsonObjectPlayer.getString("name"));
                    player.setFirst_name(jsonObjectPlayer.getString("first_name"));
                    player.setLast_name(jsonObjectPlayer.getString("last_name"));
                    player.setHometown(jsonObjectPlayer.getString("hometown"));
                    player.setImage_url(jsonObjectPlayer.getString("image_url"));

                    players.add(player);
                }

                team.setPlayers(players);



        }
        catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        return team;
    }

    public Player getPlayer(String jsonTekst) {
        Player player = new Player();

        try {
            JSONObject jsonObjectPlayer = new JSONObject(jsonTekst);

            player.setId(jsonObjectPlayer.getInt("id"));
            player.setName(jsonObjectPlayer.getString("name"));
            player.setFirst_name(jsonObjectPlayer.getString("first_name"));
            player.setLast_name(jsonObjectPlayer.getString("last_name"));
            player.setHometown(jsonObjectPlayer.getString("hometown"));
            player.setImage_url(jsonObjectPlayer.getString("image_url"));

            JSONObject jsonObjectTeam = jsonObjectPlayer.getJSONObject("current_team");
            player.setTeam_name(jsonObjectTeam.getString("name"));
            player.setTeam_image_url(jsonObjectTeam.getString("image_url"));

        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        return player;
    }
}
