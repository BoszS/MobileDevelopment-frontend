package be.thomasmore.ezsports;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;

import be.thomasmore.ezsports.customAdapters.PlayersAdapter;
import be.thomasmore.ezsports.customAdapters.TeamsAdapter;
import be.thomasmore.ezsports.models.Team;

public class TeamDetailActivity extends AppCompatActivity {

    String apiKey = "E_TxcWHZfSn61dJTrk8W8xz5cKWKvVp0BqAqOvHXBZ5pA8VgWmI";
    String game;
    String teamName;
    private Team selectedTeam;

    TeamsAdapter teamsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditText filter = (EditText) findViewById(R.id.search_filter);


        Bundle bundle = getIntent().getExtras();
        String game = bundle.getString("game");
        String teamName = bundle.getString("teamName");

        setGame(game);
        setTeamName(teamName);

        leesTeam();
        teamLayout();



    }
    private void setGame(String game){
        this.game = game;
    }

    private void setTeamName(String teamName){
        this.teamName = teamName;
    }


    private void leesTeam() {
        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();
                final Team team = jsonHelper.getTeam(result);

                selectedTeam = team;
            }
        });


        httpReader.execute("https://api.pandascore.co/" + game + "/teams/" + teamName + "?token=" + apiKey);


    }

    private void teamLayout() {
        String teamId = NumberFormat.getInstance().format(selectedTeam.getId());

        TextView textViewName = (TextView)findViewById(R.id.teamDetailName);
        textViewName.setText(selectedTeam.getName());

        ImageView imageViewTeamLogo = (ImageView)findViewById(R.id.teamDetailLogo);
        Picasso.get().load(selectedTeam.getImage_url()).into(imageViewTeamLogo);

        PlayersAdapter playersAdapter = new PlayersAdapter(getApplicationContext(), selectedTeam.getPlayers());

        final ListView listViewTeamPlayers = (ListView)findViewById(R.id.listViewTeamPlayers);

        listViewTeamPlayers.setAdapter(playersAdapter);
    }


}
