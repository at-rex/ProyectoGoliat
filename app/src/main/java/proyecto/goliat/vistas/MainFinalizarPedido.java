package proyecto.goliat.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import proyecto.goliat.R;

public class MainFinalizarPedido extends AppCompatActivity {
    Button btnSeguirComprando;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_finalizar_pedido);
        btnSeguirComprando = (Button) findViewById(R.id.btnSeguirComprando);
        btnSeguirComprando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainFinalizarPedido.this,MainMenuPrincipal.class);
                startActivity(intent2);
                finish();
            }
        });
    }
}
