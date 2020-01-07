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
import java.util.List;

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

        recyclerView = findViewById(R.id.sensus_recyler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListSensusAdapter(list);
        recyclerView.setAdapter(mAdapter);
    }

    protected void fetchData() {
        db.collection("sensus_penduduk")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Toast toast = Toast.makeText(getBaseContext(), "Maaf Data Kosong", Toast.LENGTH_LONG);
                        if (queryDocumentSnapshots.isEmpty()) {
                            toast.show();
                        } else {
                            toast.setText("Sukses ambil data");
                            toast.show();
                        }
                    }
                });
    }
}
