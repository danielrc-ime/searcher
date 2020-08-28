package com.test.liverpoolsearcher.Control;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.liverpoolsearcher.Model.DataForList;
import com.test.liverpoolsearcher.R;

import java.util.List;

public class CustomListActivity extends ArrayAdapter<DataForList> {

    List<DataForList> productList;
    Context context;
    int resource;


    public CustomListActivity(@NonNull Context context, int resource, List<DataForList> productList) {
        super(context, resource, productList);
        this.productList = productList;
        this.context = context;
        this.resource = resource;
    }

    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //we need to get the view of the xml for our list item
        //And for this we need a layoutinflater
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        //getting the view
        View view = layoutInflater.inflate(resource, null, false);

        //getting the view elements of the list from the view
        ImageView imageView = view.findViewById(R.id.imageViewProduct);
        TextView textViewPrecio = view.findViewById(R.id.textViewPrecio);
        TextView textViewUbicacion = view.findViewById(R.id.textViewUbicacion);
        TextView textViewTitulo = view.findViewById(R.id.textViewTitulo);

        //getting the data of the specified position
        DataForList data = productList.get(position);

        //adding values to the list item
        //imageView.setImageDrawable(context.getResources().getDrawable(data.getImagen()));
        //imageView.setImageBitmap(data.getImagen());
        Picasso.with(context).load(data.getImagen()).into(imageView);
        textViewTitulo.setText(data.getTitulo());
        textViewPrecio.setText(data.getPrecio());
        textViewUbicacion.setText(data.getUbicacion());

        //finally returning the view
        return view;
    }

}