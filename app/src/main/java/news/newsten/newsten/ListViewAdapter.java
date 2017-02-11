package news.newsten.newsten;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by dell on 12/10/2016.
 */

public class ListViewAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    HashMap<String, String> resultp = new HashMap<String, String>();

    public ListViewAdapter(Context context,
                           ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;
       }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        TextView headliness;
        ImageView newsImage;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.custom_list, parent, false);
        // Get the position
        resultp = data.get(position);

        headliness = (TextView) itemView.findViewById(R.id.headliness);
        newsImage = (ImageView) itemView.findViewById(R.id.newsImage);

        headliness.setText(resultp.get(Headlines.TITLE));

        Picasso.with(context)
                .load(resultp.get(Headlines.URLTOIMAGE))
                .placeholder(R.mipmap.load)   // optional
                .into(newsImage);
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get the position
                resultp = data.get(position);
                Intent intent = new Intent(context, SingleNews.class);
                // Pass all  rank
                intent.putExtra("urlImage", resultp.get(Headlines.URLTOIMAGE));
                // Pass all urlImage
                intent.putExtra("author", resultp.get(Headlines.AUTHOR));
                // Pass all  author
                intent.putExtra("title",resultp.get(Headlines.TITLE));
                // Pass all  title
                intent.putExtra("description", resultp.get(Headlines.DESCRIPTION));
                // Pass all  description
                intent.putExtra("url", resultp.get(Headlines.URL));
                // Pass all  url
                intent.putExtra("publishedAt",resultp.get(Headlines.PUBLISHEDAT));
                // Pass all  publisheAt

                // Start SingleNews Class
                context.startActivity(intent);

            }
        });

    return itemView;
    }
}
