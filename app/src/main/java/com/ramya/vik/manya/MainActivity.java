package com.ramya.vik.manya;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new OneFragment(), "Indian Comedy");
        adapter.addFrag(new ThreeFragment(), "Bests Of Kapil");
        adapter.addFrag(new Threefragment2(), "Bhabhiji Ghar pe Hai");
        adapter.addFrag(new TwoFragment(), "BB ki Vines");
        adapter.addFrag(new Twofragment2(), "WowSome Videos");

        viewPager.setAdapter(adapter);
    }
private void setupTabIcons()
{

    TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tabs, null);
    tabOne.setText("Indian Comedy");
    tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.lol, 0, 0);
    tabLayout.getTabAt(0).setCustomView(tabOne);

    TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tabs, null);
    tabTwo.setText("Bests Of Kapil");
    tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.frog, 0, 0);
    tabLayout.getTabAt(1).setCustomView(tabTwo);

    TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tabs, null);
    tabThree.setText("Bhabhiji Ghar pe Hai");
    tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.cool, 0, 0);
    tabLayout.getTabAt(2).setCustomView(tabThree);
    TextView tabfour = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tabs, null);
    tabfour.setText("BBkiVines");
    tabfour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.frog, 0, 0);
    tabLayout.getTabAt(3).setCustomView(tabfour);
    TextView tabfive = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tabs, null);
    tabfive.setText("WowSome Videos");
    tabfive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.woe, 0, 0);
    tabLayout.getTabAt(4).setCustomView(tabfive);

}
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.acio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
if(id==R.id.action_addme)        //noinspection SimplifiableIfStatement
{
    startActivity(new Intent(MainActivity.this,Oneactivity.class));
}
        else if(id==R.id.notifi){
            startActivity(new Intent(MainActivity.this,Notify.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
          if (id == R.id.nav_sea) {
            startActivity(new Intent(MainActivity.this,Notify.class));

        }
        else if (id == R.id.nav_sen) {
            startActivity(new Intent(MainActivity.this,Aboutus.class));

        }
          else if (id == R.id.nav_seas) {
              startActivity(new Intent(MainActivity.this,Beingidian.class));

          }
          else if (id == R.id.nav_share) {
              Intent i=new Intent(Intent.ACTION_SEND);
              i.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id=com.ramjana.vik.manya");
              i.setType("text/plain");
              startActivity(Intent.createChooser(i,"Share App using"));
          }
        else if (id == R.id.nav_sen1) {


            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("https://www.facebook.com/Vikas-bajpayee-1615217582079629/?fref=ts"));
            startActivity(intent);


        }
        else if(id==R.id.nav_sead){
              startActivity(new Intent(MainActivity.this,MostViewed.class));

          }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
