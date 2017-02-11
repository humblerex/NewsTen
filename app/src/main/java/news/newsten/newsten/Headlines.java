package news.newsten.newsten;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Headlines extends AppCompatActivity {

    ListView listView;
    ProgressDialog pDialog;
    ArrayList<HashMap<String, String>> articlesList;
    TextView newscompany;

    ListViewAdapter adapter;


    static String AUTHOR = "author";
    static String TITLE = "title";
    static String DESCRIPTION = "description";
    static String URL = "url";
    static String URLTOIMAGE = "urlToImage";
    static String PUBLISHEDAT = "publishedAt";

    private static String url = "";
    private String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headlines);


        listView = (ListView) findViewById(R.id.headlinelist);

        newscompany = (TextView) findViewById(R.id.newscompany);


        Intent i = getIntent();
        int pos = i.getExtras().getInt("pos");
        int cat = i.getExtras().getInt("cat");

        //entertainment
        if(pos==0 && cat == 0)
        {
            url=" https://newsapi.org/v1/articles?source=entertainment-weekly&sortBy=top&apiKey=6769e9d8ade84a8db7bba41df067aabe";
            newscompany.setText("Entertainment Weekly");
        }
        if(pos==1 && cat == 0)
        {
            url="https://newsapi.org/v1/articles?source=buzzfeed&sortBy=top&apiKey=6769e9d8ade84a8db7bba41df067aabe";
            newscompany.setText("Buzzfeed");
        }
        if(pos==2 && cat == 0)
        {
            url="https://newsapi.org/v1/articles?source=daily-mail&sortBy=top&apiKey=6769e9d8ade84a8db7bba41df067aabe";
            newscompany.setText("Daily Mail");
        }
        if(pos==3 && cat == 0)
        {
            url=" https://newsapi.org/v1/articles?source=mashable&sortBy=top&apiKey=6769e9d8ade84a8db7bba41df067aabe";
            newscompany.setText("Mashable");
        }
        if(pos==0 && cat == 0)
        {
            url=" https://newsapi.org/v1/articles?source=the-lad-bible&sortBy=top&apiKey=6769e9d8ade84a8db7bba41df067aabe";
            newscompany.setText("The Lad Bible");
        }


        //sports
        if(pos==0 && cat == 1)
        {
        url=" https://newsapi.org/v1/articles?source=bbc-sport&sortBy=top&apiKey=6769e9d8ade84a8db7bba41df067aabe";
        newscompany.setText("BBC Sport");
        }
        if(pos==1 && cat == 1)
        {
            url=" https://newsapi.org/v1/articles?source=espn&sortBy=top&apiKey=6769e9d8ade84a8db7bba41df067aabe";
            newscompany.setText("ESPN");
        }
        if(pos==2 && cat == 1)
        {
            url="https://newsapi.org/v1/articles?source=sky-sports-news&sortBy=top&apiKey=6769e9d8ade84a8db7bba41df067aabe";
            newscompany.setText("Sky Sports News");
        }

        //Business
        if(pos==0 && cat== 4)
        {
            url = " https://newsapi.org/v1/articles?source=business-insider&sortBy=top&apiKey=6769e9d8ade84a8db7bba41df067aabe";
            newscompany.setText("Business Insider");
        }
        if(pos==1 && cat== 4)
        {
            url = " https://newsapi.org/v1/articles?source=business-insider-uk&sortBy=top&apiKey=6769e9d8ade84a8db7bba41df067aabe";
            newscompany.setText("Business Insider UK");
        }
        if(pos==2 && cat== 4)
        {
            url = " https://newsapi.org/v1/articles?source=cnbc&sortBy=top&apiKey=6769e9d8ade84a8db7bba41df067aabe";
            newscompany.setText("CNBC");
        }


        //Technology
        if(pos==0 && cat== 5)
        {
            url = "https://newsapi.org/v1/articles?source=techradar&sortBy=top&apiKey=6769e9d8ade84a8db7bba41df067aabe";
            newscompany.setText("Tech Radar");
        }
        if(pos==1 && cat == 5)
        {
            url = "https://newsapi.org/v1/articles?source=techcrunch&sortBy=top&apiKey=6769e9d8ade84a8db7bba41df067aabe";
            newscompany.setText("Tech Crunch");
        }
        if(pos==2 && cat == 5)
        {
            url = "https://newsapi.org/v1/articles?source=t3n&sortBy=top&apiKey=6769e9d8ade84a8db7bba41df067aabe";
            newscompany.setText("T3n");
        }

        new GetHeadlines().execute();
    }

    private class GetHeadlines extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Headlines.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            articlesList = new ArrayList<HashMap<String, String>>();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray articles= jsonObj.getJSONArray("articles");

                    // looping through All Contacts
                    for (int i = 0; i < articles.length(); i++) {
                        JSONObject c = articles.getJSONObject(i);
                       HashMap<String, String> headline = new HashMap<>();

                        // adding each child node to HashMap key => value
                        headline.put("author",c.getString("author"));
                        headline.put("title", c.getString("title"));
                        headline.put("description", c.getString("description"));
                        headline.put("webUrl", c.getString("url"));
                        headline.put("urlToImage", c.getString("urlToImage"));
                        headline.put("publishedAt", c.getString("publishedAt"));
                        // adding contact to contact list
                        articlesList.add(headline);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            listView = (ListView) findViewById(R.id.headlinelist);

            adapter = new ListViewAdapter(Headlines.this,articlesList);

            listView.setAdapter(adapter);


        }

    }
}




















