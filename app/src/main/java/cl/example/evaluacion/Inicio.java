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

    Button btBrother, btJJbarber, btArauco,btLantañoo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        btLantañoo=findViewById(R.id.BtLantaño);
        btArauco=findViewById(R.id.BtArauco);
        btBrother=findViewById(R.id.BtBrother);
        btJJbarber=findViewById(R.id.BtJJBarbers);
        Intent intent = new Intent(this,MainActivity.class);
        Intent intent2 = new Intent(this, Mymaps.class);
        Intent intent3 = new Intent(this, brotherhood.class);

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



    }
}