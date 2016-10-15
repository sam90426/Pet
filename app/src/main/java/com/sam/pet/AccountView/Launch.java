package com.sam.pet.AccountView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.sam.pet.R;

public class Launch extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_launch);

    }
}
