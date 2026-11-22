package com.example.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tvMainText;
    private LinearLayout homeSection;
    private LinearLayout settingsSection;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        tvMainText = findViewById(R.id.tvMainText);
        homeSection = findViewById(R.id.homeSection);
        settingsSection = findViewById(R.id.settingsSection);
        bottomNavigation = findViewById(R.id.bottomNavigation);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(
                    getString(R.string.toolbar_title)
            );
        }

        bottomNavigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.navigation_home) {
                showHomeSection();
                return true;

            } else if (id == R.id.navigation_settings) {
                showSettingsSection();
                return true;
            }

            return false;
        });

        bottomNavigation.setSelectedItemId(
                R.id.navigation_home
        );
    }

    private void showHomeSection() {
        homeSection.setVisibility(View.VISIBLE);
        settingsSection.setVisibility(View.GONE);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(
                    getString(R.string.toolbar_title)
            );
        }
    }

    private void showSettingsSection() {
        homeSection.setVisibility(View.GONE);
        settingsSection.setVisibility(View.VISIBLE);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(
                    getString(R.string.settings_title)
            );
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(
                R.menu.menu_main,
                menu
        );

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_share) {
            shareApplication();

            return true;

        } else if (id == R.id.action_clear) {
            clearScreen();

            return true;

        } else if (id == R.id.action_about) {
            openAbout();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void shareApplication() {
        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.setType("text/plain");

        intent.putExtra(
                Intent.EXTRA_TEXT,
                getString(R.string.share_text)
        );

        try {
            startActivity(
                    Intent.createChooser(
                            intent,
                            getString(R.string.share_title)
                    )
            );

        } catch (ActivityNotFoundException exception) {
            Toast.makeText(
                    this,
                    R.string.no_share_app,
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    private void clearScreen() {
        tvMainText.setText(
                R.string.cleared_text
        );
    }

    private void openAbout() {
        Intent intent = new Intent(
                this,
                AboutActivity.class
        );

        startActivity(intent);
    }
}