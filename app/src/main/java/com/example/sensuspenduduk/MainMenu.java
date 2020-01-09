package com.example.sensuspenduduk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    private Button list, tambah, rekap, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        list = findViewById(R.id.btnList);
        tambah = findViewById(R.id.btnTambah);
        rekap = findViewById(R.id.btnRekap);
        logout = findViewById(R.id.btnLogout);

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list();
            }
        });

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambah();
            }
        });

        rekap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rekap();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    public void list() {
        Intent intent = new Intent(this, ListSensus.class);
        startActivity(intent);
    }

    public void tambah() {
        Intent intent = new Intent(this, InputPrefektur.class);
        intent.putExtra("admin", this.getIntent().getStringExtra("admin"));
        startActivity(intent);
    }

    public void rekap() {
        Intent intent = new Intent(this, ReportActivity.class);
        startActivity(intent);
    }

    public void logout() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
