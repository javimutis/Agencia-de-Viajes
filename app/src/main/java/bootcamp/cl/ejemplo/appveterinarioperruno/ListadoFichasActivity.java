package bootcamp.cl.ejemplo.appveterinarioperruno;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import bootcamp.cl.ejemplo.appveterinarioperruno.adaptadores.DestinoAdapter;
import bootcamp.cl.ejemplo.appveterinarioperruno.basedatos.AppDataBase;
import bootcamp.cl.ejemplo.appveterinarioperruno.basedatos.DestinoDAO;
import bootcamp.cl.ejemplo.appveterinarioperruno.basedatos.FichaDestinoDAO;
import bootcamp.cl.ejemplo.appveterinarioperruno.databinding.ActivityListadoFichasBinding;
import bootcamp.cl.ejemplo.appveterinarioperruno.modelo.Destino;

public class ListadoFichasActivity extends AppCompatActivity {
    private Context context;
    ActivityListadoFichasBinding binding;

    DestinoAdapter adaptarDestino;

    List<Destino> listaDestino = new ArrayList<>();

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
                DestinoDAO destinoDAO = db.destinoDAO();
                FichaDestinoDAO fichaDestinoDAO = db.fichaDestinoDAO();
                // Se obtine un listado de los registros de la base de datos gracias al DAO

                listaDestino = destinoDAO.obtenerDestinos();

                runOnUiThread(new Runnable() {
                    public void run() {
                        // Se crea el objeto adaptador
                        adaptarDestino = new DestinoAdapter(listaDestino, context);
                        adaptarDestino.setOnItemClickListener(new DestinoAdapter.OnItemClickListener() {

                            @Override
                            public void onItemClick(Destino destino) {

                                Toast.makeText(ListadoFichasActivity.this, destino.getNombreDestino(), Toast.LENGTH_SHORT);
                            }
                        });
                        //Al reciclerView le paso el adaptador lleno de objetos
                        binding.listadoFichas.setAdapter(adaptarDestino);
                    }
                });

            }

        });

    }
}