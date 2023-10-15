package cl.example.evaluacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cl.example.evaluacion.Clases.Mymaps;
import cl.example.evaluacion.Clases.Pedidos;

public class MainActivity extends AppCompatActivity {
    private List<Pedidos> ListPedidos = new ArrayList<Pedidos>();
    ArrayAdapter<Pedidos> arrayAdapterPedidos;

    EditText eTNombre,eTpedido,eTestado;
    Button bTAgregar,bTinicio,bTeliminar;
    ListView lvpedidos;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bTinicio=findViewById(R.id.btInicio);
        Intent intent = new Intent(this, Inicio.class);
        eTNombre=findViewById(R.id.eTnombre);
        eTestado=findViewById(R.id.eTestado);
        bTeliminar=findViewById(R.id.btEliminar);
        bTAgregar=findViewById(R.id.button);
        lvpedidos=findViewById(R.id.lvPedidos);
        inicializarFireBase();
        listarDatos();


        bTinicio.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            startActivity(intent);
                                        }
                                    });

        bTAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtén el texto ingresado en los EditText
                String nombrePedido = eTNombre.getText().toString();
                String estadoPedido = eTestado.getText().toString();

                // Verifica si los campos están vacíos
                if (nombrePedido.isEmpty() || estadoPedido.isEmpty()) {
                    // Muestra un mensaje de error o realiza alguna acción apropiada, por ejemplo:
                    Toast.makeText(getApplicationContext(), "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
                } else {
                    // Los campos no están vacíos, puedes agregar el pedido a la base de datos
                    Pedidos pedidos = new Pedidos();
                    pedidos.setIdPedido(UUID.randomUUID().toString());
                    pedidos.setNombre(nombrePedido);
                    pedidos.setEstado(estadoPedido);
                    databaseReference.child("Pedidos").child(pedidos.getIdPedido()).setValue(pedidos);

                    // Puedes mostrar un mensaje de éxito o realizar otras acciones apropiadas
                    Toast.makeText(getApplicationContext(), "Pedido agregado con éxito.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bTeliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtén el nombre del pedido que deseas eliminar desde el EditText
                String nombrePedido = eTNombre.getText().toString().trim(); // Obtén el texto del EditText

                // Verifica si el nombre del pedido no está vacío
                if (!nombrePedido.isEmpty()) {
                    // Crea una consulta para buscar el pedido por nombre
                    Query query = databaseReference.child("Pedidos").orderByChild("nombre").equalTo(nombrePedido);

                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                // El pedido con el nombre especificado existe, ahora puedes eliminarlo

                                for (DataSnapshot pedidoSnapshot : dataSnapshot.getChildren()) {
                                    // Obtiene la referencia del pedido
                                    DatabaseReference pedidoRef = pedidoSnapshot.getRef();

                                    // Elimina el pedido
                                    pedidoRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(getApplicationContext(), "Pedido eliminado con éxito.", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(getApplicationContext(), "Se produjo un error al eliminar el pedido.", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                            } else {
                                // No se encontró un pedido con el nombre especificado
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            // Maneja errores en la consulta
                        }
                    });
                } else {
                    // El campo de nombre del pedido está vacío, maneja esto según tu necesidad
                }
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