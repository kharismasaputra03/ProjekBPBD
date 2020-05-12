package com.example.bpb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class bantuanbencana extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bantuanbencana);
    }
    public void kirim(View view) {
        Intent intent = new Intent(bantuanbencana.this, beranda.class);
        startActivity(intent);
    }
}
