package com.example.tarean2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Inventario extends AppCompatActivity {
    daoContacto dao;
    Adaptador adaptador;
    ArrayList<Contacto> lista;
    Contacto c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);
        dao = new daoContacto(Inventario.this);
        lista = dao.verTodo();
        adaptador = new Adaptador(this, lista, dao);
        ListView list = findViewById(R.id.lista);
        ImageButton insertar = findViewById(R.id.btnIncertar);
        list.setAdapter(adaptador);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(Inventario.this);
                dialog.setTitle("Nuevo Registro");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.agregar);
                dialog.show();
                final EditText nombre = dialog.findViewById(R.id.et_nombre);
                final EditText categoria = dialog.findViewById(R.id.et_categoria);
                final EditText precio = dialog.findViewById(R.id.et_precio);
                final EditText cantidad = dialog.findViewById(R.id.et_cantidad);
                Button  guardar = dialog.findViewById(R.id.btnagregar);
                guardar.setText("Agregar");
                Button  cancelar = dialog.findViewById(R.id.btncancelar);
                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            c = new Contacto(nombre.getText().toString(),
                                    categoria.getText().toString(),
                                    precio.getText().toString(),
                                    cantidad.getText().toString());

                            dao.insertar(c);
                            lista = dao.verTodo();
                            adaptador.notifyDataSetChanged();
                            dialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(getApplication(), "Error", Toast.LENGTH_SHORT).show();

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
    }
    public void selec(View v){
        Intent selecc = new Intent(this, Maps.class);
        startActivity(selecc);

    }
}