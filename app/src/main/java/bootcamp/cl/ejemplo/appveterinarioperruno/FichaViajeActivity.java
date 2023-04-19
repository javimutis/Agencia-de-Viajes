package bootcamp.cl.ejemplo.appveterinarioperruno;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.RadioGroup;

import java.io.File;

import android.Manifest;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import bootcamp.cl.ejemplo.appveterinarioperruno.basedatos.DestinoDAO;
import bootcamp.cl.ejemplo.appveterinarioperruno.basedatos.AppDataBase;
import bootcamp.cl.ejemplo.appveterinarioperruno.basedatos.FichaDestinoDAO;
import bootcamp.cl.ejemplo.appveterinarioperruno.databinding.ActivityFichaViajeBinding;
import bootcamp.cl.ejemplo.appveterinarioperruno.modelo.Destino;
import bootcamp.cl.ejemplo.appveterinarioperruno.modelo.FichaDestino;

public class FichaViajeActivity extends AppCompatActivity {

    // Variables que almacenan los datos del viaje
    private String campoDestino;
    private String ImagenAvatarViaje;
    private String campoDias;
    private String campoNoches;
    private String campoValor;
    private boolean localidadDestino;
    private String descripPaquete;
    private float valoracionEstrellas;
    private ActivityFichaViajeBinding binding;

    // Variables para trabajar con la base de datos
    private Destino destino;
    private FichaDestino fichaDestino;
    private Context context;

    // Código de solicitud para la selección de imagen
    private static final int SELECT_IMAGE = 100;
    private Uri imagenUri;

    /*El método onCreate() es el método principal de la actividad y es donde se infla la vista de
    la actividad y se establecen los oyentes de los botones.*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        binding = ActivityFichaViajeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        seteoBotones();

// Obtenemos el Intent que inició esta actividad y obtenemos los datos
        Intent intent = getIntent();
        String destino = intent.getStringExtra("destino");
        String imagen = intent.getStringExtra("imagen");
        String dias = intent.getStringExtra("dias");
        String noches = intent.getStringExtra("noches");
        String valor = intent.getStringExtra("valor");
        boolean localidad = intent.getBooleanExtra("localidad", false);
        String descripcion = intent.getStringExtra("descripcion");
        float valoracion = intent.getFloatExtra("valoracion", 0f);

        // Mostramos los datos en la vista
        TextView destinoTextView = findViewById(R.id.destinoTextView);
        destinoTextView.setText(destino);

        ImageView imagenImageView = findViewById(R.id.imagenImageView);
        imagenImageView.setImageURI(Uri.parse(imagen));

        TextView diasTextView = findViewById(R.id.diasTextView);
        diasTextView.setText(dias);

        TextView nochesTextView = findViewById(R.id.nochesTextView);
        nochesTextView.setText(noches);

        TextView valorTextView = findViewById(R.id.valorTextView);
        valorTextView.setText(valor);

        TextView localidadTextView = findViewById(R.id.localidadTextView);
        localidadTextView.setText(localidad ? "Internacional" : "Nacional");

        TextView descripcionTextView = findViewById(R.id.descripcionTextView);
        descripcionTextView.setText(descripcion);

        RatingBar valoracionRatingBar = findViewById(R.id.valoracionRatingBar);
        valoracionRatingBar.setRating(valoracion);
        // Establecer el OnClickListener del botón para subir foto desde el almacenamiento del dispositivo
        binding.ImagenAvatarViaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, SELECT_IMAGE);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == SELECT_IMAGE) {
            imagenUri = data.getData();
            binding.ImagenAvatarViaje.setImageURI(imagenUri);
        }
        if (resultCode == RESULT_OK && requestCode == SELECT_IMAGE && data != null) {
        }

    }


    /*El método seteoBotones() se utiliza para establecer los oyentes de los
    botones de la interfaz de usuario.*/
    public void seteoBotones() {

// RadioGroup para seleccionar la localidad del destino
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.radioNacional) {

                    localidadDestino = false;
                    // El usuario seleccionó "nacional"
                    // Aquí puedes hacer lo que necesites con este valor

                } else if (i == R.id.radioInternacional) {

                    localidadDestino = true;

                    // El usuario seleccionó "Internacional"
                    // Aquí puedes hacer lo que necesites con este valor
                }
            }
        });

        binding.botonVerDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), VerFichaActivity.class);
                startActivity(intent);
            }
        });
        //Log.d("MainActivity", "CLICK");

    }


    /*El método llenarRegistroDestino() se utiliza para tomar la información ingresada por el usuario y
    crear una ficha de destino y una ficha de registro para esa ficha de destino en la base de datos
    de la aplicación.*/
        public void llenarRegistroDestino () {

            //Se obtiene una instancia de la base de datos
            AppDataBase db = AppDataBase.getDatabase(context);
            //Se obtiene el DAO para la tabla destino
            DestinoDAO destinoDAO = db.destinoDao();
            //Se obtiene el DAO para la tabla ficha de registro
            FichaDestinoDAO fichaDao = db.fichaRegistroDao();

            //Se obtienen los valores ingresados por el usuario en los campos correspondientes
            campoDestino = binding.campoDestino.getText().toString();
            campoValor = binding.campoValor.getText().toString();
            campoDias = binding.campoDias.getText().toString();
            campoNoches = binding.campoNoches.getText().toString();
            descripPaquete = binding.descripPaquete.getText().toString();
            valoracionEstrellas = binding.valoracionEstrellas.getRating();

            //Se crea un objeto Destino con los valores ingresados por el usuario
            Destino destino = new Destino();
            destino.setRutaImagen(imagenUri.toString());
            destino.setNombreDestino(campoDestino);
            destino.setValorDestinoRecibida(campoValor);
            destino.setTiempoDestino(campoDias);
            destino.setTiempoDestino2(campoNoches);


            //Se crea un objeto FichaDestino con los valores ingresados por el usuario
            FichaDestino fichaDestino = new FichaDestino();
            fichaDestino.setDescripPaquete(descripPaquete);
            fichaDestino.setValoracionEstrellas(valoracionEstrellas);

            //Se utiliza ExecutorService para ejecutar la inserción de los datos en la base
            // de datos en un hilo

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(new Runnable() {
                @Override
                public void run() {

                    // Insertar el destino y su ficha en la base de datos
                    //Se inserta el objeto Destino en la tabla destino y se obtiene el ID generado
                    long idDestino = destinoDAO.insertarDestino(destino); // Insertar el destino en la tabla
                    fichaDestino.setDestinoId(idDestino); // Asignar el ID generado al objeto ficha
                    long idFicha = fichaDao.insertarFichaRegistro(fichaDestino); // Insertar la ficha en la tabla

                    runOnUiThread(new Runnable() {
                        public void run() {
                            //Se muestra un mensaje al usuario indicando que se completó el ingreso del destino
                            Toast.makeText(getApplicationContext(), "Ingreso del destino completo", Toast.LENGTH_SHORT).show();
                            //Se podría lanzar una actividad para ver la ficha del destino creado

                            //Intent intentActividadVerFicha = new Intent(context, VerFichaActivity.class);
                            //intentActividadVerFicha.putExtra("perroAtendido",fichaDestino);
                            //startActivity(intentActividadVerFicha);
                        }
                    });

                }

            });

        }

        }

