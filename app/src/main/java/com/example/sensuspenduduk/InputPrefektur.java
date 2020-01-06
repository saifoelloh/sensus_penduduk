package com.example.sensuspenduduk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class InputPrefektur extends AppCompatActivity {

    private EditText provinsi, kota, kecamatan, kelurahan, rt, rw, kepala_keluarga, penduduk;
    private Button submit, cancel;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_prefektur);

        provinsi = findViewById(R.id.provinsi);
        kota = findViewById(R.id.kota);
        kecamatan = findViewById(R.id.kecamatan);
        kelurahan = findViewById(R.id.kelurahan);
        rt = findViewById(R.id.rt);
        rw = findViewById(R.id.rw);
        kepala_keluarga = findViewById(R.id.kepala_keluarga);
        penduduk = findViewById(R.id.penduduk);

        submit = findViewById(R.id.btnInputPrefektur);
        cancel = findViewById(R.id.btnCancel);

        db = FirebaseFirestore.getInstance();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHome();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNewData();
            }
        });
    }

    protected void inputNewData() {
        String inProvinsi, inKota, inKecamatan, inKelurahan;
        Integer inRt, inRw, inKepala_keluarga, inPenduduk;

        inProvinsi = provinsi.getText().toString();
        inKota = kota.getText().toString();
        inKecamatan = kecamatan.getText().toString();
        inKelurahan = kelurahan.getText().toString();
        inRt = Integer.parseInt(rt.getText().toString());
        inRw = Integer.parseInt(rw.getText().toString());
        inKepala_keluarga = Integer.parseInt(kepala_keluarga.getText().toString());
        inPenduduk = Integer.parseInt(penduduk.getText().toString());

        final Map<String, Object> sensus = new HashMap<>();
        sensus.put("provinsi", inProvinsi);
        sensus.put("kota", inKota);
        sensus.put("kecamatan", inKecamatan);
        sensus.put("kelurahan", inKelurahan);
        sensus.put("rt", inRt);
        sensus.put("rw", inRw);
        sensus.put("kepala_keluarga", inKepala_keluarga);
        sensus.put("penduduk", inPenduduk);

        System.out.println("Mulai Mamen");
        System.out.println(sensus);
        db.collection("sensus_penduduk")
                .whereEqualTo("provinsi", inProvinsi)
                .whereEqualTo("kota", inKota)
                .whereEqualTo("kecamatan", inKecamatan)
                .whereEqualTo("kelurahan", inKelurahan)
                .whereEqualTo("rt", inRt)
                .whereEqualTo("rw", inRw)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        QuerySnapshot snapshot = task.getResult();
                        System.out.println("Masuk");
                        System.out.println("Hasil: " + snapshot.getDocuments().size());
                        if (snapshot.getDocuments().size() > 0) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Duplikasi data ditemukan", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.TOP, 0, 0);
                            toast.show();
                        } else {
                            System.out.println("Masuk sini GAN");
                            db.collection("sensus_penduduk")
                                    .add(sensus)
                                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentReference> task) {
                                            Toast toast = Toast.makeText(getApplicationContext(), "Sukses membuat data baru", Toast.LENGTH_LONG);
                                            toast.setGravity(Gravity.TOP, 0, 0);
                                            if (task.isSuccessful()) {
                                                toast.show();
                                                backToHome();
                                            } else {
                                                toast.setText("Gagal menyimpan data");
                                                toast.show();
                                            }
                                        }
                                    });
                        }
                    }
                });
    }

    protected void backToHome() {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}
