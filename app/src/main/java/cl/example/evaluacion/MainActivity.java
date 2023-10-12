package cl.example.evaluacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.content.Intent;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cl.example.evaluacion.Clases.Pedidos;

public class MainActivity extends AppCompatActivity {
    private List<Pedidos> ListPedidos = new ArrayList<Pedidos>();
    ArrayAdapter<Pedidos> arrayAdapterPedidos;

    EditText eTNombre,eTpedido,eTestado;
    Button bTAgregar,bTsensor;
    ListView lvpedidos;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bTsensor=findViewById(R.id.btSensor);
        Intent intent = new Intent(this,Sensores.class);
        eTNombre=findViewById(R.id.eTnombre);
        eTestado=findViewById(R.id.eTestado);
        eTpedido=findViewById(R.id.eTpedido);
        bTAgregar=findViewById(R.id.button);
        lvpedidos=findViewById(R.id.lvPedidos);
        inicializarFireBase();
        listarDatos();


        bTsensor.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            startActivity(intent);
                                        }
                                    });
                bTAgregar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Pedidos pedidos = new Pedidos();
                        //libro.setIdAutor("11111");
                        pedidos.setIdPedido(UUID.randomUUID().toString());
                        pedidos.setNombre(eTNombre.getText().toString());
                        pedidos.setEstado(eTestado.getText().toString());
                        databaseReference.child("Pedidos").child(pedidos.getIdPedido()).setValue(pedidos);


                    }
                });


    }
    private void listarDatos() {
        databaseReference.child("Pedidos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ListPedidos.clear();
                for (DataSnapshot objs : snapshot.getChildren()){
                    Pedidos pe =objs.getValue(Pedidos.class);
                    ListPedidos.add(pe);
                    arrayAdapterPedidos =new ArrayAdapter<Pedidos>(MainActivity.this, android.R.layout.simple_expandable_list_item_1,ListPedidos);
                    lvpedidos.setAdapter(arrayAdapterPedidos);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void inicializarFireBase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase =FirebaseDatabase.getInstance();
        databaseReference =firebaseDatabase.getReference();
    }
}