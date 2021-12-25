package com.example.api_edwinfarid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {

    TextView txtNamaFramework,txtDeskripsi, txtAuthor, txtWebsite;
    ImageView imgLogo;
    ImageButton btnHome;
    ProgressBar loadingIndicator;
    String namaFramework;
    JSONObject dataTerbaru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        inisialisasiView();
        ambilDataFramework();
    }

    private void inisialisasiView() {
        txtAuthor = findViewById(R.id.txtAuthor);
        txtWebsite = findViewById(R.id.txtWebsite);
        txtDeskripsi = findViewById(R.id.txtDeskripsi);
        txtNamaFramework = findViewById(R.id.txtNamaFramework);
        imgLogo = (ImageView)findViewById(R.id.imgLogo);
        loadingIndicator = findViewById(R.id.loadingIndicator);

        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(view -> kembali());
    }

    private void kembali() {
        Log.d("MAIN","Buka activity ");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void ambilDataFramework() {
        loadingIndicator.setVisibility(View.VISIBLE);
        String baseURL = "https://ewinsutriandi.github.io/mockapi/web_framework.json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, baseURL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("MAIN",response.toString());
                        dataTerbaru = response;
                        Intent intent = getIntent();
                        namaFramework = intent.getStringExtra(MainActivity.framework_key);
                        tampilkanDataFramework(namaFramework);
                        loadingIndicator.setVisibility(View.INVISIBLE);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loadingIndicator.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(),"Gagal mengambil data",Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
    }

    private void tampilkanDataFramework(String namaFramework) {
        this.namaFramework = namaFramework;
        txtNamaFramework.setText(namaFramework);
        try { // try catch untuk antisipasi error saat parsing JSON
            JSONObject data = dataTerbaru.getJSONObject(namaFramework);
            txtAuthor.setText(data.getString("original_author"));
            txtWebsite.setText(data.getString("official_website"));
            txtDeskripsi.setText(data.getString("description"));
            Glide.with(DetailActivity.this)
                    .load(data.getString("logo"))
                    .placeholder(R.drawable.react)
                    .error(R.drawable.ic_launcher_background)
                    .into(imgLogo);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}