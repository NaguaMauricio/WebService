package com.example.webservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {
    TextView txtSaludo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtSaludo = (TextView)findViewById(R.id.txtSaludo);
       // Bundle bundle = this.getIntent().getExtras();
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://api.androidhive.info/contacts/", datos,
        MainActivity.this, MainActivity.this);
        ws.execute("");


    }


    @Override
    public void processFinish(String result) throws JSONException {
        Log.i("processFinish", result);
        ArrayList<String> contactList = null;
        String datosContac = null;
        JSONObject jsonObj= new JSONObject(result);
//Getting Json Array node
        JSONArray contacts=jsonObj.getJSONArray("contacts");


        for(int i=0;i<contacts.length();i++){
            JSONObject c=contacts.getJSONObject(i);
            String id = c.getString("id");
            String name =c.getString("name");
            String email =c.getString("email");
            String address =c.getString("address");
            String gender = c.getString("gender");


            JSONObject phone =c.getJSONObject("phone");
            String mobile = phone.getString("mobile");
            String home = phone.getString("home");
            String office = phone.getString("office");
            HashMap<String,String> contact= new HashMap<>();

            contact.put("id",id);
            contact.put("name",name);
            contact.put("email",email);
            contact.put("mobile",mobile);

            datosContac = datosContac + contact.put("id", id);
            datosContac = datosContac + contact.put("name", name);

        txtSaludo.setText("");


        }
    }
}
