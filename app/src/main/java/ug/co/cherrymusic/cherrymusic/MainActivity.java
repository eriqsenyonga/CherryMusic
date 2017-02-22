package ug.co.cherrymusic.cherrymusic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawer;
    FragmentManager fm;
    SharedPreferences mPositionSavedPrefs;
    SharedPreferences.Editor posSavedEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        fm = getSupportFragmentManager();

        mPositionSavedPrefs = getSharedPreferences("mPositionSaved",
                Context.MODE_PRIVATE);
        posSavedEditor = mPositionSavedPrefs.edit();

        if (getIntent().hasExtra("beginning")) {

            fm.beginTransaction().replace(R.id.contentMain, new HomeFragment()).commit();
            drawer.openDrawer(GravityCompat.START);
            posSavedEditor.putInt("last_main_position", R.id.nav_home).apply();
            getSupportActionBar().setTitle("Home");

        } else {

            Fragment fragment = null;
            CharSequence title = null;

            int id = mPositionSavedPrefs.getInt(
                    "last_main_position", 1);

            if (id == R.id.nav_home) {
                fragment = new HomeFragment();
                title = "Home";
            }

            if (id == R.id.nav_music) {
                fragment = new MusicFragment();
                title = "Music";
            }

            if (id == R.id.nav_books) {
                fragment = new BooksFragment();
                title = "Books";
            }

            if (id == R.id.nav_news) {
                fragment = new NewsFragment();
                title = "News";
            }

            if (id == R.id.nav_sermons) {
                fragment = new SermonsFragment();
                title = "Sermons";
            }

            if (id == R.id.nav_podcasts) {
                fragment = new PodcastsFragment();
                title = "Podcasts";
            }

            if (fragment != null) {

                fm.beginTransaction().replace(R.id.contentMain, fragment).commit();
                getSupportActionBar().setTitle(title);

                drawer.closeDrawer(GravityCompat.START);

            }
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.nav_home) {
            fragment = new HomeFragment();
        }

        if (id == R.id.nav_music) {
            fragment = new MusicFragment();
        }

        if (id == R.id.nav_books) {
            fragment = new BooksFragment();
        }

        if (id == R.id.nav_news) {
            fragment = new NewsFragment();
        }

        if (id == R.id.nav_sermons) {
            fragment = new SermonsFragment();
        }

        if (id == R.id.nav_podcasts) {
            fragment = new PodcastsFragment();
        }

        if(id == R.id.nav_contact_us){



          //  fragment = new AboutUsFragment();
        }

        if (fragment != null) {

            fm.beginTransaction().replace(R.id.contentMain, fragment).commit();
            getSupportActionBar().setTitle(item.getTitle());
         //   posSavedEditor.putInt("last_main_position", id).apply();
            drawer.closeDrawer(GravityCompat.START);
            item.setChecked(true);
        }


        return true;

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
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);


        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Toast.makeText(MainActivity.this, query, Toast.LENGTH_LONG).show();

                //   ((PapListLocalAdapter) localPapList.getAdapter()).setFilter(query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (newText.isEmpty()) {
                    //      ((PapListLocalAdapter) localPapList.getAdapter()).flushFilter();
                } else {

                    //    ((PapListLocalAdapter) localPapList.getAdapter()).setFilter(newText);

                }

                return true;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                //   ((PapListLocalAdapter) localPapList.getAdapter()).flushFilter();

                return true;
            }
        });

        //    searchView.setSearchableInfo(
        //          searchManager.getSearchableInfo(getActivity().getComponentName()));


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
}
