package bootcamp.cl.ejemplo.appveterinarioperruno.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import bootcamp.cl.ejemplo.appveterinarioperruno.R;
import bootcamp.cl.ejemplo.appveterinarioperruno.modelo.Destino;

/*
Se le pasa por paremetro el viewolder que queremos pintar
DestinoAdapter.ViewHolder,esto es herencia  la clase ViewHolder esta creada acá mismo al finla del código
 */
public class DestinoAdapter extends RecyclerView.Adapter<DestinoAdapter.ViewHolder> {

    private List<Destino> mData;
    private LayoutInflater mInflater;
    private Context context;


    public DestinoAdapter(List<Destino> itemList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /*
    Este metodo se llama x cantidad de veces , dependiendo la cantidad de objetos que le lleguen
    si tengo 5 animales , se creará 5 veces automáticamente.
    Se le envía al ViewHolder la vista xml

     */
    @Override
    public DestinoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.ficha_item, null);
        return new DestinoAdapter.ViewHolder(view);
    }



   /*
   Cada obeto que se pintará en la lista tiene una posición , este método detecta la posicion
   del objeto dentro de la lista
    */
    @Override
    public void onBindViewHolder(final DestinoAdapter.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }


   /*
   Esta es la clase ViewHolder que le pasasamos al adaptador
   Básicamente lo que hace un ViewHolder es recibir el layout del item (la fila de la lista)
   y le indica que elementos(views) quiero pintar
    */
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView rutaImagen;
        TextView nombreDestino, tiempoDestino, tiempoDestino2, valorDestinoRecibida, localidadDestino;

        /*
        Constructor del VieqHolder
        recibe como parametro el layot (la vista) que acá llega
        como itemView
         */
        ViewHolder(View itemView) {
            super(itemView);

            rutaImagen = itemView.findViewById(R.id.imagenDestinosItem);
            nombreDestino = itemView.findViewById(R.id.nombreDestinoItem);
            tiempoDestino = itemView.findViewById(R.id.diaItem);
            tiempoDestino2 = itemView.findViewById(R.id.nocheItem);
            valorDestinoRecibida= itemView.findViewById(R.id.valorDestinoItem);

        }

       /**
        * Puente que sirve para pasarle el objeto animal
        * y seterales los datos a las vistas , es como simular un binding
        * @param item
        */
        public void bindData(final Destino item) {
            //Drawable d = context.getDrawable(item.getImage());
            nombreDestino.setText(item.getNombreDestino());
            tiempoDestino.setText(item.getTiempoDestino());
            tiempoDestino2.setText(item.getTiempoDestino2());
            valorDestinoRecibida.setText(item.getValorDestinoRecibida());

        }

}
}
