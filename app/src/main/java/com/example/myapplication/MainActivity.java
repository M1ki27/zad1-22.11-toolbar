package com.example.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tvMainText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        tvMainText = findViewById(R.id.tvMainText);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(
                    getString(R.string.toolbar_title)
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
            showAboutMessage();
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

    private void showAboutMessage() {
        Toast.makeText(
                this,
                R.string.about_coming_soon,
                Toast.LENGTH_SHORT
        ).show();
    }
}