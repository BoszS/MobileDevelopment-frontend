package be.thomasmore.ezsports;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Toast;

public class GamesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    // Het spel wordt verstuurt naar ChoiceActivity
    public void game_click(View v) {
        Bundle bundle = new Bundle();
        bundle.putString("game", v.getTag().toString());

        Intent intent = new Intent(this, ChoiceActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    private void toon(String tekst) {
        Toast.makeText(getBaseContext(), tekst, Toast.LENGTH_SHORT).show();
    }
}
