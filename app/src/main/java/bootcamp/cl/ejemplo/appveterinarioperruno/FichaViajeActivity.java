package bootcamp.cl.ejemplo.appveterinarioperruno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import bootcamp.cl.ejemplo.appveterinarioperruno.basedatos.DestinoDAO;
import bootcamp.cl.ejemplo.appveterinarioperruno.basedatos.AppDataBase;
import bootcamp.cl.ejemplo.appveterinarioperruno.basedatos.FichaDestinoDAO;
import bootcamp.cl.ejemplo.appveterinarioperruno.databinding.ActivityFichaViajeBinding;
import bootcamp.cl.ejemplo.appveterinarioperruno.modelo.Destino;
import bootcamp.cl.ejemplo.appveterinarioperruno.modelo.FichaDestino;

public class FichaViajeActivity extends AppCompatActivity {

    private String nombreDestino;
    private String diasDestinoRecibida;
    private String nochesDestinoRecibida;
    private String valorDestino;
    private boolean localidadDestino;

    private ActivityFichaViajeBinding binding;
    private Destino destino;
    private FichaDestino fichaDestino;
    private Context context;

    private static final int SELECT_IMAGE = 100;

    public FichaViajeActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        binding = ActivityFichaViajeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        seteoBotones();
        binding.ImagenAvatarViaje.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, SELECT_IMAGE);
        });
    }@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == SELECT_IMAGE && data != null) {
            Uri imagenUri = data.getData();
            binding.ImagenAvatarViaje.setImageURI(imagenUri);
        }

    }

    private void seteoBotones() {
        binding.radioGroup.setOnCheckedChangeListener((radioGroup, i) -> localidadDestino = i == R.id.radioInternacional);
        binding.botonVerDatos.setOnClickListener(view -> abrirVerFichas());
    }


    private void abrirVerFichas() {
        destino = new Destino(); // inicializar la variable destino
        fichaDestino = new FichaDestino();
        destino.setRutaImagen(destino.getRutaImagen());
        destino.setNombreDestino(nombreDestino);
        destino.setTiempoDestino(diasDestinoRecibida);
        destino.setTiempoDestino2(nochesDestinoRecibida);
        destino.setValorDestinoRecibida(valorDestino);
        destino.setLocalidadDestino(localidadDestino);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            AppDataBase db = AppDataBase.getDatabase(context);
            DestinoDAO destinoDAO = db.destinoDao();
            FichaDestinoDAO fichaDestinoDAO = db.fichaDestinoDao();
            long idDestino = destinoDAO.insertarDestino(destino);
            fichaDestino.setDestino_id(idDestino);
            fichaDestinoDAO.insertarFichaDestino(fichaDestino);
            runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(getApplicationContext(), "Destino agregado", Toast.LENGTH_SHORT).show();
                    //Intent intentActividadVerFicha = new Intent(context, VerFichaActivity.class);
                    //intentActividadVerFicha.putExtra("perroAtendido",fichaAnimal);
                    //startActivity(intentActividadVerFicha);
                }
            });        });
    }
}
