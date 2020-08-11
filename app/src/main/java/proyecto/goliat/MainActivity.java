package proyecto.goliat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import proyecto.goliat.adaptadores.AdaptadorCard;
import proyecto.goliat.entidades.Categoria;
import proyecto.goliat.entidades.Producto;
import proyecto.goliat.vistas.ListAbarrotes;
import proyecto.goliat.vistas.ListBAlcoholicas;
import proyecto.goliat.vistas.ListBebidas;
import proyecto.goliat.vistas.ListGolosinas;
import proyecto.goliat.vistas.ListPLimpieza;
import proyecto.goliat.vistas.MainMenuPrincipal;
import proyecto.goliat.vistas.MainRegistro;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    EditText txtcorreo, txtclave;
    Button btnRegistrar, btnIngresar;
    private FirebaseAuth mAuth;
    ArrayList<Categoria> listaCategorias;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        txtcorreo = (EditText) findViewById(R.id.txtcorreo);
        txtclave = (EditText) findViewById(R.id.txtclave);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        mAuth = FirebaseAuth.getInstance();
        listaCategorias = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        cargarCategoria("Pilsen 1L",7.00, "BAlcoholicas","test");
        cargarCategoria("Cristal 1L", 7.00, "BAlcoholicas","test");
        cargarCategoria("Doña Gusta 7g", 1.00, "Abarrotes","test");
        //cargarCategoria("Lavavajillas en pasta 800g", "S/.5.00", "test");
        cargarCategoria("Inca Kola 1.5L", 5.00, "Bebidas","test");
        cargarCategoria("Papel Toalla Elite Mega Rollo", 6.20, "PLimpieza","test");
        cargarCategoria("Coca Cola 1.5L", 5.00, "Bebidas","test");
        cargarCategoria("Rellenitas (Fresa)", 0.60, "Golosinas","test");
        cargarCategoria("Rellenitas (Coco)", 0.60, "Golosinas","test");
        cargarCategoria("Inca Kola 1L", 3.20, "Bebidas","test");
        cargarCategoria("Mermelada Fanny 100g", 1.50, "Abarrotes","test");
        cargarCategoria("Coca Cola 1L", 3.20, "Bebidas","test");
        cargarCategoria("Harina BlancaFlor 1Kg", 4.00, "Abarrotes","test");

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = txtcorreo.getText().toString();
                String clave = txtclave.getText().toString();
                if (!correo.isEmpty() && !clave.isEmpty()){
                    if (clave.length()>=6){
                        validarUsuario(correo, clave);
                    }else{
                        Toast.makeText(getApplicationContext(),"Tamaño de la clave mayor que 6",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Datos incompletos",Toast.LENGTH_LONG).show();
                }
            }
        });
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainRegistro.class));
            }
        });
    }
    private void validarUsuario(final String correo, final String clave){
        mAuth.signInWithEmailAndPassword(correo, clave).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this, MainMenuPrincipal.class));
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Correo o Clave incorrecto",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void cargarCategoria(String nombre, double precio, String categoria, String foto){
        //String keyfirebase = databaseReference.push().getKey();
        //Producto c = new Producto("S/.7.00", "S",123);
        Map<String,Object> map = new HashMap<>();
        map.put("precio",precio);
        map.put("categoria",categoria);
        map.put("foto",foto);
        //databaseReference.child("Categorias").child(keyfirebase).setValue(c);
        databaseReference.child("Productos").child(nombre).setValue(map);
    }

    private void cargarProducto(){

    }
}
