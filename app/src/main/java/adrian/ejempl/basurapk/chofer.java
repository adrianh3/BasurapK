package adrian.ejempl.basurapk;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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

public class chofer extends AppCompatActivity {

    Double latitud;
    Double longitud;
    String longitudD;
    String latitudD;
    private Context thisContext=this;
    String EquipoCan;
    Double kilometros;

    private CountDownTimer MapaCountDownTimerr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chofer);


        final Bundle extras = getIntent().getExtras();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ImageView imageView12 =(ImageView)findViewById(R.id.imageView12);

        imageView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(view.getContext(),MainActivity.class);
                startActivityForResult(intent3, 0);
            }
        });


        final Button btnIniciar=findViewById(R.id.btnIniciar);
        final Button btnCancelar=findViewById(R.id.btnCancelar);
        final Button btnFinalizar=findViewById(R.id.btnFinalizar);

        EquipoCan =extras.getString("EquipoAc");

        btnCancelar.setEnabled(false);
        btnFinalizar.setEnabled(false);

        locationStart();

        //----------------

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //locationStart();
                //Coordenadas();



                Intent serviceIntent = new Intent(thisContext,servicioChofer.class);
                serviceIntent.putExtra("EquipoPP",EquipoCan);
                ContextCompat.startForegroundService(thisContext,serviceIntent);


                btnCancelar.setEnabled(true);
                btnFinalizar.setEnabled(true);
                btnIniciar.setEnabled(false);

            }
        });






        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent serviceIntent = new Intent(thisContext,servicioChofer.class);
                stopService(serviceIntent);


                btnIniciar.setEnabled(false);

                if (EquipoCan.equals("9")) {

                    latitud=17.962697;
                    longitud=-102.198661;
                    
                    ejecutarServicio("https://basurapk.com/webservices/mandarUbicacion.php");
                }

                if (EquipoCan.equals("10")) {

                    latitud=17.962899;
                    longitud=-102.19832;

                    ejecutarServicio("https://basurapk.com/webservices/mandarUbicacion.php");
                }

                if (EquipoCan.equals("11")) {

                    latitud=17.963150;
                    longitud=-102.197943;

                    ejecutarServicio("https://basurapk.com/webservices/mandarUbicacion.php");
                }

                String horaInicio =extras.getString("HoraInicio");
                Intent j = new Intent(chofer.this,Formulario.class);
                j.putExtra("HoraInicios",horaInicio);
                j.putExtra("EquipoEnv",EquipoCan);
                startActivity(j);


            }
        });


        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent serviceIntent = new Intent(thisContext,servicioChofer.class);
                stopService(serviceIntent);


                btnIniciar.setEnabled(false);

                if (EquipoCan.equals("9")) {

                    latitud=17.962697;
                    longitud=-102.198661;


                    ejecutarServicio("https://basurapk.com/webservices/mandarUbicacion.php");
                }

                if (EquipoCan.equals("10")) {

                    latitud=17.962899;
                    longitud=-102.19832;

                    ejecutarServicio("https://basurapk.com/webservices/mandarUbicacion.php");
                }

                if (EquipoCan.equals("11")) {

                    latitud=17.963150;
                    longitud=-102.197943;

                    ejecutarServicio("https://basurapk.com/webservices/mandarUbicacion.php");
                }

                String horaInicio =extras.getString("HoraInicio");
                Intent j = new Intent(chofer.this,Formulario.class);
                j.putExtra("HoraInicios",horaInicio);
                j.putExtra("EquipoEnv",EquipoCan);
                startActivity(j);


            }
        });


    }

//-----------------------------NUEVO------------------------------

    private void locationStart() {
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localizacion Local = new Localizacion();
        Local.setMainActivity(this);

        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }

        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) Local);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) Local);



    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationStart();
                return;
            }
        }
    }


    public class Localizacion implements LocationListener {
        chofer mainActivity;
        public chofer getMainActivity() {
            return mainActivity;
        }
        public void setMainActivity(chofer mainActivity) {
            this.mainActivity = mainActivity;
        }
        @Override
        public void onLocationChanged(Location loc) {



            latitud = loc.getLatitude();
            longitud= loc.getLongitude();




        }
        @Override
        public void onProviderDisabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es desactivado
            Toast.makeText(getApplicationContext(),"GPS Desactivado" ,Toast.LENGTH_LONG).show();
        }
        @Override
        public void onProviderEnabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es activado
            Toast.makeText(getApplicationContext(),"El GPS a vuelto a tener señal" ,Toast.LENGTH_LONG).show();
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.AVAILABLE:
                    Log.d("debug", "LocationProvider.AVAILABLE");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                    break;
            }
        }
    }


    public void Coordenadas(){



        MapaCountDownTimerr = new CountDownTimer(29999999, 15000) {



            public void onTick(long millisUntilFinished) {


                if (longitud==null || latitud ==null){

                    Toast.makeText(getApplicationContext(),"CARGANDO....!" ,Toast.LENGTH_LONG).show();

                }else{

                ejecutarServicio("https://basurapk.com/webservices/mandarUbicacion.php");
                Toast.makeText(getApplicationContext(),"Longitud " + latitud +"  Latitud  " + longitud,Toast.LENGTH_LONG).show();


                }
            }

            public void onFinish() {


            }
        }.start();


    }


    private void ejecutarServicio(String URL){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),"UPDATE INCORRECTO", Toast.LENGTH_SHORT).show();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();

                longitudD = Double.toString(longitud);
                latitudD = Double.toString(latitud);



                parametros.put("latitud",latitudD);
                parametros.put("longitud",longitudD);
                parametros.put("camion",EquipoCan);

                return parametros;

            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this );
        requestQueue.add(stringRequest);
    }



    @Override
    public void onBackPressed (){

        Intent intent2= new Intent(getApplicationContext(),MainActivity.class);
        startActivityForResult(intent2, 0);

    }


}
