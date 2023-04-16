package bootcamp.cl.ejemplo.appveterinarioperruno.basedatos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import bootcamp.cl.ejemplo.appveterinarioperruno.modelo.Destino;

@Dao
public interface DestinoDAO {

    @Insert
    long insertarDestino(Destino destino);

    @Update
    void actualizarDestino(Destino destino);

    @Delete
    void eliminarDestino(Destino destino);

    @Query("SELECT * FROM Destino")
    List<Destino> obtenerDestino();


}
