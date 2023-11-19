package cl.example.evaluacion.Clases;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import cl.example.evaluacion.Inicio;
import cl.example.evaluacion.R;

public class Mymaps extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerClickListener {

    public static class CustomInfo {
        private int imageResId;
        private String infoText;

        public CustomInfo(int imageResId, String infoText) {
            this.imageResId = imageResId;
            this.infoText = infoText;
        }

        public int getImageResId() {
            return imageResId;
        }

        public String getInfoText() {
            return infoText;
        }
    }
    EditText txtLatitud, txtLongitud;
    Button btMenu;
    GoogleMap mMap;

    // Lista para almacenar los marcadores
    List<Marker> marcadores = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymaps);

        btMenu = findViewById(R.id.btMenu);
        txtLatitud = findViewById(R.id.txtLatitud);
        txtLongitud = findViewById(R.id.txtLongitud);
        Intent intent = new Intent(this, Inicio.class);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);

        btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Configura el adaptador para el InfoWindow
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null; // Usa la ventana de información predeterminada
            }

            @Override
            public View getInfoContents(Marker marker) {
                // Infla el diseño personalizado
                View infoWindow = getLayoutInflater().inflate(R.layout.custom_info_window, null);

                // Obtén las vistas del diseño
                ImageView infoImage = infoWindow.findViewById(R.id.infoImage);
                TextView infoTextView = infoWindow.findViewById(R.id.infoTextView);

                // Obtén la información almacenada en setTag
                Object tag = marker.getTag();

                // Muestra la información en el InfoWindow personalizado
                if (tag != null && tag instanceof CustomInfo) {
                    CustomInfo customInfo = (CustomInfo) tag;

                    // Actualiza las vistas con la información personalizada
                    infoImage.setImageResource(customInfo.getImageResId());
                    infoTextView.setText(customInfo.getInfoText());
                }

                return infoWindow;
            }
        });

        // Aquí puedes agregar marcadores al mapa

        // Marcador para la tienda
        LatLng tienda = new LatLng(-36.5900806, -72.0821342);
        Marker tiendaMarker = mMap.addMarker(new MarkerOptions().position(tienda).title("Tienda"));
        tiendaMarker.setTag(new CustomInfo(R.drawable.pngegg, "Información de la tienda"));

        // Otros marcadores
        LatLng marker1 = new LatLng(-36.6064595, -72.1054738);
        Marker marker1Marker = mMap.addMarker(new MarkerOptions().position(marker1).title("broderjud").snippet("BARBERIA ASDFJKMASFMSAKFMSKAMFKDSM"));
        marker1Marker.setTag(new CustomInfo(R.drawable.a9409281fa230ecd40b7f6e5f0594159, "Información de broderjud"));

        LatLng marker2 = new LatLng(-36.6057102, -72.0944875);
        Marker marker2Marker = mMap.addMarker(new MarkerOptions().position(marker2).title("barber desenden").snippet("CORTES DEGRADESSSSSSS"));
        marker2Marker.setTag(new CustomInfo(R.drawable.sensor, "Información de barber desenden"));

        // Agrega los marcadores a la lista si es necesario (si necesitas llevar un registro)
        // marcadores.add(tiendaMarker);
        // marcadores.add(marker1Marker);
        // marcadores.add(marker2Marker);

        // Mueve la cámara al primer marcador
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tienda));
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        txtLatitud.setText(""+latLng.latitude);
        txtLongitud.setText(""+ latLng.longitude);
    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        txtLatitud.setText(""+latLng.latitude);
        txtLongitud.setText(""+ latLng.longitude);
    }
    @Override
    public boolean onMarkerClick(Marker marker) {
        // Muestra la información del marcador al hacer clic
        marker.showInfoWindow();

        // Obtiene las coordenadas del marcador
        LatLng markerPosition = marker.getPosition();
        double latitud = markerPosition.latitude;
        double longitud = markerPosition.longitude;

        // Muestra las coordenadas en los campos de texto
        txtLatitud.setText(String.valueOf(latitud));
        txtLongitud.setText(String.valueOf(longitud));

        return true;
    }


}
