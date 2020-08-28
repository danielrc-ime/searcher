package com.test.liverpoolsearcher.Control;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.toolbox.JsonObjectRequest;
import com.test.liverpoolsearcher.Model.DataForList;
import com.test.liverpoolsearcher.Model.RequestAuthListener;
import com.test.liverpoolsearcher.Model.RequestingJSON;
import com.test.liverpoolsearcher.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements RequestAuthListener {

    private String TAG = this.getClass().getSimpleName();

    RequestingJSON requestingJSON;
    //RequestAuthListener requestAuthListener;
    EditText editTextProducto;
    Button buttonBuscar;
    RadioButton radioButton10, radioButton15;
    ListView listView;
    List<DataForList> productList;
    CustomListActivity adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextProducto = findViewById(R.id.editTextTextProducto);
        buttonBuscar = findViewById(R.id.buttonBuscar);
        requestingJSON = new RequestingJSON(this);
        radioButton10 = findViewById(R.id.radioButton10);
        listView = findViewById(R.id.ListView);
        productList = new ArrayList<>();

        //creating the adapter
        adapter = new CustomListActivity(this, R.layout.activity_custom_list, productList);
        //attaching adapter to the listview
        listView.setAdapter(adapter);

        buttonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String producto = editTextProducto.getText().toString();
                String numItems;
                if (radioButton10.isChecked()){
                    numItems = "10";
                } else {
                    numItems = "15";
                }
                requestingJSON.setRequestingJSON(producto,"1",numItems, getApplicationContext());
                requestingJSON.doRequest();
            }
        });
    }

    @Override
    public void onSuccesRequest(JSONObject jsonObject) {
        try {
            String titulo = jsonObject.getString("productDisplayName");
            String precio = "Precio: " + jsonObject.getString("promoPrice");
            String ubicacion = "Ubicacion: " + jsonObject.getString("category");
            String urlImagen = jsonObject.getString("smImage");

            productList.add(new DataForList(urlImagen,titulo,precio,ubicacion));
            adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailureRequest(Exception e) {
        Toast.makeText(this,"Hubo un error :(",Toast.LENGTH_SHORT).show();
    }

}