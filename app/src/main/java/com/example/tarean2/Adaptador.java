package com.example.tarean2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    ArrayList<Contacto> lista;
    daoContacto dao;
    Contacto c;
    Activity a;
    int id=0;
    public Adaptador(Activity a, ArrayList<Contacto> lista, daoContacto dao){
        this.lista = lista;
        this.a = a;
        this.dao = dao;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    @Override
    public int getCount(){
        return lista.size();
    }
    @Override
    public Object getItem(int i){
        c = lista.get(i);
        return null;
    }
    @Override
    public long getItemId(int i){
        c = lista.get(i);
        return c.getId();
    }
    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup){
        View v = view;
        if (v == null){
            LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.editar_eliminar, null);
        }
        c = lista.get(posicion);
        TextView nombre = v.findViewById(R.id.etnombre);
        TextView categoria = v.findViewById(R.id.etcategoria);
        TextView precio = v.findViewById(R.id.etprecio);
        TextView cantidad = v.findViewById(R.id.etcantidad);
        Button editar = v.findViewById(R.id.editar);
        Button eliminar = v.findViewById(R.id.eliminar);
        nombre.setText(c.getNombre());
        categoria.setText(c.getCategoria());
        precio.setText(c.getPrecio());
        cantidad.setText(c.getCantidad());
        editar.setTag(posicion);
        eliminar.setTag(posicion);
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = Integer.parseInt(view.getTag().toString());
                final Dialog dialog = new Dialog(a);
                dialog.setTitle("Ediatar Registro");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.agregar);
                dialog.show();
                final EditText nombre = dialog.findViewById(R.id.et_nombre);
                final EditText categoria = dialog.findViewById(R.id.et_categoria);
                final EditText precio = dialog.findViewById(R.id.et_precio);
                final EditText cantidad = dialog.findViewById(R.id.et_cantidad);
                Button  guardar = dialog.findViewById(R.id.btnagregar);
                Button  cancelar = dialog.findViewById(R.id.btncancelar);
                c = lista.get(pos);
                setId(c.getId());
                nombre.setText(c.getNombre());
                categoria.setText(c.getCategoria());
                precio.setText(c.getPrecio());
                cantidad.setText(c.getCantidad());
                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            c = new Contacto(getId(), nombre.getText().toString(),
                                    categoria.getText().toString(),
                                    precio.getText().toString(),
                                    cantidad.getText().toString());
                            dao.editar(c);
                            lista = dao.verTodo();
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(a, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = Integer.parseInt(view.getTag().toString());
                c = lista.get(posicion);
                setId(c.getId());
                AlertDialog.Builder del = new AlertDialog.Builder(a);
                del.setMessage("Estas seguro de eliminar");
                del.setCancelable(false);
                del.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dao.eliminar(getId());
                        lista = dao.verTodo();
                        notifyDataSetChanged();
                    }
                });
                del.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                del.show();
            }
        });
        return v;
    }
}
