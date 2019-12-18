package be.thomasmore.ezsports;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Toast;

public class ChoiceActivity extends AppCompatActivity {

    String game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        String game = bundle.getString("game");

        setGame(game);
    }

    private void setGame(String game){
        this.game = game;
    }

    public void leagues_click(View v) {
        Bundle bundle = new Bundle();
        bundle.putString("game", this.game);
        Intent intent = new Intent(this, LeaguesActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    public void players_click(View v) {
        Bundle bundle = new Bundle();
        bundle.putString("game", this.game);
        Intent intent = new Intent(this, PlayersActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    public void teams_click(View v) {
        Bundle bundle = new Bundle();
        bundle.putString("game", this.game);
        Intent intent = new Intent(this, TeamsActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }




    private void toon(String tekst) {
        Toast.makeText(getBaseContext(), tekst, Toast.LENGTH_SHORT).show();
    }
}
