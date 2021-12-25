package com.example.api_edwinfarid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public static final String framework_key= "framework";
    ImageView btnReact, btnVue, btnAngularJS, btnLaravel,btnVaadin, btnSpring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inisialisasiView();
    }

    private void inisialisasiView() {

        btnReact = findViewById(R.id.imgReact);
        btnReact.setOnClickListener(view -> tampilkanDataFramework("React"));

        btnVue = findViewById(R.id.imgVue);
        btnVue.setOnClickListener(view -> tampilkanDataFramework("Vue"));

        btnAngularJS = findViewById(R.id.imgAngular);
        btnAngularJS.setOnClickListener(view -> tampilkanDataFramework("AngularJS"));

        btnLaravel = findViewById(R.id.imgLaravel);
        btnLaravel.setOnClickListener(view -> tampilkanDataFramework("Laravel"));

        btnVaadin = findViewById(R.id.imgVaadin);
        btnVaadin.setOnClickListener(view -> tampilkanDataFramework("Vaadin"));

        btnSpring = findViewById(R.id.imgSpring);
        btnSpring.setOnClickListener(view -> tampilkanDataFramework("Spring Framework"));
    }

    private void tampilkanDataFramework(String namaFramework) {
        Log.d("MAIN","Buka activity ");
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(framework_key, namaFramework);
        startActivity(intent);
    }
}