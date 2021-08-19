package com.example.denemem.swipeandtabs;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;



public class MainActivity extends AppCompatActivity {

    TabLayout myTab;
    ViewPager myPage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myTab = (TabLayout)findViewById(R.id.myTab1);
        myPage = (ViewPager)findViewById(R.id.myPager1);

        myPage.setAdapter(new MyOwnPagerAdapter(getSupportFragmentManager()));
        myTab.setupWithViewPager(myPage);

        myTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                myPage.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    class MyOwnPagerAdapter extends FragmentPagerAdapter {

        String data[] = {"Step Counter"};

        public MyOwnPagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            if( position == 0) {
                return new StepCounter();
            }
            /*if( position ==1)   {
                return new ---();
            }*/
            return null;
        }

        @Override
        public int getCount() {
            return data.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return data[position];
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount()!=0) {
            getFragmentManager().popBackStackImmediate();
        } else {
            finish();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                getFragmentManager().popBackStackImmediate();
                break;
            case R.id.action_settings:
                startActivity(new Intent(MainActivity.this, Settings.class));
                break;
                
            case R.id.Foods:
                startActivity(new Intent(MainActivity.this, layout_item1.class));

        }

        return true;
    }

}
