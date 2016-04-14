package com.example.phamngoctuan.capnhattintucwebkhoa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, JsoupParseCallback, ListItemCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("debug", "Begin Oncreate");
        setContentView(R.layout.activity_main);
        Log.d("debug", "Set Content view");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d("debug", "Set toolbar");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        Log.d("debug", "Toggle");

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Log.d("debug", "OnCreate");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        JsoupParseAsyncTask task = new JsoupParseAsyncTask(this);
        if (id == R.id.tinhoatdong) {
            Log.d("debug", "Tin hoat dong");
            task.execute("http://www.hcmus.edu.vn/index.php?option=com_content&task=blogcategory&id=60&Itemid=1327");
        } else if (id == R.id.tinnguoihoc) {
            task.execute("http://www.hcmus.edu.vn/index.php?option=com_content&task=blogcategory&id=0&Itemid=1322");
        } else {
            task.execute("http://www.hcmus.edu.vn/index.php?option=com_content&task=blogcategory&id=0&Itemid=1323");
        }

        Log.d("debug", "Dong drawer");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        Log.d("debug", "End menu item on click");
        return true;
    }

    @Override
    public void ParseSuccess(ArrayList<HashMap<String, String>> list) {
        ListView listView = (ListView)findViewById(R.id.list_news);
        MyListAdapter adapter = new MyListAdapter(this, R.layout.listitem, list);
        listView.setAdapter(adapter);
        Log.d("debug", "Set adapter");
    }

    @Override
    public void onClickCallback(String link) {
//        setContentView(R.layout.web_content);
//        WebView webView = (WebView)findViewById(R.id.web_content);
//        webView.getSettings().setLoadsImagesAutomatically(true);
//        webView.loadUrl(link);
        Intent intent = new Intent(this, WebContent.class);
        intent.putExtra("link", link);
        startActivity(intent);
    }
}
