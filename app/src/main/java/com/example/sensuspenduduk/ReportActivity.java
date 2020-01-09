package com.example.sensuspenduduk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReportActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private TextView tvTot, tvPen, tvKk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        tvTot = findViewById(R.id.total);
        tvPen = findViewById(R.id.vilager);
        tvKk = findViewById(R.id.kk);
        db = FirebaseFirestore.getInstance();
        db.collection("sensus_penduduk")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<DocumentSnapshot> snapshots = task.getResult().getDocuments();
                        Toast toast = Toast.makeText(getApplicationContext(), "Data Kosong", Toast.LENGTH_LONG);
                        if (!(task.getResult().getDocuments().size() > 0)) {
                            toast.show();
                        } else {
                            Integer kk, penduduk, tot;
                            tot = snapshots.size();
                            kk = 0;
                            penduduk = 0;
                            for (DocumentSnapshot snapshot: snapshots) {
                                kk += Integer.parseInt(snapshot.get("kepala_keluarga").toString());
                                penduduk += Integer.parseInt(snapshot.get("penduduk").toString());
                            }
                            tvTot.setText(tot.toString());
                            tvKk.setText(kk.toString());
                            tvPen.setText(penduduk.toString());
                        }
                    }
                });
    }
}
