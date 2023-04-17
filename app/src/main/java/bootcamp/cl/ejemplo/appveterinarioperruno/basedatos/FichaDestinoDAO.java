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

    // Inserta una nueva ficha en la tabla FichaDestino y retorna el ID de la ficha insertada
    @Insert
    long insertarFichaRegistro(FichaDestino ficha);
    // Actualiza una ficha existente en la tabla FichaDestino
    @Update
    void actualizarFichaRegistro(FichaDestino ficha);
// Elimina una ficha existente de la tabla FichaDestino
    @Delete
    void eliminarFichaRegistro(FichaDestino ficha);
// Obtiene todas las fichas registradas para un destino específico, según el ID de dicho destino
    @Query("SELECT * FROM FichaDestino WHERE destinoId = :destinoId")
    public List<FichaDestino> obtenerFichasRegistro(int destinoId);

}
