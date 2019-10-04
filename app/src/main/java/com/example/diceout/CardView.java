package com.example.diceout;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CardView extends android.support.v7.widget.AppCompatImageView {

    private int value;
    private Context context;
    private List<String> cards = new ArrayList<>();

    String[] ranks = {
            "ace","two","three","four","five", "six","seven", "eight", "nine", "ten", "jack",
            "queen","king"
    };

    String[] suits = {

            "spades", "clubs", "diamonds", "hearts"
    };

    public CardView(Context context) {
        super(context);
        init(context);
    }

    public CardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
//        setImageResource(R.drawable.die_1);
        setImageResource(R.drawable.black_joker);
        this.context = context;
    }

    public void roll() {

        value = (int) (Math.random() * 51);

        for(int i=0;i<suits.length;i++){
            for(int j=0;j<ranks.length;j++){
                cards.add(ranks[j]+"_of_"+suits[i]);
            }
        }

        Collections.shuffle(cards);

        Log.d("card",""+cards);

        try {
            InputStream stream = context.getAssets().open(cards.get(value)+".png");
            Drawable d = Drawable.createFromStream(stream,null);
            setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getValue() {
        return value;
    }
}
