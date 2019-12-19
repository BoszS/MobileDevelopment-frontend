package be.thomasmore.ezsports;

import android.media.Image;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import be.thomasmore.ezsports.models.Player;

public class PlayerDetailActivity extends AppCompatActivity {

    String apiKey = "E_TxcWHZfSn61dJTrk8W8xz5cKWKvVp0BqAqOvHXBZ5pA8VgWmI";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        int playerId = bundle.getInt("playerId");

        leesPlayer(playerId);
    }


    private void leesPlayer(int id) {
        final HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();
                final Player player = jsonHelper.getPlayer(result);

                TextView textViewPlayer = (TextView) findViewById(R.id.playerName);
                ImageView imageViewPlayer = (ImageView) findViewById(R.id.playerImage);
                TextView textViewPlayerFirstname = (TextView) findViewById(R.id.playerFirstname);
                TextView textViewPlayerLastname = (TextView) findViewById(R.id.playerLastname);
                TextView textViewPlayerHometown = (TextView) findViewById(R.id.playerHometown);
                TextView textViewTeamName = (TextView) findViewById(R.id.teamName);
                ImageView imageViewTeam = (ImageView) findViewById(R.id.teamImage);

                textViewPlayer.setText(player.getName());
                Picasso.get().load(player.getImage_url()).into(imageViewPlayer);
                textViewPlayerFirstname.setText("Firstname: " + player.getFirst_name());
                textViewPlayerLastname.setText("Lastname: " + player.getLast_name());
                textViewPlayerHometown.setText("Hometown: " + player.getHometown());
                textViewTeamName.setText("Team: " + player.getTeam_name());
                Picasso.get().load(player.getTeam_image_url()).into(imageViewTeam);

            }
        });
        httpReader.execute("https://api.pandascore.co/players/" + id + "?token=" + apiKey);
    }

}
