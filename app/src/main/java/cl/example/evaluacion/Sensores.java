package cl.example.evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class Sensores extends AppCompatActivity {

    private SensorManager mgr;
    private TextView textlista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores);
        mgr =(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        textlista =(TextView) findViewById(R.id.sensorLista);
        List<Sensor> sensorLista= mgr.getSensorList(Sensor.TYPE_ALL);
        StringBuilder strbuilder =new StringBuilder();
        for( Sensor s : sensorLista){
            strbuilder.append(s.getName()+"\n");
        }
        textlista.setVisibility(View.VISIBLE);
        textlista.setText(strbuilder);
    }
}