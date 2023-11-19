package cl.example.evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cl.example.evaluacion.Clases.Mymaps;

public class Inicio extends AppCompatActivity {

    Button idinventario, idmapa;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        idinventario=findViewById(R.id.idInventario);
        idmapa=findViewById(R.id.idMapa);

        Intent intent = new Intent(this,MainActivity.class);
        Intent intent2 = new Intent(this, Mymaps.class);


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





    }
}