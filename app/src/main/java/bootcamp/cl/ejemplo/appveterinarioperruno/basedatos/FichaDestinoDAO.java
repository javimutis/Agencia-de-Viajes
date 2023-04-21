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

// Hacer algo con la lista de fichas obtenida (por ejemplo, mostrarla en una vista)

    // Inserta una nueva ficha en la tabla FichaDestino y retorna el ID de la ficha insertada
    @Insert
    long insertarFichaDestino(FichaDestino ficha);

    // Actualiza una ficha existente en la tabla FichaDestino
    @Update
    void actualizarFichaDestino(FichaDestino ficha);

    // Elimina una ficha existente de la tabla FichaDestino
    @Delete
    void eliminarFichaDestino(FichaDestino ficha);

    // Obtiene todas las fichas registradas para un destino específico, según el ID de dicho destino
    @Query("SELECT * FROM FichaDestino WHERE destino_id = :destinoId")
    List<FichaDestino> obtenerFichasDestino(long destinoId);


}
