package com.example.tanaygupta.speco;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ChannelActivity extends Activity {

    private ChannelActivity local;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rss_channel);

        String rssUrl = (String)getIntent().getExtras().get("rss-url");

        // Set reference to this activity
        local = this;

        GetRSSDataTask task = new GetRSSDataTask();
        // Start process RSS task
        task.execute(rssUrl);
    }

    private class GetRSSDataTask extends AsyncTask<String, Void, List<RssItem>> {
        @Override
        protected List<RssItem> doInBackground(String... urls) {
            try {
                // Create RSS reader
                RssReader rssReader = new RssReader(urls[0]);

                // Parse RSS, get items
                return rssReader.getItems();

            } catch (Exception e) {
                Log.e("RssChannelActivity", e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<RssItem> result) {

            // Get a ListView from the RSS Channel view
            ListView itcItems = (ListView) findViewById(R.id.rssChannelListView);

            // Create a list adapter
            ArrayAdapter<RssItem> adapter = new ArrayAdapter<RssItem>(local,android.R.layout.simple_list_item_1, result);
            // Set list adapter for the ListView
            itcItems.setAdapter(adapter);

            // Set list view item click listener
            //itcItems.setOnItemClickListener(new ListListener(result, local));
        }
    }
}
