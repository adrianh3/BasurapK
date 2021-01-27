package adrian.ejempl.basurapk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class Buzon extends AppCompatActivity {
    ListView listaResultado;
    ImageView back,imgActualizar;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buzon);

        listaResultado= findViewById(R.id.lvLista1);


        init();
        showPDialog1();

        //Img

        imgActualizar=findViewById(R.id.btnActualiza);
        imgActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Buzón Actualizado...!", Toast.LENGTH_SHORT).show();

            }
        });


        ImageView imglogo=(ImageView)findViewById(R.id.imglogo);


        imglogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent3 = new Intent(view.getContext(),MainActivity.class);
                startActivityForResult(intent3, 0);

            }
        });

        String consulta="https://basurapk.com/webservices/bajarBuzon.php";

        EnviarRecibirDatos(consulta);

    }

    private void init(){

        this.progressDialog=new ProgressDialog(this);

    }

    private void showPDialog1(){

        progressDialog.setCancelable(false);
        progressDialog.setTitle("Cargando Mensajes Más Recientes");
        progressDialog.setMessage("Por Favor Espere Un Momento..!");
        progressDialog.show();

    }

    public void openDialog(){
        DialogBorrar dialogoBorrar = new DialogBorrar();
        dialogoBorrar.show(getSupportFragmentManager(),"Ejemplo Administrador");

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

        for(int i=0;i<ja.length();i+=4){

            try {

                lista.add("\n"+ja.getString(i)+"\n"+ja.getString(i+1)+"\n"+ja.getString(i+2)+"\n"+ja.getString(i+3)+"\n");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            progressDialog.dismiss();

        }


        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        listaResultado.setAdapter(adaptador);



    }


}
