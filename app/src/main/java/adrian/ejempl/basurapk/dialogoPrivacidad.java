package adrian.ejempl.basurapk;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

public class dialogoPrivacidad extends AppCompatDialogFragment {



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder .setTitle("Aviso BasurapK").setMessage("Tus datos personales (Nombre, Telefono y Correo Electronico ) seran usados unicamente como medio de comunicación entre ciudadano y encargado de departamento, sus datos JAMAS seran usados para lucrar con ellos. Considere que no en todos los casos se recaba toda la información anterior, lo cual atiende a que no toda la información puede ser proporcionada por el respectivo Titular o requerida para la prestación del servicio.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                }).setNegativeButton("Manual De Usuario", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                Uri uri = Uri.parse("https://basurapk.com/aplicacion/Manual.pdf");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);


            }
        });

        return builder.create();

    }


}
