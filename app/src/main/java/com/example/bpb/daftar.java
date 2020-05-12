package com.example.bpb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class daftar extends AppCompatActivity {

    RelativeLayout relativeLayout;
    AnimationDrawable animationDrawable;
    private EditText nama, namapengguna, alamat, nohp, katasandi, ketikulangkatasandi;
    String s_nama, s_namapengguna, s_alamat, s_nohp, s_katasandi, s_ketikulangkatasandi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        nama = findViewById(R.id.nama);
        namapengguna = findViewById(R.id.namapengguna);
        alamat = findViewById(R.id.alamat);
        nohp = findViewById(R.id.nohp);
        katasandi = findViewById(R.id.katasandi);
        ketikulangkatasandi = findViewById(R.id.ketikulangkatasandi);

        relativeLayout = (RelativeLayout) findViewById(R.id.tampilandaftar);
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

    public void btnmasuk(View view) {
        final ProgressDialog progressDialog= new ProgressDialog(this);
        progressDialog.setTitle("Harap Tunggu..");



        if (nama.getText().toString().equals("")) {
            Toast.makeText(this,"masukkan nama", Toast.LENGTH_SHORT).show();
        }
        else if (namapengguna.getText().toString().equals("")) {
            Toast.makeText(this,"masukkan namapengguna / email", Toast.LENGTH_SHORT).show();
        }
        else if (alamat.getText().toString().equals("")) {
            Toast.makeText(this,"masukkan alamat", Toast.LENGTH_SHORT).show();
        }
        else if (nohp.getText().toString().equals("")) {
            Toast.makeText(this,"masukkan nohp", Toast.LENGTH_SHORT).show();
        }
        else if (katasandi.getText().toString().equals("")) {
            Toast.makeText(this,"masukkan katasandi", Toast.LENGTH_SHORT).show();
        }
        else if (ketikulangkatasandi.getText().toString().equals("")) {
            Toast.makeText(this,"masukkan ulang katasandi", Toast.LENGTH_SHORT).show();
        }
        else {

            progressDialog.show();
            s_nama = nama.getText().toString().trim();
            s_namapengguna = namapengguna.getText().toString().trim();
            s_alamat = alamat.getText().toString().trim();
            s_nohp = nohp.getText().toString().trim();
            s_katasandi = katasandi.getText().toString().trim();
            s_ketikulangkatasandi = ketikulangkatasandi.getText().toString().trim();

            StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.1.4/bpbd/register.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    progressDialog.dismiss();

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        if (success.equals(1)) {
                            Toast.makeText(daftar.this, "Daftar Berhasil", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(daftar.this, " Daftar Gagal " + e.toString(), Toast.LENGTH_SHORT).show();
                    }

//                    nama.setText("");
//                    namapengguna.setText("");
//                    alamat.setText("");
//                    nohp.setText("");
//                    katasandi.setText("");
//                    ketikulangkatasandi.setText("");
//                    Toast.makeText(daftar.this, response, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(daftar.this, masuk.class));
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    progressDialog.dismiss();


                    Toast.makeText(daftar.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("nama", s_nama);
                    params.put("namapengguna", s_namapengguna);
                    params.put("alamat", s_alamat);
                    params.put("nohp", s_nohp);
                    params.put("katasandi", s_katasandi);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(daftar.this);
            requestQueue.add(request);


        }

    }

}
