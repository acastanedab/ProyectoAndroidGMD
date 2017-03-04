package com.proyecto.wasa.proyectoandroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.proyecto.wasa.proyectoandroid.Entidades.Pedido;
import com.proyecto.wasa.proyectoandroid.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Administrador on 04/03/2017.
 */

public class PedidoAdapter  extends BaseAdapter{
    List<Pedido> lista;
    static class ViewHolder {
        TextView textViewCodigoPedido;
        TextView textViewDireccionPedido;
        TextView textViewFechaPedido;
        TextView textViewTotalPedido;
    }

    private LayoutInflater inflater;

    public PedidoAdapter(Context context, List<Pedido> lista){
        inflater= LayoutInflater.from(context);
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.pedido_item,null);
            holder= new ViewHolder();
            holder.textViewCodigoPedido = (TextView)convertView.findViewById(R.id.txtCodigoPedido);
            holder.textViewDireccionPedido = (TextView)convertView.findViewById(R.id.txtDireccionPedido);
            holder.textViewFechaPedido  = (TextView)convertView.findViewById(R.id.txtFechaPedido);
            holder.textViewTotalPedido = (TextView)convertView.findViewById(R.id.txtTotalPedido);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();

        }
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        holder.textViewCodigoPedido.setText(new Long(lista.get(position).getCodigoPedido()).toString());
        holder.textViewDireccionPedido.setText(lista.get(position).getDireccionPedido());
        holder.textViewFechaPedido.setText(df.format(lista.get(position).getFechaPedido()));
        holder.textViewTotalPedido.setText(String.valueOf(lista.get(position).getPrecioTotalPedido()));
        return convertView;
    }
}
