package news.newsten.newsten;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by dell on 12/11/2016.
 */

public class SingleNews extends AppCompatActivity implements View.OnClickListener{
    private Toolbar toolbar;
    TextView singletitle,singleDescription,publish_date,singleauthor;
    ImageView singleImage;
    Button readmore;
    String author,publish,description,title,urlToImage,url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singlenews);

        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        singletitle = (TextView) findViewById(R.id.single_news_title);
        singleDescription = (TextView) findViewById(R.id.news_main_content);
        singleImage = (ImageView) findViewById(R.id.header_image);
        publish_date = (TextView) findViewById(R.id.publish_date);
        singleauthor = (TextView) findViewById(R.id.author);
        readmore = (Button) findViewById(R.id.readmore);

        Intent i = getIntent();
         author = i.getExtras().getString("author");
        publish = i.getExtras().getString("publishedAt");
        description= i.getExtras().getString("description");
        title = i.getExtras().getString("title");
         urlToImage = i.getExtras().getString("urlImage");
        url = i.getExtras().getString("url");
        getSupportActionBar().setTitle("");

        publish = publish.replace("T"," ");
       publish = publish.replace("Z"," ");


        publish_date.setText(publish);
         singletitle.setText(title);
        singleauthor.setText(author);
         singleDescription.setText(description);
        Picasso.with(this)
                .load(urlToImage)
                .placeholder(R.mipmap.load)   // optional
                .into(singleImage);
                readmore.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Intent implicit = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(implicit);
    }
}
