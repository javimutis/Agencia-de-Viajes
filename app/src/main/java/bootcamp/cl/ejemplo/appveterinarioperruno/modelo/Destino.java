package bootcamp.cl.ejemplo.appveterinarioperruno.modelo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Destino {

    @PrimaryKey(autoGenerate = true)
    private long idDestino;
    private String rutaImagen;
    private String nombreDestino;
    private String tiempoDestino;
    private String tiempoDestino2;
    private String valorDestinoRecibida;
    private boolean localidadDestino;

    public Destino() {
    }

    public Destino(long idDestino, String rutaImagen, String nombreDestino, String tiempoDestino, String tiempoDestino2, String valorDestinoRecibida, boolean localidadDestino) {
        this.idDestino = idDestino;
        this.rutaImagen = rutaImagen;
        this.nombreDestino = nombreDestino;
        this.tiempoDestino = tiempoDestino;
        this.tiempoDestino2 = tiempoDestino2;
        this.valorDestinoRecibida = valorDestinoRecibida;
        this.localidadDestino = localidadDestino;
    }

    public long getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(long idDestino) {
        this.idDestino = idDestino;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getNombreDestino() {
        return nombreDestino;
    }

    public void setNombreDestino(String nombreDestino) {
        this.nombreDestino = nombreDestino;
    }

    public String getTiempoDestino() {
        return tiempoDestino;
    }

    public void setTiempoDestino(String tiempoDestino) {
        this.tiempoDestino = tiempoDestino;
    }

    public String getTiempoDestino2() {
        return tiempoDestino2;
    }

    public void setTiempoDestino2(String tiempoDestino2) {
        this.tiempoDestino2 = tiempoDestino2;
    }

    public String getValorDestinoRecibida() {
        return valorDestinoRecibida;
    }

    public void setValorDestinoRecibida(String valorDestinoRecibida) {
        this.valorDestinoRecibida = valorDestinoRecibida;
    }

    public boolean isLocalidadDestino() {
        return localidadDestino;
    }

    public void setLocalidadDestino(boolean localidadDestino) {
        this.localidadDestino = localidadDestino;
    }
}
