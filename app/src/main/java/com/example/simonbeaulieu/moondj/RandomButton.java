package com.example.simonbeaulieu.moondj;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

/**
 * Created by vegas on 15/08/17.
 */

public class RandomButton extends android.support.v7.widget.AppCompatImageButton {

    public RandomButton(Context context) {
        super(context);
    }

    public RandomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RandomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void adaptState(boolean b){
        if (b){
            this.setImageResource(R.drawable.random);
        }
        else{
            this.setImageResource(R.drawable.random_deactivated);
        }
    }
}
