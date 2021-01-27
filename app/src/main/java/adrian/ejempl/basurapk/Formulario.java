package adrian.ejempl.basurapk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.basurapk.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Formulario extends AppCompatActivity {

    String CamionDecide;
    String ChoferDecide;
    String FechaHoy;
    EditText edtComentarios;
    String Hora;
    String EquipoDios;
    String horaInicio;
    private ProgressDialog progressDialog;
    Double latitud;
    Double longitud;
    String longitudD;
    String latitudD;
    String kilometros;
    String gasolina;
    String consumoStr;

    Double kilometrosFormato;
    Double gasolinaFormato;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            //IMAGEN

        ImageView imageView6;
        imageView6=findViewById(R.id.imageView6);
        final Animation zoomAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom);
        imageView6.startAnimation(zoomAnimation);

        //Sacar Kilometros y gasolina Doubles

       Double variablekm = servicioChofer.Global.kilometrosd;

       Double consumo = variablekm * .60;


       kilometrosFormato=(double)Math.round(variablekm * 100d) / 100d;
       gasolinaFormato=(double)Math.round(consumo * 100d) / 100d;

       //STRINGS

        consumoStr = Double.toString(gasolinaFormato);
        kilometros =Double.toString(kilometrosFormato);

        gasolina = consumoStr;

        //Ver que

        //Fin sacar kilometros


        final Date date = new Date();

        Button btnEnviar = findViewById(R.id.btnEnviar);
        edtComentarios = findViewById(R.id.edtComentarios);

       final RadioButton rbSiCamion = findViewById(R.id.rbSiCamion);
       final  RadioButton rbNoCamion = findViewById(R.id.rbNoCamion);

       final RadioButton rbSiChofer = findViewById(R.id.rbSiChofer);
       final RadioButton rbNoChofer = findViewById(R.id.rbNoChofer);


        Bundle extras = getIntent().getExtras();


        //Sacar Fecha


        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        int monthDay = today.monthDay;
        int month = (today.month)+1;
        int Year = today.year;



        String dia =String.valueOf(monthDay);
        String mes=String.valueOf(month);
        String year=String.valueOf(Year);

        FechaHoy=year + "-" + mes +"-" + dia;

        //Fin Sacar Fecha
        //Sacar Horarios



        horaInicio =extras.getString("HoraInicios");

        SimpleDateFormat h = new SimpleDateFormat("h:mm a");
        Hora = h.format(date);

        //Sacar Fin Horarios


        EquipoDios=extras.getString("EquipoEnv");




        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (rbNoChofer.isChecked()){
                    ChoferDecide="No";
                }

                if (rbSiChofer.isChecked()){
                    ChoferDecide="Si";
                }

                if (rbNoCamion.isChecked()){
                    CamionDecide="No";
                }
                if(rbSiCamion.isChecked())
                {
                    CamionDecide="Si";
                }

                init();
                showPDialog1();


                ejecutarServicio("https://basurapk.com/webservices/crearRecorrido.php");


            }
        });


    }

    private void init(){

        this.progressDialog=new ProgressDialog(this);

    }

    private void showPDialog1(){

        progressDialog.setCancelable(false);
        progressDialog.setTitle("Enviando los detalles del recorrido");
        progressDialog.setMessage("Por Favor Espere Un Momento..!");
        progressDialog.show();

    }

    public void openDialogInicio(){
        DialogCancelar exampleDialog = new DialogCancelar();
        exampleDialog.show(getSupportFragmentManager(),"Ejemplo Administrador");
    }

    private void ejecutarServicio(String URL){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Envio de datos realizados correctamente.", Toast.LENGTH_SHORT).show();
                //Intent intent4= new Intent(getApplicationContext(),MainActivity.class);
                //startActivityForResult(intent4, 0);
                finishAffinity();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),"No es posible enviar los datos, Verifica Conexión.", Toast.LENGTH_SHORT).show();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();

                parametros.put("Fecha",FechaHoy);
                parametros.put("CamionSus",CamionDecide);
                parametros.put("PersonalSus",ChoferDecide);
                parametros.put("Comentario",edtComentarios.getText().toString());
                parametros.put("HoraFinal",Hora);
                parametros.put("HoraInicio",horaInicio);
                parametros.put("EquipoRT",EquipoDios);
                parametros.put("kilometros",kilometros);
                parametros.put("gasolina",gasolina);


                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this );
        requestQueue.add(stringRequest);
    }



}
