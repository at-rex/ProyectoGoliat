package proyecto.goliat.vistas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import proyecto.goliat.entidades.Producto;
import proyecto.goliat.R;
import proyecto.goliat.adaptadores.AdaptadorAbarrotes;

public class ListAbarrotes extends AppCompatActivity {
    ArrayList<Producto> listAbarrotes;
    RecyclerView reciclerAbarrotes;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_abarrotes);
        listAbarrotes = new ArrayList<>();
        reciclerAbarrotes = findViewById(R.id.reciclerAbarrotes);
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        mAuth = FirebaseAuth.getInstance();
        reciclerAbarrotes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //llenarProductos();
        obtenerUsuario();
        databaseReference.child("Productos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listAbarrotes.clear();
                //String codigo = Settings.Secure.getString(getBaseContext().getContentResolver(), Settings.Secure.ANDROID_ID);
                //String codigoUltimo = "";
                for (DataSnapshot objSnapshot: dataSnapshot.getChildren()){
                    Producto c = objSnapshot.getValue(Producto.class);
                    listAbarrotes.add(c);
                    //codigoUltimo = c.getCodigo();
                }
                AdaptadorAbarrotes adapter = new AdaptadorAbarrotes(listAbarrotes);
                reciclerAbarrotes.setAdapter(adapter);
                /*if (!codigo.equals(codigoUltimo)){
                    vibrar.vibrate(700);
                }*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        /*AdaptadorAbarrotes adapter = new AdaptadorAbarrotes(listAbarrotes);
        reciclerAbarrotes.setAdapter(adapter);*/
    }
    private void obtenerUsuario(){
        //String id = mAuth.getCurrentUser().getUid();
        databaseReference.child("Productos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    Toast.makeText(getApplicationContext(), "se cargo los datos", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void llenarProductos() {
        listAbarrotes.add(new Producto("Avena", "S/.1.00", R.drawable.logo_cuadrado));
        listAbarrotes.add(new Producto("Café", "S/.1.50", R.drawable.logo_cuadrado));
        listAbarrotes.add(new Producto("Jabón Moncler 160g", "S/.1.50", R.drawable.logo_cuadrado));
        listAbarrotes.add(new Producto("Harina", "S/.0.99", R.drawable.logo_cuadrado));
        listAbarrotes.add(new Producto("Sazonadores", "S/.1.00", R.drawable.logo_cuadrado));
        listAbarrotes.add(new Producto("Mermelada", "S/.1.50", R.drawable.logo_cuadrado));
        listAbarrotes.add(new Producto("Mayonesa", "S/.1.00", R.drawable.logo_cuadrado));
    }
}
