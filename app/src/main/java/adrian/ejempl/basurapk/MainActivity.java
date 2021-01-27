package adrian.ejempl.basurapk;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.basurapk.R;

public class MainActivity extends AppCompatActivity {


    ImageView imgMapa,imgNoticias,imgRutas,imgContacto,imgSalir,imgLogo;
    ImageView imgAviso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        //Aviso de privacidad

        imgAviso=findViewById(R.id.imgAviso);

        imgAviso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogoAviso();

            }
        });


        //Fin aviso de privacidad




        //Efecto

        imgLogo=findViewById(R.id.imageView3);

        final Animation zoomAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom);
        imgLogo.startAnimation(zoomAnimation);


        //


        ConnectivityManager nuevo = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = nuevo.getActiveNetworkInfo();


        imgMapa=findViewById(R.id.imgMapa);
        imgNoticias=findViewById(R.id.imgNoticias);
        imgRutas=findViewById(R.id.imgRutas);
        imgContacto=findViewById(R.id.imgContacto);
        imgSalir=findViewById(R.id.imgSALIR);


        //.-----SAlir


        imgSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

          finishAffinity();

            }
        });



        //------


        imgMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(view.getContext(),mapa.class);
                startActivityForResult(intent3, 0);
            }
        });

        imgNoticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(networkInfo != null && networkInfo.isConnected()){
                    Intent intent3 = new Intent(view.getContext(),notificaciones.class);
                    startActivityForResult(intent3, 0);
                }else{

                    Toast.makeText(getApplicationContext(),"Verifica tu conexón a internet para obtener las noticias mas recientes" , Toast.LENGTH_SHORT).show();
                }


            }
        });

        imgRutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(view.getContext(),info_rutas1.class);
                startActivityForResult(intent3, 0);
            }
        });

        imgContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(view.getContext(),contacto.class);
                startActivityForResult(intent3, 0);
            }
        });





        Button btnInfoRuta=(Button)findViewById(R.id.btnInfoRuta);

      btnInfoRuta.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              Intent intent = new Intent (view.getContext(), info_rutas1.class);
              startActivityForResult(intent, 0);

          }
      });


        Button btnMapa=(Button)findViewById(R.id.btnMapa);

        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2 = new Intent (view.getContext(), mapa.class);
                startActivityForResult(intent2, 0);

            }
        });


        Button btnNotificacion = (Button)findViewById(R.id.btnNotificacion);

        btnNotificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(networkInfo != null && networkInfo.isConnected()){
                    Intent intent3 = new Intent(view.getContext(),notificaciones.class);
                    startActivityForResult(intent3, 0);

                }else{

                    Toast.makeText(getApplicationContext(),"Verifica tu conexón a internet para obtener las noticias mas recientes" , Toast.LENGTH_SHORT).show();
                }


            }
        });



        Button btnContacto = (Button)findViewById(R.id.btnContacto);

        btnContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent4= new Intent(view.getContext(),contacto.class);
                startActivityForResult(intent4, 0);

            }
        });


        Button button6 = (Button)findViewById(R.id.button6);

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent5= new Intent(view.getContext(),LoginAd.class);
                startActivityForResult(intent5, 0);


            }
        });



        Button button7 = (Button)findViewById(R.id.button7);

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent5= new Intent(view.getContext(),LoginCh.class);
                startActivityForResult(intent5, 0);


            }
        });




    }

    public void DialogoAviso(){
        dialogoPrivacidad privacidad = new dialogoPrivacidad();
        privacidad.show(getSupportFragmentManager(),"Ejemplo Administrador");

    }

}
