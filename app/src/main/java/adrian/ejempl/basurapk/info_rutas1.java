package adrian.ejempl.basurapk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.example.basurapk.R;

public class info_rutas1 extends AppCompatActivity {

    String Horarios,Dias,Calles,Numero,Color;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_rutas1);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ImageView imageView = (ImageView)findViewById(R.id.imageView4);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent4 = new Intent(view.getContext(),MainActivity.class);
                startActivityForResult(intent4, 0);
            }
        });





        Button btnRuta9 = (Button) findViewById(R.id.btnRuta9);
        Button btnRuta10 = (Button) findViewById(R.id.btnRuta10);
        Button btnRuta11 = (Button) findViewById(R.id.btnRuta11);



        //Guardar informacion para ser enviada

        btnRuta9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent3 = new Intent(view.getContext(),infoRutaDetail9.class);
                startActivityForResult(intent3, 0);

            }
        });


        btnRuta10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(view.getContext(),infoRutaDetail10.class);
                startActivityForResult(intent3, 0);

            }
        });

        btnRuta11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(view.getContext(),infoRutaDetail11.class);
                startActivityForResult(intent3, 0);


            }
        });


        //Guardar información para ser enviada FIN



    }



}
