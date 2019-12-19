package be.thomasmore.ezsports;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

import be.thomasmore.ezsports.customAdapters.MatchesAdapter;
import be.thomasmore.ezsports.customAdapters.PlayersAdapter;
import be.thomasmore.ezsports.models.Match;
import be.thomasmore.ezsports.models.Player;

public class MatchesActivity extends AppCompatActivity {

    String apiKey = "E_TxcWHZfSn61dJTrk8W8xz5cKWKvVp0BqAqOvHXBZ5pA8VgWmI";
    String game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        String game = bundle.getString("game");

        setGame(game);

        leesMatches();
    }

    private void setGame(String game){
        this.game = game;
    }

    private void leesMatches() {
        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();
                final List<Match> matchces = jsonHelper.getMatches(result);

                MatchesAdapter matchesAdapter = new MatchesAdapter(getApplicationContext(), matchces);

                final ListView listViewMatches = (ListView)findViewById(R.id.listViewMatches);

                listViewMatches.setAdapter(matchesAdapter);
            }
        });
        httpReader.execute("https://api.pandascore.co/" + game + "/matches?token=" + apiKey);
    }

}
