package cl.example.evaluacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
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

import cl.example.evaluacion.Clases.brotherhood;

public class jjbarbers extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

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
    GoogleMap mMap;

    // Lista para almacenar los marcadores
    List<Marker> marcadores = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jjbarbers);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa4);
        mapFragment.getMapAsync(this);

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
                if (tag != null && tag instanceof jjbarbers.CustomInfo) {
                    jjbarbers.CustomInfo customInfo = (jjbarbers.CustomInfo) tag;

                    // Actualiza las vistas con la información personalizada
                    infoImage.setImageResource(customInfo.getImageResId());
                    infoTextView.setText(customInfo.getInfoText());
                }

                return infoWindow;
            }
        });
        // Aquí puedes agregar marcadores al mapa
        // Otros marcadores
        LatLng marker1 = new LatLng(-36.59948,-72.08966);
        Marker marker1Marker = mMap.addMarker(new MarkerOptions().position(marker1).title("JJ Barbers").snippet("BARBERIA "));
        marker1Marker.setTag(new jjbarbers.CustomInfo(R.drawable.jj_barbers__black, "Información de JJ Barbers"));


        // Mueve la cámara al primer marcador
        mMap.moveCamera(CameraUpdateFactory.newLatLng(marker1));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        // Muestra la información del marcador al hacer clic
        marker.showInfoWindow();

        return true;
    }


}