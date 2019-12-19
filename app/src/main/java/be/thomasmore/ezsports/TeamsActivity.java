package be.thomasmore.ezsports;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

import be.thomasmore.ezsports.customAdapters.LeaguesAdapter;
import be.thomasmore.ezsports.customAdapters.TeamsAdapter;
import be.thomasmore.ezsports.models.League;
import be.thomasmore.ezsports.models.Team;

public class TeamsActivity extends AppCompatActivity {

    String apiKey = "E_TxcWHZfSn61dJTrk8W8xz5cKWKvVp0BqAqOvHXBZ5pA8VgWmI";
    String game;
    TeamsAdapter teamsAdapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditText filter = (EditText) findViewById(R.id.search_filter);


        Bundle bundle = getIntent().getExtras();
        String game = bundle.getString("game");

        setGame(game);

        CharSequence filterword = "";
        leesTeams(filterword);

        filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                leesTeams(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    private void setGame(String game){
        this.game = game;
    }

    private void leesTeams(CharSequence filterword) {
        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();
                final List<Team> teams = jsonHelper.getTeams(result);

                teamsAdapter = new TeamsAdapter(getApplicationContext(), teams);

                final ListView listViewTeams = (ListView)findViewById(R.id.listViewTeams);

                listViewTeams.setAdapter(teamsAdapter);


                listViewTeams.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView parentView, View childView, int position, long id) {
                        teamDetail_click(teams.get(position).getId());
                    }
                });
            }
        });

        if(filterword == "") {

            httpReader.execute("https://api.pandascore.co/" + game + "/teams?token=" + apiKey);
        } else {
            httpReader.execute("https://api.pandascore.co/" + game + "/teams?search[name]=" + filterword + "&token=" + apiKey);
        }

    }



    public void teamDetail_click(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("teamId", id);
        bundle.putString("game", this.game);
        Intent intent = new Intent(this, TeamDetailActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }


}
