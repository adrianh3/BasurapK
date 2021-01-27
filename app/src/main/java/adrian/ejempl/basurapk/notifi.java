package adrian.ejempl.basurapk;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class notifi extends Application {


    public static final String CHANNEL_ID = "exampleServiceChannel";


    @Override
    public void onCreate() {
        super.onCreate();

        crearNotificacionChannel();
    }

    private void crearNotificacionChannel(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationChannel serviceChannel=new NotificationChannel(
                    CHANNEL_ID,
                    "BasurapK Ejecutandose",
                    NotificationManager.IMPORTANCE_DEFAULT
                );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);


        }


    }


}