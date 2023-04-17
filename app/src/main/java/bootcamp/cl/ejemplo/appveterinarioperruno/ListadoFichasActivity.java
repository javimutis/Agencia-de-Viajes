package bootcamp.cl.ejemplo.appveterinarioperruno;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import bootcamp.cl.ejemplo.appveterinarioperruno.adaptadores.DestinoAdapter;
import bootcamp.cl.ejemplo.appveterinarioperruno.basedatos.DestinoDAO;
import bootcamp.cl.ejemplo.appveterinarioperruno.basedatos.AppDataBase;
import bootcamp.cl.ejemplo.appveterinarioperruno.basedatos.FichaDestinoDAO;
import bootcamp.cl.ejemplo.appveterinarioperruno.databinding.ActivityListadoFichasBinding;
import bootcamp.cl.ejemplo.appveterinarioperruno.modelo.Destino;

/*este código es para la pantalla de listado de fichas de una aplicación  y utiliza una
base de datos. La actividad crea un hilo secundario para consultar la base de datos y obtiene una
lista de objetos Destino. Luego, crea un adaptador para la lista y la muestra en un RecyclerView.*/
public class ListadoFichasActivity extends AppCompatActivity {

    private Context context;
    ActivityListadoFichasBinding binding; // Binding de la vista de la actividad
    DestinoAdapter adaptarDestino; // Adaptador para la lista de destinos
    List<Destino> listaDestino = new ArrayList<>();// Lista de objetos Destino

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        binding = ActivityListadoFichasBinding.inflate(getLayoutInflater());
        AppDataBase db = AppDataBase.getDatabase(context);
        setContentView(binding.getRoot());
        binding.listadoFichas.setLayoutManager(new LinearLayoutManager(this));

        /*
        Las consultas a las bases de datos se tiene que hacer en un hilo
        secundario , si no la app se cae porque toma el hilo principal
        La lógica que uds hagan debe ir dentro del run()
         */
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {

                DestinoDAO destinoDAO = db.destinoDao();
                FichaDestinoDAO fichaDao = db.fichaRegistroDao();

                // Se obtine un listado de los registros de la base de datos gracias al DAO
                listaDestino = destinoDAO.obtenerDestino();

                runOnUiThread(new Runnable() {
                    public void run() {

                        // Se crea el objeto adaptador
                        adaptarDestino = new DestinoAdapter(listaDestino, context);
                        //Al reciclerView le paso el adaptador lleno de objetos
                        binding.listadoFichas.setAdapter(adaptarDestino);
                    }
                });

            }

        });

    }
}