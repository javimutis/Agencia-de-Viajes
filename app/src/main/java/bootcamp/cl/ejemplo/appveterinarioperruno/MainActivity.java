package bootcamp.cl.ejemplo.appveterinarioperruno;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
// A una pantalla o vista del programa se le conoce como Activity
//Por cada activity siempre hay un archivo layout
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button agregarDestino = findViewById(R.id.botonAgregar);

        Log.d("MainActivity", "La activity agregar se ha creado");

        agregarDestino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirFichaViaje();
            }
        });

        Button irAlRegistro = findViewById(R.id.botonRegistro);

        Log.e("MainActivity", "La activity registro se ha creado");

        irAlRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirVerFicha();
            }
        });
    }

    private void abrirFichaViaje() {
        Intent intentoFichaViaje = new Intent(this, FichaViajeActivity.class);
        startActivity(intentoFichaViaje);
    }

    private void abrirVerFicha() {
        Intent intentoVerFicha = new Intent(this, VerFichaActivity.class);
        startActivity(intentoVerFicha);
    }
}
