package bootcamp.cl.ejemplo.appveterinarioperruno;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import bootcamp.cl.ejemplo.appveterinarioperruno.basedatos.AppDataBase;
import bootcamp.cl.ejemplo.appveterinarioperruno.basedatos.DestinoDAO;
import bootcamp.cl.ejemplo.appveterinarioperruno.basedatos.FichaDestinoDAO;
import bootcamp.cl.ejemplo.appveterinarioperruno.databinding.ActivityFichaViajeBinding;
import bootcamp.cl.ejemplo.appveterinarioperruno.modelo.Destino;
import bootcamp.cl.ejemplo.appveterinarioperruno.modelo.FichaDestino;

public class FichaViajeActivity extends AppCompatActivity {

    private static final int SELECT_IMAGE = 100;
    private String nombreDestino;
    private String tiempoDestino;
    private String tiempoDestino2;
    private String valorDestinoRecibida;
    private boolean localidadDestino;
    private String descripcionFichaDestino;
    private float estrellasValoracion;
    private ActivityFichaViajeBinding binding;
    private Destino destino;
    private FichaDestino fichaDestino;
    private Context context;

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
    }

    @Override
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
        nombreDestino = binding.campoDestino.getText().toString();
        tiempoDestino = binding.campoDias.getText().toString();
        tiempoDestino2 = binding.campoNoches.getText().toString();
        valorDestinoRecibida = binding.campoValor.getText().toString();
        localidadDestino = binding.radioGroup.getCheckedRadioButtonId() == R.id.radioInternacional;
        descripcionFichaDestino = binding.descripPaquete.getText().toString();
        estrellasValoracion = binding.valoracionEstrellas.getRating();

        destino = new Destino();
        fichaDestino = new FichaDestino();
        destino.setNombreDestino(nombreDestino);
        destino.setTiempoDestino(tiempoDestino);
        destino.setTiempoDestino2(tiempoDestino2);
        destino.setValorDestinoRecibida(valorDestinoRecibida);
        destino.setLocalidadDestino(localidadDestino);
        fichaDestino.setDescripcionFichaDestino(descripcionFichaDestino);
        fichaDestino.setEstrellasValoracion(estrellasValoracion);

        // Save the image to the filesystem
        Bitmap bitmap = ((BitmapDrawable) binding.ImagenAvatarViaje.getDrawable()).getBitmap();
        String filename = "imagen_" + System.currentTimeMillis() + ".jpg";
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
            outputStream.close();
            destino.setRutaImagen(filename);
        } catch (Exception e) {
            e.printStackTrace();
        }


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
                }
            });
            Intent intent = new Intent(FichaViajeActivity.this, VerFichaActivity.class);
            intent.putExtra("destino_id", idDestino);
            startActivity(intent);
        });
    }
}


