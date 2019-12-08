package be.thomasmore.ezsports.customAdapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import be.thomasmore.ezsports.R;
import be.thomasmore.ezsports.models.Player;

public class PlayersAdapter extends ArrayAdapter<Player> {

    private final Context context;
    private final List<Player> values;

    public PlayersAdapter(Context context, List<Player> values) {
        super(context, R.layout.playerlistviewitem, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(final int position, View converView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.playerlistviewitem, parent, false);

        final TextView name = (TextView)rowView.findViewById(R.id.playername);
        final ImageView favoriteImg = (ImageView)rowView.findViewById(R.id.playerfavorite);

        name.setText(values.get(position).getName());
        favoriteImg.setImageResource(R.drawable.favorite_on);

        return rowView;
    }

}
