package adrian.ejempl.basurapk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

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

public class notificaciones extends AppCompatActivity {

    ImageView imageView;
    ListView listaResultado;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaciones);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        init();
        showPDialog1();

        //----

        imageView=findViewById(R.id.imageView);
        final Animation zoomAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom);
        imageView.startAnimation(zoomAnimation);

        //---

        listaResultado= findViewById(R.id.lvLista);

        ImageView imageView = (ImageView)findViewById(R.id.imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent3 = new Intent(view.getContext(),MainActivity.class);
                startActivityForResult(intent3, 0);

            }
        });

        ImageView imgRefresca =findViewById(R.id.imgRefresca);

        imgRefresca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openDialogRegresh();
                String Consulta="https://basurapk.com/webservices/bajarNoticias.php";
                EnviarRecibirDatos(Consulta);

            }
        });




        String Consulta="https://basurapk.com/webservices/bajarNoticias.php";
        EnviarRecibirDatos(Consulta);

    }

    private void init(){

        this.progressDialog=new ProgressDialog(this);

    }

    private void showPDialog1(){

        progressDialog.setCancelable(false);
        progressDialog.setTitle("Cargando Noticias Más Recientes");
        progressDialog.setMessage("Por Favor Espere Un Momento..!");
        progressDialog.show();

    }

    public void openDialogRegresh(){

        DialogoRefresh dialogoRefresh = new DialogoRefresh();

        dialogoRefresh.show(getSupportFragmentManager(),"Ejemplo Notificación");

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

        for(int i=0;i<ja.length();i+=2){

            try {

                lista.add("\n"+ja.getString(i)+"\n"+ja.getString(i+1)+"\n");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        progressDialog.dismiss();

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        listaResultado.setAdapter(adaptador);



    }



}
