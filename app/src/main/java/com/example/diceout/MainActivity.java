package com.example.diceout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Field to hold the roll result text
    TextView rollResult;

    // Field to hold the score
    int score;

    // Field to hold the score text
    TextView scoreText;

    // ArrayList to hold all three dice ImageViews
    ArrayList<CardView> cardViews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice(view);
            }
        });

        // Set initial score
        score = 0;

        // Create greeting
        Toast.makeText(getApplicationContext(),"Welcome to DiceOut!", Toast.LENGTH_SHORT).show();

        // Link instances to widgets in the activity view
        rollResult = (TextView) findViewById(R.id.rollResult);
        scoreText = (TextView) findViewById(R.id.scoreText);

        cardViews = new ArrayList<>();

        // Access the dice ImageView widgets
        cardViews.add((CardView) findViewById(R.id.card1Image));
        cardViews.add((CardView) findViewById(R.id.card2Image));
        cardViews.add((CardView) findViewById(R.id.card3Image));

    }

    public void rollDice(View v) {

        for (CardView d : cardViews) {
            d.roll();
        }

        // Build message with the result
        String msg;

        int card1 = cardViews.get(0).getValue();
        int card2 = cardViews.get(1).getValue();
        int card3 = cardViews.get(2).getValue();

        // Run the scoring logic to determine points scored for the roll
        if (card1 == card2 && card1 == card3) {
            // Triples
            int scoreDelta = card1*100;
            msg = "You rolled a triple " + card1 + "! You scored " + scoreDelta + " points!";
            score += scoreDelta;
        } else if (card1 == card2 || card1 == card3 || card2 == card3) {
            // Doubles
            msg = "You rolled doubles for 50 points!";
            score += 50;
        } else {
            msg = "You didn't score this roll. Try again!";
        }

        // Update the app to display the result message
        rollResult.setText(msg);
        scoreText.setText("Score: " + score);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
