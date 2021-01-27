package adrian.ejempl.basurapk;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.basurapk.R;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginAd extends AppCompatActivity {

    EditText edtUsuario;
    EditText edtContrasena;
    ImageView imgContactos;
    String Encargado;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ad);

            ConnectivityManager nuevo = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo networkInfo = nuevo.getActiveNetworkInfo();

        edtContrasena=findViewById(R.id.edtContrasena);
        edtUsuario=findViewById(R.id.edtUsuario);



        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ImageView imageView4 = (ImageView)findViewById(R.id.imageView4);
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(view.getContext(),MainActivity.class);
                startActivityForResult(intent3, 0);
            }
        });

        imgContactos = findViewById(R.id.imgContactos);

        imgContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent3 = new Intent(view.getContext(),contactoL.class);
                startActivityForResult(intent3, 0);

            }
        });


        //Boton Entrar


        Button btnEntrar = (Button)findViewById(R.id.btnEntrarCh);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edtContrasena.getText().toString().isEmpty() || edtUsuario.getText().toString().isEmpty() ){

                    openDialogInicio2();

                }else{

                    if(networkInfo != null && networkInfo.isConnected()){
                        init();
                        showPDialog1();
                        AccesarUsuario();
                    }else{

                        Toast.makeText(getApplicationContext(),"Sin acceso a internet verifique la conexión y vuelva a entrar a la aplcación" , Toast.LENGTH_SHORT).show();
                        }

                }

            }
        });

        //Fin Boton Entrar


        Button btnSalir = (Button)findViewById(R.id.btnSalirCh);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5 = new Intent(view.getContext(),MainActivity.class);
                startActivityForResult(intent5, 0);
            }
        });


    }

    //Metodo de login

    private void init(){

        this.progressDialog=new ProgressDialog(this);

    }

    private void showPDialog1(){

        progressDialog.setCancelable(false);
        progressDialog.setTitle("Verificando Sus Datos.");
        progressDialog.setMessage("Por Favor Espere Un Momento..!");
        progressDialog.show();

    }

    private void AccesarUsuario(){

        final  String User = edtUsuario.getText().toString();
        final  String Pw = edtContrasena.getText().toString();


        Response.Listener<String> respuesta = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonRespuesta = null;

                try {
                    jsonRespuesta = new JSONObject(response);

                    boolean ok = jsonRespuesta.getBoolean("success");



                    if (ok == true){
                        Encargado = jsonRespuesta.getString("IDEncargado");
                        Toast.makeText(getApplicationContext(), "Acceso Correcto", Toast.LENGTH_SHORT).show();
                        Intent j = new Intent(getApplication(),administrador.class);
                        j.putExtra("EncargadoID",Encargado);
                        progressDialog.dismiss();
                        startActivity(j);
                    }else{
                        progressDialog.dismiss();
                        AlertDialog.Builder alerta = new AlertDialog.Builder(LoginAd.this);
                        alerta.setMessage("Usuario o Contraseña incorrecta").setNegativeButton("Reintentar", null).create().show();
                    }


                } catch (JSONException e) {
                    e.getMessage();
                }




            }
        };


        LoginUsuarioRequest r = new LoginUsuarioRequest(User.trim(),Pw.trim(),respuesta);
        RequestQueue cola = Volley.newRequestQueue(LoginAd.this);
        cola.add(r);


    }

    private void AccesoPerfilSinMantenerSesionIniciada(){
        Intent intent3 = new Intent(getApplicationContext(),administrador.class);
        startActivityForResult(intent3, 0);
    }

    public void openDialogInicio2(){
        dialogoLogin exampleDialog = new dialogoLogin();
        exampleDialog.show(getSupportFragmentManager(),"Ejemplo Administrador");
    }



}
