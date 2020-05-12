package com.example.bpb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class kodekeamanan extends AppCompatActivity {

    RelativeLayout relativeLayout;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kodekeamanan);
        relativeLayout = (RelativeLayout) findViewById(R.id.idkodekeamanan);
        animationDrawable = (AnimationDrawable) relativeLayout.getBackground();

        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);
    }
    @Override
    protected void onPause() {
        super.onPause();

        if (animationDrawable != null && animationDrawable.isRunning()){
            animationDrawable.stop();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (animationDrawable != null && !animationDrawable.isRunning()){
            animationDrawable.start();
        }
    }
    public void kembali(View view) {
        Intent intent = new Intent(kodekeamanan.this, pemulihankatasandi.class);
        startActivity(intent);
    }

    public void selanjutnya(View view) {
        Intent intent = new Intent(kodekeamanan.this, bantuanbencana.class);
        startActivity(intent);
    }
}
