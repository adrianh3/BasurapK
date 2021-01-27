package adrian.ejempl.basurapk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.basurapk.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class administrador extends AppCompatActivity{

    ListView idLista;
    String Equipo="9";
    String EncargadoI;

    ImageView btnruta9,btnruta10,btnruta11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final Bundle extras = getIntent().getExtras();

        EncargadoI =extras.getString("EncargadoID");

        btnruta9 = findViewById(R.id.btnruta9);
        btnruta10 = findViewById(R.id.btnruta10);
        btnruta11 = findViewById(R.id.btnruta11);

        btnruta9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                idLista = findViewById(R.id.idLista);
                String consulta="https://basurapk.com/webservices/bajarRecorridoNueve.php";
                EnviarRecibirDatos(consulta);

                Toast.makeText(getApplicationContext(),"Info. Ruta 9 Cargada", Toast.LENGTH_SHORT).show();

            }
        });



        btnruta10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                idLista = findViewById(R.id.idLista);
                String consulta="https://basurapk.com/webservices/bajarRecorridoDiez.php";
                EnviarRecibirDatos(consulta);

                Toast.makeText(getApplicationContext(),"Info. Ruta 10 Cargada", Toast.LENGTH_SHORT).show();


            }
        });



        btnruta11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                idLista = findViewById(R.id.idLista);
                String consulta="https://basurapk.com/webservices/bajarRecorridoOnce.php";
                EnviarRecibirDatos(consulta);

                Toast.makeText(getApplicationContext(),"Info. Ruta 11 Cargada", Toast.LENGTH_SHORT).show();

            }
        });



        ImageView imgNotif = (ImageView)findViewById(R.id.imgNotif);
        imgNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent6= new Intent(view.getContext(),NuevaNotificacion.class);

                intent6.putExtra("EncargadoIDs",EncargadoI);

                startActivityForResult(intent6, 0);


            }
        });


        ImageView imgBuzon = (ImageView)findViewById(R.id.imgBuzon);
        imgBuzon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent9= new Intent(view.getContext(),Buzon.class);
                startActivityForResult(intent9, 0);

            }
        });


        ImageView imageView5 = (ImageView)findViewById(R.id.imageView5);


        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2= new Intent(view.getContext(),MainActivity.class);
                startActivityForResult(intent2, 0);

            }
        });




        ImageView imgActualiza =findViewById(R.id.imgActualiza);

        imgActualiza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                idLista = findViewById(R.id.idLista);
                String consulta="https://basurapk.com/webservices/bajarRecorridos.php";
                EnviarRecibirDatos(consulta);

                Toast.makeText(getApplicationContext(),"Actualizado", Toast.LENGTH_SHORT).show();


            }
        });

        idLista = findViewById(R.id.idLista);
        String consulta="https://basurapk.com/webservices/bajarRecorridos.php";
        EnviarRecibirDatos(consulta);

    }


    public void EnviarRecibirDatos(String URL){

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {

                response = response.replace("][",",");
                if (response.length()>0){
                    try {
                        JSONArray ja = new JSONArray(response);
                        Log.i("sizejson",""+ja.length());
                        CargarListView(ja);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        queue.add(stringRequest);
    }

    public void CargarListView(JSONArray ja){

        ArrayList<String> lista = new ArrayList<>();

        for(int i=0;i<ja.length();i+=12){

            try {
                //Fecha
                lista.add("\n"+ja.getString(i)+"\n"+

                        "Hrs: "+ja.getString(i+1)+" a "+ja.getString(i+2)+"\n"+
                        "Chofer Sust? "+ja.getString(i+3)+"\n"+
                        "Camion Sust? "+ja.getString(i+4)+"\n"+
                        "Chofer: "+ja.getString(i+5)+"\n"+
                        "Telefono: "+ja.getString(i+6)+"\n" +
                        "Camión: "+ja.getString(i+7)+"\n" +
                        "Ruta: : "+ja.getString(i+8)+"\n" +
                        "Km Aprox: "+ja.getString(i+9)+" Km.\n" +
                        "Litros Aprox: "+ja.getString(i+10)+" Lts. \n" +
                        "Comentarios: "+ja.getString(i+11)+"\n");



            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        idLista.setAdapter(adaptador);


    }


    @Override
    public void onBackPressed (){

        Intent intent2= new Intent(getApplicationContext(),MainActivity.class);
        startActivityForResult(intent2, 0);

    }



}
