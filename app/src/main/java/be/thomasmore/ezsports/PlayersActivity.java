package be.thomasmore.ezsports;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import be.thomasmore.ezsports.customAdapters.PlayersAdapter;
import be.thomasmore.ezsports.models.Player;

public class PlayersActivity extends AppCompatActivity {

    String apiKey = "E_TxcWHZfSn61dJTrk8W8xz5cKWKvVp0BqAqOvHXBZ5pA8VgWmI";
    String game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        String game = bundle.getString("game");

        setGame(game);

        leesPlayers();


    }

    private void setGame(String game){
        this.game = game;
    }

    private void leesPlayers() {
        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();
                final List<Player> players = jsonHelper.getPlayers(result);

                PlayersAdapter playersAdapter = new PlayersAdapter(getApplicationContext(), players);

                final ListView listViewPlayers = (ListView)findViewById(R.id.listViewPlayers);
                listViewPlayers.setAdapter(playersAdapter);

            }
        });
        httpReader.execute("https://api.pandascore.co/" + game + "/players?token=" + apiKey);


    }

    private void searchPlayer(String searchString) {
        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();
                final List<Player> players = jsonHelper.getPlayers(result);

                PlayersAdapter playersAdapter = new PlayersAdapter(getApplicationContext(), players);

                final ListView listViewPlayers = (ListView)findViewById(R.id.listViewPlayers);

                listViewPlayers.setAdapter(playersAdapter);
            }
        });
        httpReader.execute("https://api.pandascore.co/" + game + "/players?search[name]=" + searchString  + "&token=" + apiKey);
    }

    public void searchPlayer_click(View v) {
        EditText playerName = (EditText)findViewById(R.id.searchPlayer);

        if (playerName.getText().toString() == "") {
            leesPlayers();
        } else {
            searchPlayer(playerName.getText().toString());
        }
    }

    private void toon(String tekst) {
        Toast.makeText(getBaseContext(), tekst, Toast.LENGTH_SHORT).show();
    }
}
