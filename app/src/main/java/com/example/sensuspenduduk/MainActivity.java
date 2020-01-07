package com.example.sensuspenduduk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    private EditText email, password;
    private Button submit;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        submit = findViewById(R.id.btnLogin);
        db = FirebaseFirestore.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em = email.getText().toString();
                String pass = password.getText().toString();
                db.collection("sensus_user")
                        .whereEqualTo("email", em)
                        .whereEqualTo("password", pass)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                Context context = getApplicationContext();
                                CharSequence msg = "Selamat datang";
                                Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.TOP, 0, 0);
                                if (!task.isSuccessful()) {
                                    msg = "Maaf data anda tidak terdaftar";
                                    toast.setText(msg);
                                    toast.show();
                                } else {
                                    toast.show();
                                    login();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Context context = getApplicationContext();
                                CharSequence msg = "Maaf ada kesalahan teknis silahkan mencoba lagi";
                                Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.TOP, 0, 0);
                                toast.show();
                            }
                        });
            }
        });
    }

    protected void login() {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}
