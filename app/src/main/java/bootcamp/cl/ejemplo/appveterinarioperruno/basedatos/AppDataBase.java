package bootcamp.cl.ejemplo.appveterinarioperruno.basedatos;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import bootcamp.cl.ejemplo.appveterinarioperruno.modelo.Destino;
import bootcamp.cl.ejemplo.appveterinarioperruno.modelo.FichaDestino;

/*este código define la clase AppDataBase que se encarga de la creación y el manejo de la base de
datos de la aplicación. Contiene métodos para acceder a las tablas correspondientes a las entidades
Destino y FichaDestino, así como un executor que permite realizar operaciones de escritura de forma
asíncrona. Además, implementa el patrón Singleton para garantizar que solo haya una instancia de la
base de datos en la aplicación.*/

// Anotación que define la clase como una base de datos, y le indica qué entidades contiene y la versión

@Database(entities = {Destino.class, FichaDestino.class}, version = 4)
// Clase abstracta que extiende de RoomDatabase, para crear la base de datos
public abstract class AppDataBase extends RoomDatabase {

    public abstract DestinoDAO destinoDao();
    public abstract FichaDestinoDAO fichaDestinoDao();

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static volatile AppDataBase INSTANCE;

    public static synchronized AppDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class, "bd_agencia_viajes")
                    .addMigrations(new Migration1To2())
                    .build();
        }
        return INSTANCE;
    }
}
