package bootcamp.cl.ejemplo.appveterinarioperruno;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button agregarDestino = findViewById(R.id.botonAgregar);
        Button irAlRegistro = findViewById(R.id.botonRegistro);

        agregarDestino.setOnClickListener(view -> abrirFichaViaje());

        irAlRegistro.setOnClickListener(view -> abrirListadoFichas());

        Log.d("MainActivity", "La activity se ha creado");
    }

    private void abrirFichaViaje() {
        Intent intentoFichaViaje = new Intent(this, FichaViajeActivity.class);
        startActivity(intentoFichaViaje);
    }

    private void abrirListadoFichas() {
        Intent intentoListadoFichas = new Intent(this, ListadoFichasActivity.class);
        startActivity(intentoListadoFichas);
    }
}
