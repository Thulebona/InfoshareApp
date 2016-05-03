package cput.ac.za.infoshare.views;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import cput.ac.za.infoshare.R;
import cput.ac.za.infoshare.views.fragments.ContentFragment;
import cput.ac.za.infoshare.views.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
     Toolbar toolbar;
     DrawerLayout drawerLayout;
     ActionBarDrawerToggle actionBarDrawerToggle;
     FragmentTransaction fragmentTransaction;
     NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //drawerLayout
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //first fragment
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container,new ContentFragment());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Published Content");

        //NavigationView
         navigationView = (NavigationView) findViewById(R.id.nav_view);
         navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    protected void onPostCreate( Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_content:
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container,new ContentFragment());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle("Published Content");
                item.setChecked(true);
                break;
             case R.id.nav_profile:
                 fragmentTransaction = getSupportFragmentManager().beginTransaction();
                 fragmentTransaction.replace(R.id.main_container,new ProfileFragment());
                 fragmentTransaction.commit();
                 getSupportActionBar().setTitle("Your Profile");
                 item.setChecked(true);
                break;
             case R.id.nav_manage:
                break;
             case R.id.nav_share:
                break;
             case R.id.nav_send:
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }




}
