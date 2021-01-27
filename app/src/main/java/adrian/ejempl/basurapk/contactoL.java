package adrian.ejempl.basurapk;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.basurapk.R;

public class contactoL extends AppCompatActivity {

    private Button btnEmergencias,btnBasurapk,btnAseo,btnAyuntamiento;
    String phoneNumber;

    private final int PhONE_CALL_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_l);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

//Llamar Emergencias

        btnEmergencias = findViewById(R.id.btnEmergencias);
        btnEmergencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    phoneNumber="7535321855";
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PhONE_CALL_CODE);

                }

            }


        });

//Llamar Aseo publico

        btnAseo = findViewById(R.id.btnAseo);
        btnAseo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    phoneNumber="7535372281";
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PhONE_CALL_CODE);

                }



            }
        });

//Llamar BasurapK


        btnBasurapk=findViewById(R.id.btnBasurapk);
        btnBasurapk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    phoneNumber="7535363491";
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PhONE_CALL_CODE);

                }

            }
        });


//Llamar H.Ayuntamiento

        btnAyuntamiento=findViewById(R.id.btnAyuntamiento);
        btnAyuntamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    phoneNumber="7535403300";
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PhONE_CALL_CODE);

                }

            }
        });


        //--------------------

        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent3 = new Intent(view.getContext(), MainActivity.class);
                startActivityForResult(intent3, 0);

            }
        });


        //----------------------------------------------------------


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PhONE_CALL_CODE:
                String permission = permissions[0];
                int result = grantResults[0];

                if (permission.equals(Manifest.permission.CALL_PHONE)) {
                    //Comprobar si ah sido aceptado o denegada la peticion de permiso
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        //Acepto permisos
                        Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            return; }
                        startActivity(intentCall);
                    }else{
                        //No Acepto permiso
                        Toast.makeText(contactoL.this,"Rechazaste los permisos",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);break;
        }

    }




    private boolean CheckPermission(String permission){
        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }





}
