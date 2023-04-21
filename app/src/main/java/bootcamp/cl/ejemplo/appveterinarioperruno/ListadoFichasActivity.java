package bootcamp.cl.ejemplo.appveterinarioperruno;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import bootcamp.cl.ejemplo.appveterinarioperruno.adaptadores.DestinoAdapter;
import bootcamp.cl.ejemplo.appveterinarioperruno.basedatos.AppDataBase;
import bootcamp.cl.ejemplo.appveterinarioperruno.basedatos.DestinoDAO;
import bootcamp.cl.ejemplo.appveterinarioperruno.databinding.ActivityListadoFichasBinding;
import bootcamp.cl.ejemplo.appveterinarioperruno.modelo.Destino;

public class ListadoFichasActivity extends AppCompatActivity {

    private ActivityListadoFichasBinding binding; // Binding de la vista de la actividad
    private DestinoAdapter destinoAdapter; // Adaptador para la lista de destinos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListadoFichasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.listadoFichas.setLayoutManager(new LinearLayoutManager(this));

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try {
                AppDataBase db = AppDataBase.getDatabase(getApplicationContext());
                DestinoDAO destinoDAO = db.destinoDao();
                List<Destino> listaDestino = destinoDAO.obtenerDestino();

                runOnUiThread(() -> {
                    destinoAdapter = new DestinoAdapter(listaDestino, getApplicationContext());
                    destinoAdapter.setOnItemClickListener(destino -> Toast.makeText(ListadoFichasActivity.this,
                            destino.getNombreDestino(), Toast.LENGTH_SHORT).show());
                    binding.listadoFichas.setAdapter(destinoAdapter);
                });
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    Toast.makeText(ListadoFichasActivity.this, "Error al obtener los destinos.", Toast.LENGTH_SHORT).show();
                });
            }
        });
    }
}