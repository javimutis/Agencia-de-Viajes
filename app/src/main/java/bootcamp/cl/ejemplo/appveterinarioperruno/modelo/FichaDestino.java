package bootcamp.cl.ejemplo.appveterinarioperruno.modelo;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Destino.class, parentColumns = "idDestino", childColumns = "destino_id"))
public class FichaDestino {

    //Declaración de las variables que almacenarán los datos de la FichaDestino
    @PrimaryKey(autoGenerate = true)
    private long idFichaDestino;
    private String tituloFichaDestino;
    private String descripcionFichaDestino;
    private long destino_id; // Agregar variable para la relación con Destino

    public FichaDestino() {
    }

    public FichaDestino(long idFichaDestino, String tituloFichaDestino, String descripcionFichaDestino, long destino_id) {
        this.idFichaDestino = idFichaDestino;
        this.tituloFichaDestino = tituloFichaDestino;
        this.descripcionFichaDestino = descripcionFichaDestino;
        this.destino_id = destino_id;
    }

    public long getIdFichaDestino() {
        return idFichaDestino;
    }

    public void setIdFichaDestino(int idFichaDestino) {
        this.idFichaDestino = idFichaDestino;
    }

    public String getTituloFichaDestino() {
        return tituloFichaDestino;
    }

    public void setTituloFichaDestino(String tituloFichaDestino) {
        this.tituloFichaDestino = tituloFichaDestino;
    }

    public String getDescripcionFichaDestino() {
        return descripcionFichaDestino;
    }

    public void setDescripcionFichaDestino(String descripcionFichaDestino) {
        this.descripcionFichaDestino = descripcionFichaDestino;
    }

    public long getDestino_id() {
        return destino_id;
    }

    public void setDestino_id(long destino_id) {
        this.destino_id = destino_id;
    }
}