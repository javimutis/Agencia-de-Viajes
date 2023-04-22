package bootcamp.cl.ejemplo.appveterinarioperruno.basedatos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import bootcamp.cl.ejemplo.appveterinarioperruno.modelo.Destino;

// Indica que esta clase es un Data Access Object (DAO) y está relacionada con la entidad Destino
@Dao
public interface DestinoDAO {

    // Método para insertar un objeto Destino en la base de datos y devuelve el id del registro insertado
    @Insert
    long insertarDestino(Destino destino);

    // Método para actualizar un objeto Destino en la base de datos
    @Update
    void actualizarDestino(Destino destino);

    // Método para eliminar un objeto Destino de la base de datos
    @Delete
    void eliminarDestino(Destino destino);

    // Método para obtener un objeto Destino por su ID
       @Query("SELECT * FROM Destino")
       List<Destino> obtenerDestinos();
}