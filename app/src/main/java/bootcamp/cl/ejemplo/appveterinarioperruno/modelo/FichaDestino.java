package bootcamp.cl.ejemplo.appveterinarioperruno.modelo;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Destino.class,
        parentColumns = "idDestino",
        childColumns = "destinoId",
        onDelete = ForeignKey.CASCADE))
public class FichaDestino {

    @PrimaryKey(autoGenerate = true)
    private long idFicha;
    private String descripPaquete;
    private float valoracionEstrellas;
    private long destinoId;

    public FichaDestino() {
    }

    public FichaDestino(long idFicha, String descripPaquete, float valoracionEstrellas, long destinoId) {
        this.idFicha = idFicha;
        this.descripPaquete = descripPaquete;
        this.valoracionEstrellas = valoracionEstrellas;
        this.destinoId = destinoId;
    }

    public long getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(long idFicha) {
        this.idFicha = idFicha;
    }

    public String getDescripPaquete() {
        return descripPaquete;
    }

    public void setDescripPaquete(String descripPaquete) {
        this.descripPaquete = descripPaquete;
    }

    public float getValoracionEstrellas() {
        return valoracionEstrellas;
    }

    public void setValoracionEstrellas(float valoracionEstrellas) {
        this.valoracionEstrellas = valoracionEstrellas;
    }

    public long getDestinoId() {
        return destinoId;
    }

    public void setDestinoId(long destinoId) {
        this.destinoId = destinoId;
    }
}
