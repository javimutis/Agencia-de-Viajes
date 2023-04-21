package bootcamp.cl.ejemplo.appveterinarioperruno;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import bootcamp.cl.ejemplo.appveterinarioperruno.basedatos.AppDataBase;
import bootcamp.cl.ejemplo.appveterinarioperruno.modelo.Destino;
import bootcamp.cl.ejemplo.appveterinarioperruno.modelo.FichaDestino;

public class VerFichaActivity extends AppCompatActivity {

    private String campoDestino = "";
    private String campoDias = "";
    private String campoNoches = "";
    private String campoValor = "";
    private boolean localidadDestino = false;
    private String descripPaquete = "";
    private float estrellasValoracion = 0.0f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_ficha);

        long destinoId = getIntent().getLongExtra("destino_id", -1);
        AppDataBase appDataBase = AppDataBase.getDatabase(this);
        Destino destino = appDataBase.destinoDao().obtenerDestinosById(destinoId);
        FichaDestino fichaDestino = appDataBase.fichaDestinoDao().getFichaDestinoByDestinoId(destinoId);

        if (destino != null && fichaDestino != null) {
            campoDestino = destino.getNombreDestino();
            campoDias = destino.getTiempoDestino();
            campoNoches = destino.getTiempoDestino2();
            campoValor = destino.getValorDestinoRecibida();
            localidadDestino = destino.isLocalidadDestino();
            descripPaquete = fichaDestino.getDescripcionFichaDestino();
            estrellasValoracion = fichaDestino.getEstrellasValoracion();

            TextView nombreFichaItemTextView = findViewById(R.id.nombreFichaItem);
            nombreFichaItemTextView.setText(campoDestino);

            ImageView imagenFichaItem = findViewById(R.id.imagenFichaItem);
            imagenFichaItem.setImageURI(Uri.parse(destino.getRutaImagen()));

            TextView diasFichaItemTextView = findViewById(R.id.diasFichaItem);
            diasFichaItemTextView.setText(campoDias);

            TextView nocheFichaItemTextView = findViewById(R.id.nocheFichaItem);
            nocheFichaItemTextView.setText(campoNoches);

            TextView valorFichaItemTextView = findViewById(R.id.valorFichaItem);
            valorFichaItemTextView.setText(campoValor);

            RatingBar valoracionFichaItemRatingBar = findViewById(R.id.estrellasFichaItemRatingBar);
            valoracionFichaItemRatingBar.setRating(estrellasValoracion);

        }
    }
}