package adrian.ejempl.basurapk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.basurapk.R;

public class infoRutaDetail9 extends AppCompatActivity {


    ImageView imgBack;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_ruta_detail9);

        imgBack=findViewById(R.id.imgBack);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent3 = new Intent(view.getContext(),info_rutas1.class);
                startActivityForResult(intent3, 0);
            }
        });



        Button idMapa = findViewById(R.id.idMapa);

        idMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4= new Intent(getApplicationContext(),mapa.class);
                startActivityForResult(intent4, 0);
            }
        });




    }
}
