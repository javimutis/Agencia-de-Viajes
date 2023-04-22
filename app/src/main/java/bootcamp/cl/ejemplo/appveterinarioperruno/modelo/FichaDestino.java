package bootcamp.cl.ejemplo.appveterinarioperruno.modelo;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Destino.class, parentColumns = "idDestino", childColumns = "destinoId",onDelete = ForeignKey.CASCADE))
public class FichaDestino {

    //Declaración de las variables que almacenarán los datos de la FichaDestino
    @PrimaryKey(autoGenerate = true)
    private long idFichaDestino;
    private String descripcionFichaDestino;
    private float estrellasValoracion;
    private long destinoId; // Agregar variable para la relación con Destino

    public FichaDestino() {
    }

    public FichaDestino(long idFichaDestino, String descripcionFichaDestino, float estrellasValoracion, long destinoId) {
        this.idFichaDestino = idFichaDestino;
        this.descripcionFichaDestino = descripcionFichaDestino;
        this.estrellasValoracion = estrellasValoracion;
        this.destinoId = destinoId;
    }

    public long getIdFichaDestino() {
        return idFichaDestino;
    }

    public void setIdFichaDestino(long idFichaDestino) {
        this.idFichaDestino = idFichaDestino;
    }

    public String getDescripcionFichaDestino() {
        return descripcionFichaDestino;
    }

    public void setDescripcionFichaDestino(String descripcionFichaDestino) {
        this.descripcionFichaDestino = descripcionFichaDestino;
    }

    public float getEstrellasValoracion() {
        return estrellasValoracion;
    }

    public void setEstrellasValoracion(float estrellasValoracion) {
        this.estrellasValoracion = estrellasValoracion;
    }

    public long getDestinoId() {
        return destinoId;
    }

    public void setDestinoId(long destinoId) {
        this.destinoId = destinoId;
    }
}