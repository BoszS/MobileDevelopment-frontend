package be.thomasmore.ezsports;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.ezsports.models.Player;

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
}
