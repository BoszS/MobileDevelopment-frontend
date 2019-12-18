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

import be.thomasmore.ezsports.R;
import be.thomasmore.ezsports.models.League;
import be.thomasmore.ezsports.models.Team;

public class TeamsAdapter extends ArrayAdapter<Team> {

    private final Context context;
    private final List<Team> values;

    public TeamsAdapter(Context context, List<Team> values) {
        super(context, R.layout.teamlistviewitem, values);
        this.context = context;
        this.values = values;
    }


    @Override
    public View getView(final int position, View converView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.teamlistviewitem, parent, false);

        final TextView name = (TextView)rowView.findViewById(R.id.teamname);
        final ImageView teamLogo = (ImageView)rowView.findViewById(R.id.teamlogo);

        name.setText(values.get(position).getName());
        Picasso.get().load(values.get(position).getImage_url()).into(teamLogo);

        return rowView;
    }


}
