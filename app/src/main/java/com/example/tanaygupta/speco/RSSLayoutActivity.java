package com.example.tanaygupta.speco;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class RSSLayoutActivity extends TabActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rss_layout);
        TabHost tabHost = getTabHost();

        Intent artIntent = new Intent().setClass(this, ChannelActivity.class);
        // Set Art category RSS URL
        artIntent.putExtra("rss-url", "http://feeds.reuters.com/news/artsculture?format=xml");

        // The name of the art tab taken from the String resources
        String artTabName = getResources().getString(R.string.tab_art);
        TabHost.TabSpec artTabSpec = tabHost.newTabSpec(artTabName)
                .setIndicator(artTabName, getResources().getDrawable(R.drawable.rss_tab_art))
                .setContent(artIntent);
        // Add art tab to the TabHost
        tabHost.addTab(artTabSpec);

        /* *****************
         * Tech tab
         */
        Intent techIntent = new Intent().setClass(this, ChannelActivity.class);
        // Set Tech category RSS URL
        techIntent.putExtra("rss-url", "http://feeds.reuters.com/reuters/technologyNews?format=xml");

        // Tech tab name taken from the string resources
        String techTabName = getResources().getString(R.string.tab_tech);
        TabHost.TabSpec techTabSpec = tabHost.newTabSpec(techTabName)
                .setIndicator(techTabName, getResources().getDrawable(R.drawable.rss_tab_tech))
                .setContent(techIntent);
        // Add tech tab to the TabHost
        tabHost.addTab(techTabSpec);


        /* *****************
         * Sports tab
         */
        Intent sportsIntent = new Intent().setClass(this, ChannelActivity.class);
        // Set Sports category RSS URL
        sportsIntent.putExtra("rss-url", "http://feeds.reuters.com/reuters/sportsNews?format=xml");

        // Sports tab name - string resources
        String sportsTabName = getResources().getString(R.string.tab_sports);
        TabHost.TabSpec sportsTabSpec = tabHost.newTabSpec(sportsTabName)
                .setIndicator(sportsTabName, getResources().getDrawable(R.drawable.rss_tab_sports))
                .setContent(sportsIntent);
        // Add sports tab to the TabHost
        tabHost.addTab(sportsTabSpec);


        // Set current tab to Technology
        tabHost.setCurrentTab(1);
    }
}
