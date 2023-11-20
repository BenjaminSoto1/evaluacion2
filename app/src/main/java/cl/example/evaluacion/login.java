package cl.example.evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;


public class login extends AppCompatActivity {

    EditText editTextEmail, editTextPassword;
    Button btnLogin;

    Button btRegistro;

    FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        Intent intent = new Intent(this,Registro.class);

        editTextEmail = findViewById(R.id.editTextTextEmailAddress);
        editTextPassword = findViewById(R.id.editTextTextPassword);
        btnLogin = findViewById(R.id.btLogin);
        btRegistro = findViewById(R.id.btRegistro);


        btRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String email = editTextEmail.getText().toString().trim(); // Obtén el correo y quita espacios en blanco
                String password = editTextPassword.getText().toString().trim(); // Obtén la contraseña y quita espacios en blanco

                if (!email.isEmpty() && !password.isEmpty()) {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Inicio de sesión exitoso
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(login.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                                        // Redirigir al usuario a la actividad MainActivity
                                        Intent intent = new Intent(login.this, Inicio.class);
                                        startActivity(intent);
                                    } else {
                                        // El inicio de sesión falló
                                        Toast.makeText(login.this, "Inicio de sesión fallido", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(login.this, "Por favor, ingresa tu correo y contraseña", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}