package cl.example.evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;


import cl.example.evaluacion.Clases.brotherhood;

public class Inicio extends AppCompatActivity {


    Button btBrother, btJJbarber, btArauco,btLantañoo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Button btSalir = findViewById(R.id.btSalir);

        btSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cierra la actividad actual y, opcionalmente, la aplicación
                finish();
                System.exit(0); // Cierra la aplicación por completo (opcional)
            }
        });

        btLantañoo=findViewById(R.id.BtLantaño);
        btArauco=findViewById(R.id.BtArauco);
        btBrother=findViewById(R.id.BtBrother);
        btJJbarber=findViewById(R.id.BtJJBarbers);

        Intent intent = new Intent(this, barberhouse.class);
        Intent intent2 = new Intent(this, jjbarbers.class);
        Intent intent3 = new Intent(this, brotherhood.class);
        Intent intent4 = new Intent(this, araucobarber.class);
        Intent intent5 = new Intent(this, jjbarbers.class);

        btLantañoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        btJJbarber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent2);
            }
        });
        btBrother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent3);
            }
        });
        btArauco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent4);
            }
        });
        btJJbarber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent5);
            }
        });



    }
}