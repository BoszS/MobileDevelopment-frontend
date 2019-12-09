package be.thomasmore.ezsports;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import be.thomasmore.ezsports.customAdapters.LeaguesAdapter;
import be.thomasmore.ezsports.customAdapters.PlayersAdapter;
import be.thomasmore.ezsports.models.League;
import be.thomasmore.ezsports.models.Player;

public class LeaguesActivity extends AppCompatActivity {

    String apiKey = "E_TxcWHZfSn61dJTrk8W8xz5cKWKvVp0BqAqOvHXBZ5pA8VgWmI";
    String game;

    LeaguesAdapter leaguesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leagues);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditText filter = (EditText) findViewById(R.id.search_filter);


        Bundle bundle = getIntent().getExtras();
        String game = bundle.getString("game");

        setGame(game);

        CharSequence filterword = "";
        leesLeagues(filterword);

        filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                leesLeagues(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    private void setGame(String game){
        this.game = game;
    }

    private void leesLeagues(CharSequence filterword) {
        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();
                final List<League> leagues = jsonHelper.getLeagues(result);

                leaguesAdapter = new LeaguesAdapter(getApplicationContext(), leagues);

                final ListView listViewLeagues = (ListView)findViewById(R.id.listViewLeagues);

                listViewLeagues.setAdapter(leaguesAdapter);
            }
        });

        if(filterword == "") {

            httpReader.execute("https://api.pandascore.co/" + game + "/leagues?token=" + apiKey);
        } else {
            httpReader.execute("https://api.pandascore.co/" + game + "/leagues?search[name]=" + filterword + "&token=" + apiKey);
        }
    }

}
