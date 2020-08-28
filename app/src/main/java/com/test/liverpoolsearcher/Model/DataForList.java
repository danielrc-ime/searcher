package com.test.liverpoolsearcher.Model;

import android.graphics.Bitmap;
import android.media.Image;
import android.widget.ImageView;

public class DataForList {
    String titulo, precio, ubicacion, imagen;

    public DataForList(String imagen, String titulo, String precio,String ubicacion){
        this.titulo = titulo;
        this.precio = precio;
        this.ubicacion = ubicacion;
        this.imagen = imagen;
    }

    public String getImagen() {
        return imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getPrecio() {
        return precio;
    }

    public String getUbicacion() {
        return ubicacion;
    }
}
