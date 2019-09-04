package com.example.diceout;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import java.io.IOException;
import java.io.InputStream;

public class DieView extends android.support.v7.widget.AppCompatImageView {

    private int value;
    private Context context;

    public DieView(Context context) {
        super(context);
        init(context);
    }

    public DieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setImageResource(R.drawable.die_1);
        this.context = context;
    }

    public void roll() {
        value = (int) (Math.random() * 6);
        try {
            InputStream stream = context.getAssets().open("die_"+value+".png");
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
