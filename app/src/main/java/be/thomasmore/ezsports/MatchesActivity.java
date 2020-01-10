package be.thomasmore.ezsports;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
                final List<Match> matches = jsonHelper.getMatches(result);

                MatchesAdapter matchesAdapter = new MatchesAdapter(getApplicationContext(), matches);

                final ListView listViewMatches = (ListView)findViewById(R.id.listViewMatches);

                listViewMatches.setAdapter(matchesAdapter);


                listViewMatches.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView parentView, View childView, int position, long id) {
                        matchDetails(matches.get(position).getId());
                    }
                });
            }
        });
        httpReader.execute("https://api.pandascore.co/" + game + "/matches/upcoming?page[size]=30&token=" + apiKey);
    }


    public void matchDetails(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("matchId", id);
        bundle.putString("game", this.game);
        Intent intent = new Intent(this, MatchDetailActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }

}
