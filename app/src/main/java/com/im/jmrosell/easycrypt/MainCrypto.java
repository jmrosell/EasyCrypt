package com.im.jmrosell.easycrypt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

public class MainCrypto extends ActionBarActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 2;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    /*
     * The share action
     */
    private ShareActionProvider mShareActionProvider;

    private Toolbar mToolbar;
    private Button tabEncriptar;
    private Button tabDesencriptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_crypto);

        tabEncriptar = (Button) findViewById(R.id.tabEncriptar);
        tabDesencriptar = (Button) findViewById(R.id.tabDesencriptar);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        if(mToolbar!=null){
            setSupportActionBar(mToolbar);
            getSupportActionBar().setIcon(R.mipmap.ic_launcher);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tabDesencriptar.setActivated(false);
                        tabEncriptar.setActivated(true);
                        break;
                    case 1:
                        tabDesencriptar.setActivated(true);
                        tabEncriptar.setActivated(false);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //Botones tab
        tabDesencriptar.setActivated(false);
        tabEncriptar.setActivated(true);

        tabEncriptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabDesencriptar.setActivated(false);
                tabEncriptar.setActivated(true);
                mPager.setCurrentItem(0);
            }
        });

        tabDesencriptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabDesencriptar.setActivated(true);
                tabEncriptar.setActivated(false);
                mPager.setCurrentItem(1);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new EncriptarFragment();
                case 1:
                    return new DesencriptarFragment();
                default:
                    return new EncriptarFragment();
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_crypto, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.action_settings:
                return true;
            case R.id.menu_item_share:
                TextView resultado = (TextView) findViewById(R.id.tv_resultado);
                EditText pass = (EditText) findViewById(R.id.et_pass);

                if(resultado.getText().toString().equals("")){
                    Toast.makeText(this,"Nada que compartir",Toast.LENGTH_SHORT).show();
                }else {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, resultado.getText().toString() + " - Desencriptar con EasyCrypto [pass: " + pass.getText().toString() + "]");
                    sendIntent.setType("text/plain");
                    sendIntent.setPackage("com.whatsapp");
                    startActivity(sendIntent);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
