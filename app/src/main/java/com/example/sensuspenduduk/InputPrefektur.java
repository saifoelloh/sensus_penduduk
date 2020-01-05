package com.example.sensuspenduduk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputPrefektur extends AppCompatActivity {

    private EditText provinsi, kota, kecamatan, kelurahan, kepala_keluarga, penduduk;
    private Button submit, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_prefektur);

        submit = findViewById(R.id.btnInputPrefektur);
        cancel = findViewById(R.id.btnCancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHome();
            }
        });
    }

    protected void backToHome() {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}
