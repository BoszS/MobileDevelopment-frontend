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
import be.thomasmore.ezsports.models.Player;

import be.thomasmore.ezsports.models.Match;
import be.thomasmore.ezsports.models.Team;

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

        final TextView opponentname1 = (TextView)rowView.findViewById(R.id.opponentname1);
        final TextView opponentname2 = (TextView)rowView.findViewById(R.id.opponentname2);
        final ImageView opponentlogo1 = (ImageView)rowView.findViewById(R.id.opponentlogo1);
        final ImageView opponentlogo2 = (ImageView)rowView.findViewById(R.id.opponentlogo2);
        final TextView date = (TextView)rowView.findViewById(R.id.matchdate);

        if (values.get(position).getOpponents().size() == 2){
            opponentname1.setText(values.get(position).getOpponents().get(0).getName());
            Picasso.get().load(values.get(position).getOpponents().get(0).getImage_url()).into(opponentlogo1);

            opponentname2.setText(values.get(position).getOpponents().get(1).getName());
            Picasso.get().load(values.get(position).getOpponents().get(1).getImage_url()).into(opponentlogo2);


        } else if (values.get(position).getOpponents().size() == 1) {

            opponentname1.setText(values.get(position).getOpponents().get(0).getName());
            Picasso.get().load(values.get(position).getOpponents().get(0).getImage_url()).into(opponentlogo1);

            opponentname2.setText("TBD");


        } else {
            opponentname1.setText("TBD");
            opponentname2.setText("TBD");
        }


        String begindate = values.get(position).getDate();
        String begintime = values.get(position).getTime();
        date.setText(begindate + " " + begintime);

        return rowView;
    }

}
