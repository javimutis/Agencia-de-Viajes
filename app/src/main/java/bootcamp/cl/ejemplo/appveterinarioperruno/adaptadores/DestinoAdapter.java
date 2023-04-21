package bootcamp.cl.ejemplo.appveterinarioperruno.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import bootcamp.cl.ejemplo.appveterinarioperruno.databinding.FichaItemBinding;
import bootcamp.cl.ejemplo.appveterinarioperruno.modelo.Destino;

/*
Se le pasa por paremetro el viewolder que queremos pintar
DestinoAdapter.ViewHolder,esto es herencia  la clase ViewHolder esta creada acá mismo al finla del código
 */
// Esta línea importa las librerías necesarias para la implementación del adaptador.

public class DestinoAdapter extends RecyclerView.Adapter<DestinoAdapter.ViewHolder> {

    private final List<Destino> mData;// Lista de objetos de tipo Destino.

    private OnItemClickListener onItemClickListener;

    public DestinoAdapter(List<Destino> itemList, Context context) {
        // Para inflar la vista XML del ViewHolder.
        LayoutInflater mInflater = LayoutInflater.from(context);
        // Contexto de la aplicación.
        this.mData = itemList;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /*
    Este metodo se llama x cantidad de veces , dependiendo la cantidad de objetos que le lleguen
    si tengo 5 destinos , se creará 5 veces automáticamente.
    Se le envía al ViewHolder la vista xml

     */
    @NonNull
    @Override
    public DestinoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FichaItemBinding binding = FichaItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        // Crea una nueva vista del ViewHolder inflando el XML.
        return new DestinoAdapter.ViewHolder(binding);
    }

   /*
   Cada obeto que se pintará en la lista tiene una posición , este método detecta la posicion
   del objeto dentro de la lista
    */
/*
   Cada obeto que se pintará en la lista tiene una posición , este método detecta la posicion
   del objeto dentro de la lista
    */
    @Override
    public void onBindViewHolder(final DestinoAdapter.ViewHolder holder, final int position) {
        // Asigna los valores de los destinos a las vistas del ViewHolder.
        holder.bindData(mData.get(position));
    }
    // Se crea la interfaz en Java para que esta sea llamada desde cualquier otro lugar
    public interface OnItemClickListener{
        void onItemClick(Destino destino);
    }
    // Setro del listener como cualquier otro atributo
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    /*
      Esta es la clase ViewHolder que le pasasamos al adaptador
      Básicamente lo que hace un ViewHolder es recibir el layout del item (la fila de la lista)
      y le indica que elementos(views) quiero pintar
       */

      public class ViewHolder extends RecyclerView.ViewHolder {
        // Declaración de las vistas del ViewHolder.
        final FichaItemBinding binding;

            /*
        Constructor del ViewHolder
        recibe como parametro el layot (la vista) que acá llega
        como itemView
         */
        ViewHolder(FichaItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
       /**
        * Puente que sirve para pasarle el objeto destino
        * y seterales los datos a las vistas , es como simular un binding
        */
        public void bindData(final Destino item) {
            // Asigna los valores del objeto Destino a las vistas del ViewHolder.

                //Drawable d = context.getDrawable(item.getImage());
                binding.nombreDestinoItem.setText(item.getNombreDestino());
                binding.diaItem.setText(item.getTiempoDestino());
                binding.nocheItem.setText(item.getTiempoDestino2());
                binding.valorDestinoItem.setText(item.getValorDestinoRecibida());

                binding.getRoot().setOnClickListener(view -> onItemClickListener.onItemClick(item));

                binding.executePendingBindings();
            }
        }

      }
