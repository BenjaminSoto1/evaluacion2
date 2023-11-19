package cl.example.evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cl.example.evaluacion.Clases.Mymaps;
import cl.example.evaluacion.Clases.brotherhood;

public class Inicio extends AppCompatActivity {

    Button idinventario, idmapa, idsensores;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        idinventario=findViewById(R.id.idInventario);
        idmapa=findViewById(R.id.idMapa);
        idsensores=findViewById(R.id.idSensores);
        Intent intent = new Intent(this,MainActivity.class);
        Intent intent2 = new Intent(this, Mymaps.class);
        Intent intent3 = new Intent(this, brotherhood.class);

        idinventario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        idmapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent2);
            }
        });
        idsensores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent3);
            }
        });



    }
}