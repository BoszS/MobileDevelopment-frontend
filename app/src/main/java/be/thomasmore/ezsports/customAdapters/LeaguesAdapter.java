package be.thomasmore.ezsports.customAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

import be.thomasmore.ezsports.R;
import be.thomasmore.ezsports.models.League;
import be.thomasmore.ezsports.models.Player;

public class LeaguesAdapter extends ArrayAdapter<League> {

    private final Context context;
    private final List<League> values;

    public LeaguesAdapter(Context context, List<League> values) {
        super(context, R.layout.leaguelistviewitem, values);
        this.context = context;
        this.values = values;
    }


    @Override
    public View getView(final int position, View converView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.leaguelistviewitem, parent, false);

        final TextView name = (TextView)rowView.findViewById(R.id.leaguename);
        final ImageView leagueLogo = (ImageView)rowView.findViewById(R.id.leaguelogo);

        name.setText(values.get(position).getName());
        Picasso.with(context).load(values.get(position).getImage_url()).into(leagueLogo);

        return rowView;
    }


}
