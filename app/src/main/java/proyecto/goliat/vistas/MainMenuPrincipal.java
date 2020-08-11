package proyecto.goliat.vistas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import proyecto.goliat.entidades.Producto;
import proyecto.goliat.R;
import proyecto.goliat.adaptadores.AdaptadorCard;

public class MainMenuPrincipal extends AppCompatActivity {
    RecyclerView recyclerProductos;
    ArrayList<Producto> listaProductos;
    //botones
    Button btnabarrotes, btngolosinas, btnbebidas, btnalcohol, btnlimpieza;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_principal);
        btnabarrotes = findViewById(R.id.btnabarrotes);
        btngolosinas = findViewById(R.id.btngolosinas);
        btnbebidas = findViewById(R.id.btnbebidas);
        btnalcohol = findViewById(R.id.btnalcohol);
        btnlimpieza = findViewById(R.id.btnlimpieza);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
        //Lista de Productos recientes
        listaProductos = new ArrayList<>();
        recyclerProductos = findViewById(R.id.recicler);
        recyclerProductos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        llenarProductos();

        AdaptadorCard adapter = new AdaptadorCard(listaProductos);
        recyclerProductos.setAdapter(adapter);

        //ejecucion de botones
        btnabarrotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainMenuPrincipal.this, ListAbarrotes.class);
                startActivity(intent1);
            }
        });
        btngolosinas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainMenuPrincipal.this, ListGolosinas.class);
                startActivity(intent2);
            }
        });
        btnbebidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(MainMenuPrincipal.this, ListBebidas.class);
                startActivity(intent3);
            }
        });
        btnalcohol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(MainMenuPrincipal.this, ListBAlcoholicas.class);
                startActivity(intent4);
            }
        });
        btnlimpieza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(MainMenuPrincipal.this, ListPLimpieza.class);
                startActivity(intent5);
            }
        });
    }
    private void llenarProductos() {
        listaProductos.add(new Producto("Pilsen 1 L", "S/.7.00", R.drawable.logo_cuadrado));
        listaProductos.add(new Producto("Cristal 1 L", "S/.7.00", R.drawable.logo_cuadrado));
        listaProductos.add(new Producto("Sazonadores", "S/.1.00", R.drawable.logo_cuadrado));
        listaProductos.add(new Producto("Lavavajillas en pasta 800g", "S/.5.00", R.drawable.logo_cuadrado));
        listaProductos.add(new Producto("Inca Kola 1.5 L", "S/.5.00", R.drawable.logo_cuadrado));
        listaProductos.add(new Producto("Papel Toalla ELITE", "S/.6.20", R.drawable.logo_cuadrado));
        listaProductos.add(new Producto("Coca Cola 1.5 L", "S/.5.00", R.drawable.logo_cuadrado));
        listaProductos.add(new Producto("Rellenitas (Fresa)", "S/.0.60", R.drawable.logo_cuadrado));
        listaProductos.add(new Producto("Rellenitas (Coco)", "S/.0.60", R.drawable.logo_cuadrado));
        listaProductos.add(new Producto("Inca Kola 1 L", "S/.3.20", R.drawable.logo_cuadrado));
        listaProductos.add(new Producto("Mermelada", "S/.1.50", R.drawable.logo_cuadrado));
        listaProductos.add(new Producto("Coca Cola 1 L", "S/.3.20", R.drawable.logo_cuadrado));
        listaProductos.add(new Producto("Harina", "S/.0.99", R.drawable.logo_cuadrado));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mimenu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.mnCarrito:
                Intent intent2 = new Intent(MainMenuPrincipal.this,MainCarrito.class);
                startActivity(intent2);
                break;
            case R.id.mnPedidos:
                Intent intent3 = new Intent(MainMenuPrincipal.this,MainPedidos.class);
                startActivity(intent3);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
