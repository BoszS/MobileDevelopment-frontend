package be.thomasmore.ezsports;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

import be.thomasmore.ezsports.customAdapters.PlayersAdapter;
import be.thomasmore.ezsports.models.Team;

public class TeamDetailActivity extends AppCompatActivity {

    String apiKey = "E_TxcWHZfSn61dJTrk8W8xz5cKWKvVp0BqAqOvHXBZ5pA8VgWmI";
    String game;
    int teamId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Bundle bundle = getIntent().getExtras();
        String game = bundle.getString("game");
        int teamId = bundle.getInt("teamId");


        setGame(game);

        setTeamId(teamId);

        leesTeam();



    }
    private void setGame(String game){
        this.game = game;
    }

    private void setTeamId(int teamId){
        this.teamId = teamId;
    }


    public void leesTeam() {
        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();
                Team team = jsonHelper.getTeam(result);

                teamLayout(team);

            }
        });


        httpReader.execute("https://api.pandascore.co/" + game + "/teams/?filter[id]=" + teamId + "&token=" + apiKey);



    }

    private void teamLayout(Team team) {

        setContentView(R.layout.content_team_details);

        TextView textViewName = (TextView)findViewById(R.id.teamDetailName);
        textViewName.setText(team.getName());

        ImageView imageViewTeamLogo = (ImageView)findViewById(R.id.teamDetailLogo);
        Picasso.get().load(team.getImage_url()).into(imageViewTeamLogo);

        PlayersAdapter playersAdapter = new PlayersAdapter(getApplicationContext(), team.getPlayers());

        final ListView listViewPlayers = (ListView)findViewById(R.id.listViewTeamPlayers);

        listViewPlayers.setAdapter(playersAdapter);

    }



}
