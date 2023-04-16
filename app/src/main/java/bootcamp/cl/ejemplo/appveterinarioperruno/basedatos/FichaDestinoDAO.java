package bootcamp.cl.ejemplo.appveterinarioperruno.basedatos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import bootcamp.cl.ejemplo.appveterinarioperruno.modelo.FichaDestino;

@Dao
public interface FichaDestinoDAO {

    @Insert
    long insertarFichaRegistro(FichaDestino ficha);

    @Update
    void actualizarFichaRegistro(FichaDestino ficha);

    @Delete
    void eliminarFichaRegistro(FichaDestino ficha);

    @Query("SELECT * FROM FichaDestino WHERE destinoId = :destinoId")
    public List<FichaDestino> obtenerFichasRegistro(int destinoId);

}
