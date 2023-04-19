package bootcamp.cl.ejemplo.appveterinarioperruno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class VerFichaActivity extends AppCompatActivity {


    // Este método se ejecuta cuando se crea la actividad
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Establecemos la interfaz de usuario para esta actividad
        setContentView(R.layout.activity_ver_ficha);
    }
    // Creamos un objeto Intent
    Intent intent = new Intent(this, VerFichaActivity.class);

// Agregamos los datos al Intent
intent.putExtra("destino", campoDestino);
intent.putExtra("imagen", ImagenAvatarViaje);
intent.putExtra("dias", campoDias);
intent.putExtra("noches", campoNoches);
intent.putExtra("valor", campoValor);
intent.putExtra("localidad", localidadDestino);
intent.putExtra("descripcion", descripPaquete);
intent.putExtra("valoracion", valoracionEstrellas);

    // Iniciamos VerFichaActivity
    startActivity(intent);
// Este método se ejecuta cuando la actividad comienza a mostrarse en pantalla
    @Override
    protected void onStart() {
        super.onStart();
    }
}