package be.thomasmore.ezsports.customAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import be.thomasmore.ezsports.R;
import be.thomasmore.ezsports.models.Player;

import be.thomasmore.ezsports.models.Match;

public class MatchesAdapter extends ArrayAdapter<Match> {

    private final Context context;
    private final List<Match> values;

    public MatchesAdapter(Context context, List<Match> values) {
        super(context, R.layout.matchlistviewitem, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(final int position, View converView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.matchlistviewitem, parent, false);

        final TextView name = (TextView)rowView.findViewById(R.id.matchname);

        name.setText(values.get(position).getName());

        return rowView;
    }

}
