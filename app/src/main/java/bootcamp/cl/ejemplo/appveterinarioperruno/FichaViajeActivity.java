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

/*El método "tomarFoto()" es utilizado para lanzar la cámara del dispositivo y permitir al usuario
 tomar una foto. Este método solicita el permiso para usar la cámara si no está otorgado y crea un
 archivo donde se almacenará la imagen capturada por la cámara. Luego, el método inicia la actividad
 de la cámara y pasa la URI del archivo creado al intent de la cámara.

El método "createImageFile()" es utilizado para crear un archivo de imagen único, basado en la fecha
y hora actuales, que se utilizará para almacenar la imagen capturada por la cámara. Este método
devuelve el archivo creado y guarda la ruta absoluta del archivo en la variable "mCurrentPhotoPath".*/
public class FichaViajeActivity extends AppCompatActivity {

    // Variables que almacenan los valores de los permisos de la cámara y la foto
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_PERMISSION_CAMERA = 2;
    // Variable que almacena la imagen de la cámara
    private Bitmap imageBitmap;
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

    /*El método onCreate() es el método principal de la actividad y es donde se infla la vista de
    la actividad y se establecen los oyentes de los botones.*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_ficha_viaje);
        context = getApplicationContext();
        binding = ActivityFichaViajeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        seteoBotones();

    }

    /*El método seteoBotones() se utiliza para establecer los oyentes de los
    botones de la interfaz de usuario.*/
    public void seteoBotones() {
// Botón para tomar una foto
        binding.ImagenAvatarViaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tomarFoto();
                //Aca llamo a la cámra
            }
        });
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
                Intent intent = new Intent(getApplicationContext(), ListadoFichasActivity.class);
                startActivity(intent);
            }
        });
                //Log.d("MainActivity", "CLICK");
            }




    /*El método llenarRegistroDestino() se utiliza para tomar la información ingresada por el usuario y
    crear una ficha de destino y una ficha de registro para esa ficha de destino en la base de datos
    de la aplicación.*/
    public void llenarRegistroDestino() {

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
        destino.setRutaImagen(ImagenAvatarViaje);
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

    //Método para lanzar la cámara
    public void tomarFoto() {
        // Solicitar permiso para usar la cámara si no está otorgado
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    REQUEST_PERMISSION_CAMERA);
            return;
        }
// Crear un intent para tomar una foto
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Verificar si hay una aplicación de cámara instalada en el dispositivo
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Crear el archivo donde se almacenará la imagen
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error al crear el archivo
                ex.printStackTrace();
            }
            // Continuar solo si el archivo fue creado correctamente
            if (photoFile != null) {
                // Obtener la URI del archivo creado
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                // Pasar la URI al intent de la cámara
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                // Iniciar la actividad de la cámara
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    // Método para crear un archivo de imagen único
    private File createImageFile() throws IOException {
        // Crear un nombre único para la imagen basado en la fecha y hora actuales
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        // Obtener el directorio donde se almacenarán las imágenes
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        // Crear el archivo
        File image = File.createTempFile(
                imageFileName,  /* prefijo */
                ".jpg",         /* sufijo */
                storageDir      /* directorio */
        );
        // Guardar la ruta absoluta del archivo creado
        ImagenAvatarViaje = image.getAbsolutePath();
        return image;
    }

    // Método para recibir la imagen capturada por la cámara
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Verificar que la solicitud es para tomar una foto y que fue exitosa
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // Mostrar la imagen en el ImageView

            Bitmap bitmap = BitmapFactory.decodeFile(ImagenAvatarViaje);
            binding.ImagenAvatarViaje.setImageBitmap(bitmap);
            // Guardar la imagen en la galería del dispositivo
            //galleryAddPic();
        }
    }


}
