package cl.example.evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class Sensores extends AppCompatActivity {

    private SensorManager mgr;
    private TextView textlista;
    Button btsensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores);
        btsensor=(findViewById(R.id.btSensor2));
        Intent intent2 = new Intent(this, Inicio.class);
        mgr =(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        textlista =(TextView) findViewById(R.id.sensorLista);
        List<Sensor> sensorLista= mgr.getSensorList(Sensor.TYPE_ALL);
        StringBuilder strbuilder =new StringBuilder();
        for( Sensor s : sensorLista){
            strbuilder.append(s.getName()+"\n");
        }
        textlista.setVisibility(View.VISIBLE);
        textlista.setText(strbuilder);
        btsensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent2);
            }
        });
    }
}