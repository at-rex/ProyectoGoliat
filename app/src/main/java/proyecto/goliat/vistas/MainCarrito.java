package proyecto.goliat.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import proyecto.goliat.R;

public class MainCarrito extends AppCompatActivity {
    Button btnFinalizarPedido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_carrito);
        btnFinalizarPedido = (Button) findViewById(R.id.btnFinalizarPedido);

        btnFinalizarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainCarrito.this,MainFinalizarPedido.class);
                startActivity(intent2);
            }
        });
    }
}
