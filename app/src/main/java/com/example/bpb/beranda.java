package com.example.bpb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

public class beranda extends AppCompatActivity {

    ViewFlipper v_flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);
    }

    public void akun(View view) {
        Intent intent = new Intent(beranda.this, akun.class);
        startActivity(intent);
    }

    public void dokumentasi(View view) {
        Intent intent = new Intent(beranda.this, dokumentasi.class);
        startActivity(intent);
    }

    public void inputbantuan(View view) {
        Intent intent = new Intent(beranda.this, masuk.class);
        startActivity(intent);
    }

    public void about(View view) {
        Intent intent = new Intent(beranda.this, tentang.class);
        startActivity(intent);
    }
}
