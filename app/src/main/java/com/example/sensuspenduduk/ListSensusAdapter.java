package com.example.sensuspenduduk;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ListSensusAdapter extends RecyclerView.Adapter<ListSensusAdapter.ListSensusViewHolder> {
    private ArrayList<Sensus> list;
    private FirebaseFirestore db;

    public ListSensusAdapter(ArrayList<Sensus> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ListSensusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_sensus, parent, false);
        return new ListSensusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListSensusViewHolder holder, int position) {
        final Sensus sensus = list.get(position);
        String loc = sensus.getKelurahan() + ", " + sensus.getKecamatan() + ", " + sensus.getKota() + ", " + sensus.getProvinsi();

        db = FirebaseFirestore.getInstance();

        holder.location.setText(loc);
        holder.kepalaKeluarga.setText(sensus.getKepala_keluarga().toString());
        holder.penduduk.setText(sensus.getPenduduk().toString());
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSensus(v, sensus);
                System.out.println("Edit ke-" + sensus.getKota());
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Delete ke-" + sensus.getKota());
            }
        });
    }

    @Override
    public int getItemCount() {
        return (list != null) ? list.size() : 0;
    }

    public static class ListSensusViewHolder extends RecyclerView.ViewHolder {
        public TextView location, kepalaKeluarga, penduduk;
        public Button btnEdit, btnDelete;
        public ListSensusViewHolder(View view) {
            super(view);
            location = (TextView) view.findViewById(R.id.location);
            kepalaKeluarga = (TextView) view.findViewById(R.id.kepala_keluarga);
            penduduk = (TextView) view.findViewById(R.id.penduduk);
            btnEdit = (Button) view.findViewById(R.id.btnEdit);
            btnDelete = (Button) view.findViewById(R.id.btnDelete);
        }
    }

    protected void changeSensus(View view, Sensus sensus) {
        Intent intent = new Intent(view.getContext(), EditSensus.class);
        Bundle bundle = new Bundle();
        bundle.putString("provinsi", sensus.getProvinsi());
        bundle.putString("kota[", sensus.getKota());
        bundle.putString("kecamatan", sensus.getKecamatan());
        bundle.putString("kelurahan", sensus.getKelurahan());
        bundle.putInt("rt", sensus.getRt());
        bundle.putInt("rw", sensus.getRw());
        bundle.putInt("kepala_keluarga", sensus.getKepala_keluarga());
        bundle.putInt("penduduk", sensus.getPenduduk());
        intent.putExtras(bundle);
    }
}
