package tc2.mamendez.top20movies2017;

/**
 * Created by Mauro on 3/23/2018.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * {@link BaseAdapter} para poblar peliculas en un grid view
 */

public class AdaptadorDePeliculas extends BaseAdapter {
    private Context context;

    public AdaptadorDePeliculas(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return MainActivity.Peliculas.size();
    }

    @Override
    public Pelicula getItem(int position) {
        return MainActivity.Peliculas.get(position);
    }

    @Override
    public long getItemId(int i) {
        return getItem(i).getNumero();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item, viewGroup, false);
        }

        ImageView imagenPelicula = (ImageView) view.findViewById(R.id.imagen_pelicula);
        TextView infoPelicula = (TextView) view.findViewById(R.id.info_pelicula);

        final Pelicula item = getItem(position);
        imagenPelicula.setImageBitmap(item.getPortada());
        infoPelicula.setText(item.toString());

        return view;
    }

}
