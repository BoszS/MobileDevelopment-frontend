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
import be.thomasmore.ezsports.models.Team;

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
                JSONObject jsonObjectPlayer = jsonArray.getJSONObject(i);

                Match match = new Match();

                match.setId(jsonObjectPlayer.getInt("id"));
                match.setName(jsonObjectPlayer.getString("name"));


                matches.add(match);
            }

        }
        catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        return matches;
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
