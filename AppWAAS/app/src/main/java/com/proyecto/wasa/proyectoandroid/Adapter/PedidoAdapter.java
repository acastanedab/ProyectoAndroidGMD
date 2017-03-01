package com.proyecto.wasa.proyectoandroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.proyecto.wasa.proyectoandroid.Entidades.Articulo;
import com.proyecto.wasa.proyectoandroid.R;

import java.util.List;

/**
 * Created by AngelEloy on 28/02/2017.
 */

public class PedidoAdapter extends BaseAdapter {

    List<Articulo> listaPreferencia;
    static class ViewHolder{
        TextView textViewCodigoArticulo;
        TextView textViewNombreArticulo;
        TextView textViewDescripcionArticulo;
        TextView textViewPrecioArticulo;
    }

    private LayoutInflater inflater;

    public PedidoAdapter(Context context, List<Articulo> listaPreferencia) {
        inflater = LayoutInflater.from(context);
        this.listaPreferencia = listaPreferencia;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public int getCount() {
        return listaPreferencia.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.pedido_item,null);

            holder = new ViewHolder();
            holder.textViewCodigoArticulo = (TextView)convertView.findViewById(R.id.txtCodigoArticulo);
            holder.textViewNombreArticulo = (TextView)convertView.findViewById(R.id.txtNombreArticulo);
            holder.textViewDescripcionArticulo = (TextView)convertView.findViewById(R.id.txtDescripcionArticulo);
            holder.textViewPrecioArticulo = (TextView)convertView.findViewById(R.id.txtPrecioArticulo);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.textViewCodigoArticulo.setText(listaPreferencia.get(position).getCodigoArticulo());
        holder.textViewNombreArticulo.setText(listaPreferencia.get(position).getNombreArticulo());
        holder.textViewDescripcionArticulo.setText(listaPreferencia.get(position).getDescripcionArticulo());
        holder.textViewPrecioArticulo.setText(listaPreferencia.get(position).getPrecioArticulo().toString());

        return convertView;
    }
}
