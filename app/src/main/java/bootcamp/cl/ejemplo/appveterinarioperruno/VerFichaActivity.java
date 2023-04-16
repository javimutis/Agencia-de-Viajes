package bootcamp.cl.ejemplo.appveterinarioperruno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class VerFichaActivity extends AppCompatActivity {

    // Este método se ejecuta cuando se crea la actividad
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Establecemos la interfaz de usuario para esta actividad
        setContentView(R.layout.activity_ver_ficha);
    }
// Este método se ejecuta cuando la actividad comienza a mostrarse en pantalla
    @Override
    protected void onStart() {
        super.onStart();
    }
}