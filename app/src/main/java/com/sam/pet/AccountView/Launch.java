package com.sam.pet.AccountView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.sam.pet.R;
import com.sam.pet.UserView.Index;
import com.sam.pet.Util.PetSQLData;
import com.sam.pet.Util.UserData;

public class Launch extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_launch);

        if(!PetSQLData.GetValue(Launch.this,0).isEmpty()&& Integer.parseInt(PetSQLData.GetValue(Launch.this,0))>0){
            UserData.setUserID(Integer.parseInt(PetSQLData.GetValue(Launch.this,0)));
            startActivity(new Intent().setClass(Launch.this, Index.class));
            finish();
        }else{
            startActivity(new Intent().setClass(Launch.this,Login.class));
            finish();
        }
    }
}
