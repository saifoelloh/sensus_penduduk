package com.example.sensuspenduduk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ListSensus extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Sensus> list;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sensus);

        list = new ArrayList<>();
        db = FirebaseFirestore.getInstance();

        fetchData();
    }

    protected void fetchData() {
        db.collection("sensus_penduduk")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Data Kosong", Toast.LENGTH_LONG);
                        if (!task.isSuccessful()) {
                            toast.show();
                        } else {
                            for (DocumentSnapshot doc: task.getResult().getDocuments()) {
                                Sensus sensus = new Sensus();
                                sensus.setProvinsi(doc.get("provinsi").toString());
                                sensus.setKota(doc.get("kota").toString());
                                sensus.setKecamatan(doc.get("kecamatan").toString());
                                sensus.setKelurahan(doc.get("kelurahan").toString());
                                sensus.setRt(Integer.parseInt(doc.get("rt").toString()));
                                sensus.setRw(Integer.parseInt(doc.get("rw").toString()));
                                sensus.setKepala_keluarga(Integer.parseInt(doc.get("kepala_keluarga").toString()));
                                sensus.setPenduduk(Integer.parseInt(doc.get("penduduk").toString()));
                                list.add(sensus);
                                System.out.println("Panjang list = " + list.size());
                            }
                            recyclerView = findViewById(R.id.sensus_recyler_view);
                            layoutManager = new LinearLayoutManager(getApplicationContext());
                            mAdapter = new ListSensusAdapter(list);
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(mAdapter);
                        }
                    }
                });
    }
}
