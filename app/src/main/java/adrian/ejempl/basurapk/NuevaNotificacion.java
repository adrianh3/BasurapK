package adrian.ejempl.basurapk;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.basurapk.R;

import java.util.HashMap;
import java.util.Map;

public class NuevaNotificacion extends AppCompatActivity {


    EditText edtNuevaNoticia;
    String Encargados,FechaHoy;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_notificacion);


        final Bundle extras = getIntent().getExtras();

        Encargados =extras.getString("EncargadoIDs");

        edtNuevaNoticia=findViewById(R.id.edtNuevaNoticia);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ImageView imageView13 = (ImageView)findViewById(R.id.imageView13);

        imageView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent4 = new Intent(view.getContext(),MainActivity.class);
                startActivityForResult(intent4, 0);

            }
        });


        //Inicio captura de fecha

        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        int monthDay = today.monthDay;
        int month = (today.month)+1;
        int Year = today.year;


        String dia =String.valueOf(monthDay);
        String mes=String.valueOf(month);
        String year=String.valueOf(Year);

        FechaHoy=year + "-" + mes +"-" + dia;

        //Fin Captura de fecha

        //---->

        //inicio Captura de datos

        Button btnEnviarN = findViewById(R.id.btnEnviarN);

        btnEnviarN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            if (edtNuevaNoticia.getText().toString().isEmpty()) {
                     CamposVacios();

            }else {
                //Aqui se inicia
                init();
                showPDialog1();
                ejecutarServicio("https://basurapk.com/webservices/crearNoticia.php");
            }
            }
        });

        //Fin Captura de datos

    }

    private void init(){

        this.progressDialog=new ProgressDialog(this);

    }

    private void showPDialog1(){

        progressDialog.setCancelable(false);
        progressDialog.setTitle("Enviando noticia");
        progressDialog.setMessage("Por Favor Espere Un Momento..!");
        progressDialog.show();

    }

    public void openDialog(){
        DialogNoticia dialognoticia = new DialogNoticia();
        dialognoticia.show(getSupportFragmentManager(),"Ejemplo Administrador");

        edtNuevaNoticia.setText("");

    }

    public void CamposVacios(){

        dialogoCamposVacios dialogoCamposVacios = new dialogoCamposVacios();
        dialogoCamposVacios.show(getSupportFragmentManager(),"Ejemplo Administrador");

    }
    
    public void dialogonoEnvio(){

        MensajeNoEnviado mensajeNoEnviado = new MensajeNoEnviado();
        mensajeNoEnviado.show(getSupportFragmentManager(),"Ejemplo Administrador");
    }

    private void ejecutarServicio(String URL){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Aqui se detiene

                progressDialog.dismiss();

                openDialog();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                dialogonoEnvio();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();

                parametros.put("Fecha",FechaHoy);
                parametros.put("Noticia",edtNuevaNoticia.getText().toString());
                parametros.put("Encargado",Encargados);


                return parametros;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this );
        requestQueue.add(stringRequest);
    }


}
