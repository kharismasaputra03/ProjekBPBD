package com.example.bpb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class masuk extends AppCompatActivity {
    private EditText namapengguna, katasandi;
    private Button lupakatasandi, daftar;
    String s_namapengguna, s_katasandi;


    RelativeLayout relativeLayout;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk);

        namapengguna = findViewById(R.id.namapengguna);
        katasandi = findViewById(R.id.katasandi);
        lupakatasandi = findViewById(R.id.lupakatasandi);
        daftar = findViewById(R.id.daftar);

        lupakatasandi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(masuk.this, beranda.class));
            }
        });

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(masuk.this, daftar.class));
            }
        });

        relativeLayout = (RelativeLayout) findViewById(R.id.tampilanmasuk);
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

    public void btnberanda(View view) {

        if (namapengguna.getText().toString().equals("")) {
            Toast.makeText(this,"masukkan nama pengguna / email", Toast.LENGTH_SHORT).show();
        }
        else if (katasandi.getText().toString().equals("")) {
            Toast.makeText(this,"masukkan katasandi", Toast.LENGTH_SHORT).show();
        }
        else {

            final ProgressDialog progressDialog= new ProgressDialog(this);
            progressDialog.setTitle("Harap Tunggu..");

            progressDialog.show();
            s_namapengguna = namapengguna.getText().toString().trim();
            s_katasandi = katasandi.getText().toString().trim();


            StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.1.4/bpbd/masuk.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();

                    if(response.equalsIgnoreCase("success")) {
                        namapengguna.setText("nnnn");
                        katasandi.setText("kkkk");
                        startActivity(new Intent(getApplicationContext(), bantuanbencana.class));
                        Toast.makeText(masuk.this, response, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(masuk.this, response, Toast.LENGTH_SHORT).show();
                    }

//                    try {
//                        JSONObject jsonObject = new JSONObject(response);
//                        String success = jsonObject.getString("success");
//                        if (success.equals(1)) {
//                            Toast.makeText(masuk.this, "Daftar Berhasil", Toast.LENGTH_SHORT).show();
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        Toast.makeText(masuk.this, " Daftar Gagal " + e.toString(), Toast.LENGTH_SHORT).show();
//
//                    }

//                    namapengguna.setText("");
//                    katasandi.setText("");
//                    Toast.makeText(daftar.this, response, Toast.LENGTH_SHORT).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    progressDialog.dismiss();


                    Toast.makeText(masuk.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("namapengguna", s_namapengguna);
                    params.put("katasandi", s_katasandi);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(masuk.this);
            requestQueue.add(request);

        }

    }

}
