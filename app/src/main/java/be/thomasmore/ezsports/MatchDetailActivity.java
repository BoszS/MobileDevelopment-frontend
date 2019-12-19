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
import be.thomasmore.ezsports.models.Match;
import be.thomasmore.ezsports.models.Team;

public class MatchDetailActivity extends AppCompatActivity {

    String apiKey = "E_TxcWHZfSn61dJTrk8W8xz5cKWKvVp0BqAqOvHXBZ5pA8VgWmI";
    String game;
    Match selectedMatch;
    int matchId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Bundle bundle = getIntent().getExtras();
        String game = bundle.getString("game");
        int matchId = bundle.getInt("matchId");


        setGame(game);

        setMatchId(matchId);

        leesMatch();



    }
    private void setGame(String game){
        this.game = game;
    }

    private void setMatchId(int matchId){
        this.matchId = matchId;
    }


    public void leesMatch() {
        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();
                Match match = jsonHelper.getMatch(result);

                matchLayout(match);

                selectedMatch = match;



            }
        });


        httpReader.execute("https://api.pandascore.co/" + game + "/matches/?filter[id]=" + matchId + "&token=" + apiKey);



    }


    public void teamDetail_click(View v) {


        int teamId = Integer.parseInt(v.getTag().toString());

        Bundle bundle = new Bundle();
        bundle.putInt("teamId", teamId);
        bundle.putString("game", this.game);
        Intent intent = new Intent(this, TeamDetailActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }


    private void matchLayout(Match match) {

        setContentView(R.layout.content_match_details);

        TextView textViewName = (TextView)findViewById(R.id.matchDetailName);
        textViewName.setText(match.getName());

        TextView textViewSerie = (TextView)findViewById(R.id.matchDetailSerie);
        textViewSerie.setText(match.getSerie().getName());

        TextView textViewLeague = (TextView)findViewById(R.id.matchDetailLeague);
        textViewLeague.setText(match.getLeague().getName());

        ImageView imageViewLeague = (ImageView)findViewById(R.id.matchDetailLeagueLogo);
        Picasso.get().load(match.getLeague().getImage_url()).into(imageViewLeague);

        TextView textViewTournament = (TextView)findViewById(R.id.matchDetailTournament);
        textViewTournament.setText(match.getTournament().getName());

        TextView textViewDate = (TextView)findViewById(R.id.matchDetailDate);
        textViewDate.setText(match.getDate());

        TextView textViewTime = (TextView)findViewById(R.id.matchDetailTime);
        textViewTime.setText(match.getTime());


        ImageView imageViewTeam1Logo = (ImageView)findViewById(R.id.team1Logo);

        ImageView imageViewTeam2Logo = (ImageView)findViewById(R.id.team2Logo);


        if (match.getOpponents().size() == 2){

            Picasso.get().load(match.getOpponents().get(0).getImage_url()).into(imageViewTeam1Logo);
            imageViewTeam1Logo.setTag(match.getOpponents().get(0).getId());
            imageViewTeam1Logo.setClickable(true);

            Picasso.get().load(match.getOpponents().get(1).getImage_url()).into(imageViewTeam2Logo);
            imageViewTeam2Logo.setTag(match.getOpponents().get(1).getId());
            imageViewTeam2Logo.setClickable(true);


        } else if (match.getOpponents().size() == 1) {

            Picasso.get().load(match.getOpponents().get(0).getImage_url()).into(imageViewTeam1Logo);
            imageViewTeam1Logo.setTag(match.getOpponents().get(0).getId());
            imageViewTeam1Logo.setClickable(true);


            imageViewTeam2Logo.setImageResource(getResources().getIdentifier("questionmark.png", "drawable", this.getPackageName()));


        } else {
            imageViewTeam2Logo.setImageResource(getResources().getIdentifier("questionmark.png", "drawable", this.getPackageName()));
            imageViewTeam2Logo.setImageResource(getResources().getIdentifier("questionmark.png", "drawable", this.getPackageName()));
        }



    }



}
