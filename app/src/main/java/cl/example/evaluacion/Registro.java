package cl.example.evaluacion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Registro extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore Firestore;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button btnRegister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.editTextTextEmailAddress2);
        editTextPassword = findViewById(R.id.editTextTextPassword2);
        btnRegister = findViewById(R.id.btLogin2);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Firestore = FirebaseFirestore.getInstance();
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Registro.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Registro.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        registrouser(email, password);
                                        Toast.makeText(Registro.this, "Registro de usuario exitoso", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Registro.this, Inicio.class);
                                        startActivity(intent);
                                    } else {
                                        //si falla el registro
                                        Toast.makeText(Registro.this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }

            private void registrouser(String email, String password) {
                String id = mAuth.getCurrentUser().getUid();
                Map<String, Object> map = new HashMap<>();
                map.put("id", id);
                map.put("email", email);
                map.put("password", password);

                Firestore.collection("user").document(id).set(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                finish();
                                startActivity(new Intent(Registro.this, login.class));
                                Toast.makeText(Registro.this, "Usuario registrado", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Registro.this, "Error al guardar", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}