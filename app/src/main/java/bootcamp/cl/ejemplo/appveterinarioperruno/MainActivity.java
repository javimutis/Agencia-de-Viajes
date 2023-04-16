package bootcamp.cl.ejemplo.appveterinarioperruno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

// A una pantalla o vista del programa se le conoce como Activity
//Por cada activity siempre hay un archivo layout
public class MainActivity extends AppCompatActivity {


    /*
    Variables para lógica
     */
    // Método que se ejecuta cuando se crea la actividad
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);// Establece la vista del layout llamado activity_main

    /*
    Traigo la vista a la actividad
    */
        // Obtiene la referencia al botón de navegación de la vista actual

       Button agregarRegistro = findViewById(R.id.botonAgregar);

    // Imprime un mensaje en el registro de logs de la aplicación
        Log.d("MainActivity", "La activity se ha creado");
/*
    Funcionalidad de la app que lleva a la ficha de viaje cuando se presiona el botón
    */
        // Asigna un escucha de clics al botón de navegación
        agregarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirAgregarFichaDestino();
            }// Llama al método abrirRegistroFichaDestino cuando se hace clic en el botón

        });

    }

    // Método que se ejecuta cuando se presiona el botón de navegación
    private void abrirAgregarFichaDestino() {
// Crea un nuevo intento que abre la actividad de la ficha del viaje
        Intent intentoFicha = new Intent(this, FichaViajeActivity.class);
        startActivity(intentoFicha); // Inicia la actividad del viaje
    }

    // Método que se ejecuta cuando se presiona el botón de lista de viajes
    public void abrirListaDestino(View view) {
// Crea un nuevo intento que abre la actividad de listado de fichas de viajes
        Intent intent = new Intent(this, ListadoFichasActivity.class);
        startActivity(intent); // Inicia la actividad del listado de fichas de viajes


    }

}