package adrian.ejempl.basurapk;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.basurapk.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class mapa extends FragmentActivity implements OnMapReadyCallback {
    SupportMapFragment mapFrag;
    private GoogleMap mMap;

    //VerdeCamion

    String LatitudRojo, LongitudRojo;
    Double DLatitud;
    Double DLongitud;

    //RojoCamion

    Double DLatitudrojo;
    Double DLongitudrojo;
    String LatitudRojos, LongitudRojos;

    //AzulCamion

    Double DLatitudAzul;
    Double DLongitudAzul;
    String LatitudAzul, LongitudAzul;


    private FusedLocationProviderClient fusedLocationClient;
    RequestQueue requestQueue;
    private CountDownTimer MapaCountDownTimerr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);


        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

        if (status == ConnectionResult.SUCCESS) {


            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        } else {

            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, (Activity) getApplicationContext(), 10);
            dialog.show();
        }


        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;


        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                mMap.setMyLocationEnabled(true);
            }
        } else {

            mMap.setMyLocationEnabled(true);


        }

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        LatLng ContenedorI = new LatLng(17.961532, -102.196813);
        float zoomlevelR2 = 14;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ContenedorI, zoomlevelR2));

        Conteo();

        Toast.makeText(getApplicationContext(), "Cargando Camiones...!", Toast.LENGTH_SHORT).show();


        //Buscar de una


        //Ruta9 Verde

        ruta9();
        contenedorRuta9();

        //Ruta 10
        ruta10();
        contenedorRuta10();

        //Ruta11
        ruta11();
        contenedorRuta11();

    }


    public void ruta9() {
        Polyline r9 = mMap.addPolyline(new PolylineOptions()

                .add(new LatLng(17.956919, -102.194146), new LatLng(17.956550, -102.192714))
                .add(new LatLng(17.956550, -102.192714), new LatLng(17.955735, -102.189651))
                .add(new LatLng(17.955735, -102.189651), new LatLng(17.955513, -102.188605))
                .add(new LatLng(17.955513, -102.188605), new LatLng(17.956054, -102.188506))
                .add(new LatLng(17.956054, -102.188506), new LatLng(17.956526, -102.190123))
                .add(new LatLng(17.956526, -102.190123), new LatLng(17.957439, -102.193476))
                .add(new LatLng(17.957439, -102.193476), new LatLng(17.957938, -102.192789))
                .add(new LatLng(17.957938, -102.192789), new LatLng(17.957637, -102.191685))
                .add(new LatLng(17.957637, -102.191685), new LatLng(17.957134, -102.189976))
                .add(new LatLng(17.957134, -102.189976), new LatLng(17.956973, -102.189295))
                .add(new LatLng(17.956973, -102.189295), new LatLng(17.956700, -102.188318))
                .add(new LatLng(17.956700, -102.188318), new LatLng(17.956983, -102.188238))
                .add(new LatLng(17.956983, -102.188238), new LatLng(17.957394, -102.188235))
                .add(new LatLng(17.957394, -102.188235), new LatLng(17.957738, -102.189783))
                .add(new LatLng(17.957738, -102.189783), new LatLng(17.958363, -102.192119))
                .add(new LatLng(17.958563, -102.191907), new LatLng(17.963603, -102.195843))
                .add(new LatLng(17.963455, -102.194814), new LatLng(17.958974, -102.191333))
                .width(15)
                .color(Color.GREEN));


        Polyline r91 = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(17.963679, -102.196993), new LatLng(17.958085, -102.192636))
                .width(15)
                .color(Color.GREEN));

        Polyline r92 = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(17.959296, -102.194395), new LatLng(17.957713, -102.193160))
                .width(15)
                .color(Color.GREEN));

        Polyline r93 = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(17.958877, -102.194997), new LatLng(17.957301, -102.193735))
                .width(15)
                .color(Color.GREEN));

        Polyline r94 = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(17.963204, -102.197816), new LatLng(17.959137, -102.194711))
                .width(15)
                .color(Color.GREEN));
        //

        Polyline r95 = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(17.958007, -102.190809), new LatLng(17.959011, -102.190529))
                .width(15)
                .color(Color.GREEN));

        Polyline r96 = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(17.957745, -102.189781), new LatLng(17.958785, -102.189489))
                .width(15)
                .color(Color.GREEN));


        Polyline r97 = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(17.957392, -102.188238), new LatLng(17.957950, -102.188275))
                .width(15)
                .color(Color.GREEN));

    }

    public void banderas() {

        //Inicio Fin Ruta10 Roja


        //LatLng banderair = new LatLng(17.962707, -102.199177);
        //mMap.addMarker(new MarkerOptions().position(banderair).title("Inicio Ruta ").icon(BitmapDescriptorFactory.fromResource(R.drawable.band)));

        //LatLng banderafr = new LatLng(17.956149, -102.193767);
        //mMap.addMarker(new MarkerOptions().position(banderafr).title("Fin Ruta ").icon(BitmapDescriptorFactory.fromResource(R.drawable.terminar)));

        //Inicio Fin Ruta 9 verde

        //LatLng banderaiv = new LatLng(17.963199, -102.197814);
        //mMap.addMarker(new MarkerOptions().position(banderaiv).title("Inicio Ruta ").icon(BitmapDescriptorFactory.fromResource(R.drawable.band)));

        //LatLng banderafv = new LatLng(17.956909, -102.194102);
        //mMap.addMarker(new MarkerOptions().position(banderafv).title("Fin Ruta ").icon(BitmapDescriptorFactory.fromResource(R.drawable.terminar)));

        //Inicio Fin Ruta11 Azul

        //LatLng banderaia = new LatLng(17.959394, -102.190788);
        //mMap.addMarker(new MarkerOptions().position(banderaia).title("Inicio Ruta ").icon(BitmapDescriptorFactory.fromResource(R.drawable.band)));

        //LatLng banderafa = new LatLng(17.969325, -102.197535);
        //mMap.addMarker(new MarkerOptions().position(banderafa).title("Fin Ruta ").icon(BitmapDescriptorFactory.fromResource(R.drawable.terminar)));


    }

    public void ruta10() {
        Polyline r10 = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(17.962845, -102.199289), new LatLng(17.962445, -102.198956))
                .add(new LatLng(17.962445, -102.198956), new LatLng(17.962195, -102.198735))
                .add(new LatLng(17.962195, -102.198735), new LatLng(17.962145, -102.198687))
                .add(new LatLng(17.962145, -102.198687), new LatLng(17.959746, -102.196780))
                .add(new LatLng(17.959746, -102.196780), new LatLng(17.958396, -102.195735))
                .add(new LatLng(17.958396, -102.195735), new LatLng(17.957624, -102.195109))
                .add(new LatLng(17.957624, -102.195109), new LatLng(17.956744, -102.194444))
                .add(new LatLng(17.956744, -102.194444), new LatLng(17.955681, -102.193655))
                .add(new LatLng(17.955681, -102.193655), new LatLng(17.955597, -102.193603))
                .add(new LatLng(17.955597, -102.193603), new LatLng(17.954506, -102.192782))
                .add(new LatLng(17.954506, -102.192782), new LatLng(17.954029, -102.192355))
                .add(new LatLng(17.954029, -102.192355), new LatLng(17.954029, -102.192355))
                .add(new LatLng(17.954029, -102.192355), new LatLng(17.953670, -102.192112))
                .add(new LatLng(17.953670, -102.192112), new LatLng(17.951674, -102.190537))


                //Venustiano Carranza
                .add(new LatLng(17.951674, -102.190537), new LatLng(17.951719, -102.190867))
                .add(new LatLng(17.951719, -102.190867), new LatLng(17.951786, -102.191013))
                .add(new LatLng(17.951786, -102.191013), new LatLng(17.952035, -102.192154))
                .add(new LatLng(17.952035, -102.192154), new LatLng(17.952118, -102.193028))
                .add(new LatLng(17.952118, -102.193028), new LatLng(17.952040, -102.193786))
                .add(new LatLng(17.952040, -102.193786), new LatLng(17.952016, -102.194713))
                .add(new LatLng(17.952016, -102.194713), new LatLng(17.952042, -102.195368))


                //Rector Hidalgo
                .add(new LatLng(17.952042, -102.195368), new LatLng(17.952503, -102.195725))
                .add(new LatLng(17.952503, -102.195725), new LatLng(17.953030, -102.196137))
                .add(new LatLng(17.953030, -102.196137), new LatLng(17.953556, -102.196529))
                .add(new LatLng(17.953556, -102.196529), new LatLng(17.953919, -102.196835))
                .add(new LatLng(17.953919, -102.196835), new LatLng(17.954197, -102.197017))
                .add(new LatLng(17.954197, -102.197017), new LatLng(17.954647, -102.197354))
                .add(new LatLng(17.954647, -102.197354), new LatLng(17.955202, -102.197801))
                .add(new LatLng(17.955202, -102.197801), new LatLng(17.956285, -102.198639))
                .add(new LatLng(17.956285, -102.198639), new LatLng(17.957214, -102.199357))
                .add(new LatLng(17.957214, -102.199357), new LatLng(17.959250, -102.200915))
                .add(new LatLng(17.959250, -102.200915), new LatLng(17.960444, -102.201845))

                //Heroica naval

                .add(new LatLng(17.960444, -102.201845), new LatLng(17.961891, -102.199846))
                .add(new LatLng(17.961891, -102.199846), new LatLng(17.962462, -102.198981))
                .width(15)
                .color(Color.RED));

        //Constitucion


        Polyline r101 = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(17.953713, -102.192151), new LatLng(17.953203, -102.193083))
                .add(new LatLng(17.953203, -102.193083), new LatLng(17.953203, -102.193083))
                .add(new LatLng(17.953203, -102.193083), new LatLng(17.952917, -102.193738))
                .add(new LatLng(17.952917, -102.193738), new LatLng(17.952596, -102.194273))
                .add(new LatLng(17.952596, -102.194273), new LatLng(17.952046, -102.195367))
                .width(15)
                .color(Color.RED));

        //Francisco y Madero


        Polyline r102 = mMap.addPolyline(new PolylineOptions()

                .add(new LatLng(17.952209, -102.190947), new LatLng(17.952629, -102.192468))
                .add(new LatLng(17.952629, -102.192468), new LatLng(17.952791, -102.192775))
                .add(new LatLng(17.952791, -102.192775), new LatLng(17.953202, -102.193083))
                .add(new LatLng(17.953202, -102.193083), new LatLng(17.953925, -102.193670))
                .add(new LatLng(17.953925, -102.193670), new LatLng(17.954472, -102.194068))
                .add(new LatLng(17.954472, -102.194068), new LatLng(17.955651, -102.194979))
                .add(new LatLng(17.955651, -102.194979), new LatLng(17.957771, -102.196589))
                .add(new LatLng(17.957771, -102.196589), new LatLng(17.957723, -102.196676))
                .add(new LatLng(17.957723, -102.196676), new LatLng(17.961252, -102.199422))
                .add(new LatLng(17.961252, -102.199422), new LatLng(17.959845, -102.201364))

                .width(15)
                .color(Color.RED));

        //Venustiano Carranza

        Polyline r103 = mMap.addPolyline(new PolylineOptions()

                .add(new LatLng(17.952039, -102.193813), new LatLng(17.952603, -102.194273))
                .add(new LatLng(17.952603, -102.194273), new LatLng(17.954241, -102.195526))
                .add(new LatLng(17.954241, -102.195526), new LatLng(17.954881, -102.196060))
                .add(new LatLng(17.954881, -102.196060), new LatLng(17.955301, -102.196379))
                .add(new LatLng(17.955301, -102.196379), new LatLng(17.956161, -102.197106))
                .add(new LatLng(17.956161, -102.197106), new LatLng(17.957869, -102.198462))
                .add(new LatLng(17.957869, -102.198462), new LatLng(17.957929, -102.198382))
                .add(new LatLng(17.957929, -102.198382), new LatLng(17.958353, -102.198731))
                .add(new LatLng(17.958353, -102.198731), new LatLng(17.960528, -102.200422))

                .width(15)
                .color(Color.RED));


        //Francisco Villa
        Polyline r104 = mMap.addPolyline(new PolylineOptions()

                .add(new LatLng(17.952925, -102.193742), new LatLng(17.953537, -102.194216))
                .add(new LatLng(17.953537, -102.194216), new LatLng(17.954227, -102.194728))
                .add(new LatLng(17.954227, -102.194728), new LatLng(17.954608, -102.195037))
                .add(new LatLng(17.954608, -102.195037), new LatLng(17.955272, -102.195512))
                .add(new LatLng(17.955272, -102.195512), new LatLng(17.956574, -102.196572))
                .add(new LatLng(17.956574, -102.196572), new LatLng(17.957013, -102.196032))

                .width(15)
                .color(Color.RED));

        //Verdusco
        Polyline r105 = mMap.addPolyline(new PolylineOptions()

                .add(new LatLng(17.958621, -102.197392), new LatLng(17.957216, -102.199364))

                .width(15)
                .color(Color.RED));

        //Benito Juarez


        Polyline r106 = mMap.addPolyline(new PolylineOptions()

                .add(new LatLng(17.956151, -102.193810), new LatLng(17.954818, -102.188857))

                .width(15)
                .color(Color.RED));

        //Guillermo Prieto
        Polyline r107 = mMap.addPolyline(new PolylineOptions()

                .add(new LatLng(17.954365, -102.188917), new LatLng(17.955080, -102.191664))
                .add(new LatLng(17.955080, -102.191664), new LatLng(17.955477, -102.193280))
                .width(15)
                .color(Color.RED));

        //Ignacio Zaragosa

        Polyline r108 = mMap.addPolyline(new PolylineOptions()

                .add(new LatLng(17.953739, -102.189114), new LatLng(17.954046, -102.190189))
                .add(new LatLng(17.954046, -102.190189), new LatLng(17.954437, -102.191844))
                .add(new LatLng(17.954437, -102.191844), new LatLng(17.954650, -102.192522))
                .width(15)
                .color(Color.RED));

        //Melchor Ocampo

        Polyline r109 = mMap.addPolyline(new PolylineOptions()

                .add(new LatLng(17.953125, -102.189188), new LatLng(17.953780, -102.192000))
                .width(15)
                .color(Color.RED));

        //Lerdo de Tejada
        Polyline r110 = mMap.addPolyline(new PolylineOptions()

                .add(new LatLng(17.953527, -102.190956), new LatLng(17.953057, -102.191138))
                .add(new LatLng(17.953057, -102.191138), new LatLng(17.952751, -102.191225))
                .width(15)
                .color(Color.RED));


        // Ignacio Comfort
        Polyline r111 = mMap.addPolyline(new PolylineOptions()

                .add(new LatLng(17.953399, -102.190341), new LatLng(17.952111, -102.190743))
                .width(15)
                .color(Color.RED));


    }

    public void ruta11() {
        Polyline line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(17.962718, -102.190656), new LatLng(17.960490, -102.189021))
                .add(new LatLng(17.960490, -102.189021), new LatLng(17.960329, -102.189042))
                .add(new LatLng(17.960329, -102.189042), new LatLng(17.959451, -102.189310))
                .add(new LatLng(17.959451, -102.189310), new LatLng(17.959510, -102.188374))
                .add(new LatLng(17.959510, -102.188374), new LatLng(17.962187, -102.188422))
                .add(new LatLng(17.962187, -102.188422), new LatLng(17.962373, -102.188494))
                .add(new LatLng(17.962373, -102.188494), new LatLng(17.962531, -102.188692))
                .add(new LatLng(17.962531, -102.188692), new LatLng(17.962631, -102.189588))
                .add(new LatLng(17.962631, -102.189588), new LatLng(17.962764, -102.189797))
                .add(new LatLng(17.962764, -102.189797), new LatLng(17.963874, -102.196855))
                .add(new LatLng(17.963874, -102.196855), new LatLng(17.963782, -102.197220))
                .add(new LatLng(17.963782, -102.197220), new LatLng(17.963333, -102.197939))
                .add(new LatLng(17.963333, -102.197939), new LatLng(17.963593, -102.198053))
                .add(new LatLng(17.963593, -102.198053), new LatLng(17.964996, -102.197954))
                .add(new LatLng(17.964996, -102.197954), new LatLng(17.965488, -102.198753))
                .add(new LatLng(17.965488, -102.198753), new LatLng(17.965572, -102.199179))
                .add(new LatLng(17.965572, -102.199179), new LatLng(17.965483, -102.199624))
                .add(new LatLng(17.965483, -102.199624), new LatLng(17.965266, -102.200241))
                .add(new LatLng(17.965266, -102.200241), new LatLng(17.965228, -102.200869))
                .add(new LatLng(17.965228, -102.200869), new LatLng(17.966894, -102.202167))
                .add(new LatLng(17.966894, -102.202167), new LatLng(17.967501, -102.202577))
                .add(new LatLng(17.967501, -102.202577), new LatLng(17.967879, -102.202188))
                .add(new LatLng(17.967879, -102.202188), new LatLng(17.968707, -102.201113))
                .add(new LatLng(17.968707, -102.201113), new LatLng(17.969853, -102.199702))
                .add(new LatLng(17.969853, -102.199702), new LatLng(17.969108, -102.199128))
                .add(new LatLng(17.969108, -102.199128), new LatLng(17.969011, -102.198924))
                .add(new LatLng(17.969011, -102.198924), new LatLng(17.967296, -102.198586))


                .width(10)
                .color(Color.BLUE));

        Polyline r111 = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(17.959389, -102.190786), new LatLng(17.963240, -102.193743))
                .width(15)
                .color(Color.BLUE));

        Polyline r112 = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(17.963067, -102.192724), new LatLng(17.959777, -102.190210))
                .width(15)
                .color(Color.BLUE));

        Polyline r113 = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(17.960236, -102.189620), new LatLng(17.962899, -102.191691))
                .width(15)
                .color(Color.BLUE));

        Polyline r114 = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(17.966440, -102.201223), new LatLng(17.966483, -102.201086))
                .add(new LatLng(17.966483, -102.201086), new LatLng(17.966557, -102.201046))
                .add(new LatLng(17.966557, -102.201046), new LatLng(17.966845, -102.200888))
                .add(new LatLng(17.966845, -102.200888), new LatLng(17.966904, -102.200698))
                .add(new LatLng(17.966904, -102.200698), new LatLng(17.967384, -102.197961))
                .add(new LatLng(17.967384, -102.197961), new LatLng(17.966960, -102.195901))
                .add(new LatLng(17.966960, -102.195901), new LatLng(17.966468, -102.195929))

                .width(15)
                .color(Color.BLUE));


        Polyline r115 = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(17.967371, -102.197991), new LatLng(17.966958, -102.197902))
                .add(new LatLng(17.966958, -102.197902), new LatLng(17.964779, -102.197964))
                .add(new LatLng(17.964779, -102.197964), new LatLng(17.964907, -102.197618))
                .add(new LatLng(17.964907, -102.197618), new LatLng(17.964985, -102.196955))
                .add(new LatLng(17.964985, -102.196955), new LatLng(17.965194, -102.196097))
                .add(new LatLng(17.965194, -102.196097), new LatLng(17.965344, -102.190232))
                .add(new LatLng(17.965344, -102.190232), new LatLng(17.965331, -102.190004))
                .add(new LatLng(17.965331, -102.190004), new LatLng(17.965277, -102.189789))
                .add(new LatLng(17.965277, -102.189789), new LatLng(17.965116, -102.189440))
                .add(new LatLng(17.965116, -102.189440), new LatLng(17.964825, -102.189113))
                .add(new LatLng(17.964825, -102.189113), new LatLng(17.964427, -102.188893))
                .add(new LatLng(17.964427, -102.188893), new LatLng(17.964100, -102.188842))
                .add(new LatLng(17.964100, -102.188842), new LatLng(17.963698, -102.188839))
                .add(new LatLng(17.963698, -102.188839), new LatLng(17.963167, -102.189064))
                .add(new LatLng(17.963167, -102.189064), new LatLng(17.962884, -102.189410))
                .add(new LatLng(17.962884, -102.189410), new LatLng(17.962744, -102.189740))

                .width(15)
                .color(Color.BLUE));

        Polyline r116 = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(17.967107, -102.196578), new LatLng(17.967367, -102.196481))
                .add(new LatLng(17.967367, -102.196481), new LatLng(17.967594, -102.196520))
                .add(new LatLng(17.967594, -102.196520), new LatLng(17.969321, -102.197512))
                .width(15)
                .color(Color.BLUE));

        Polyline r117 = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(17.959389, -102.190786), new LatLng(17.963240, -102.193743))
                .width(15)
                .color(Color.BLUE));

        Polyline r1178 = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(17.965272, -102.193854), new LatLng(17.964706, -102.193929))
                .add(new LatLng(17.964706, -102.193929), new LatLng(17.964520, -102.193890))
                .add(new LatLng(17.964520, -102.193890), new LatLng(17.964298, -102.193917))
                .add(new LatLng(17.964298, -102.193917), new LatLng(17.963961, -102.194102))
                .add(new LatLng(17.963961, -102.194102), new LatLng(17.963468, -102.194182))

                .width(15)
                .color(Color.BLUE));


        Polyline r119 = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(17.965241, -102.195739), new LatLng(17.964759, -102.195821))
                .add(new LatLng(17.964759, -102.195821), new LatLng(17.964610, -102.195829))
                .add(new LatLng(17.964610, -102.195829), new LatLng(17.964237, -102.195799))
                .add(new LatLng(17.964237, -102.195799), new LatLng(17.963728, -102.195856))
                .width(15)
                .color(Color.BLUE));


        Polyline r1110 = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(17.963038, -102.191707), new LatLng(17.964509, -102.191410))
                .add(new LatLng(17.964509, -102.191410), new LatLng(17.964488, -102.191265))
                .add(new LatLng(17.964488, -102.191265), new LatLng(17.964788, -102.191212))
                .width(15)
                .color(Color.BLUE));

    }

    public void contenedorRuta9() {

        LatLng Contenedor1 = new LatLng(17.962334, -102.197274);
        mMap.addMarker(new MarkerOptions().position(Contenedor1).title("Ruta 9 Contenedor #063 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor2 = new LatLng(17.959565, -102.195055);
        mMap.addMarker(new MarkerOptions().position(Contenedor2).title("Ruta 9 Contenedor #043 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor3 = new LatLng(17.960402, -102.195706);
        mMap.addMarker(new MarkerOptions().position(Contenedor3).title("Ruta 9 Contenedor #055 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor4 = new LatLng(17.958630, -102.194825);
        mMap.addMarker(new MarkerOptions().position(Contenedor4).title("Ruta 9 Contenedor #041 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor5 = new LatLng(17.957397, -102.193846);
        mMap.addMarker(new MarkerOptions().position(Contenedor5).title("Ruta 9 Contenedor #040 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor6 = new LatLng(17.957807, -102.193246);
        mMap.addMarker(new MarkerOptions().position(Contenedor6).title("Ruta 9 Contenedor #030 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor7 = new LatLng(17.958752, -102.194008);
        mMap.addMarker(new MarkerOptions().position(Contenedor7).title("Ruta 9 Contenedor #039 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor8 = new LatLng(17.959612, -102.193963);
        mMap.addMarker(new MarkerOptions().position(Contenedor8).title("Ruta 9 Contenedor #042 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor9 = new LatLng(17.958114, -102.192686);
        mMap.addMarker(new MarkerOptions().position(Contenedor9).title("Ruta 9 Contenedor #SN ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor91 = new LatLng(17.958614, -102.193052);
        mMap.addMarker(new MarkerOptions().position(Contenedor91).title("Ruta 9 Contenedor #038 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor10 = new LatLng(17.960982, -102.194930);
        mMap.addMarker(new MarkerOptions().position(Contenedor10).title("Ruta 9 Contenedor #054 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor11 = new LatLng(17.962178, -102.195862);
        mMap.addMarker(new MarkerOptions().position(Contenedor11).title("Ruta 9 Contenedor #056 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor12 = new LatLng(17.963539, -102.195823);
        mMap.addMarker(new MarkerOptions().position(Contenedor12).title("Ruta 9 Contenedor #058 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor13 = new LatLng(17.962891, -102.195255);
        mMap.addMarker(new MarkerOptions().position(Contenedor13).title("Ruta 9 Contenedor #057 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor14 = new LatLng(17.960358, -102.193352);
        mMap.addMarker(new MarkerOptions().position(Contenedor14).title("Ruta 9 Contenedor #053 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor15 = new LatLng(17.963012, -102.194507);
        mMap.addMarker(new MarkerOptions().position(Contenedor15).title("Ruta 9 Contenedor #059 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor16 = new LatLng(17.961990, -102.193720);
        mMap.addMarker(new MarkerOptions().position(Contenedor16).title("Ruta 9 Contenedor #052 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor17 = new LatLng(17.956501, -102.192709);
        mMap.addMarker(new MarkerOptions().position(Contenedor17).title("Ruta 9 Contenedor #SN ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor18 = new LatLng(17.956158, -102.191433);
        mMap.addMarker(new MarkerOptions().position(Contenedor18).title("Ruta 9 Contenedor #029 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor19 = new LatLng(17.955853, -102.190268);
        mMap.addMarker(new MarkerOptions().position(Contenedor19).title("Ruta 9 Contenedor #018 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor20 = new LatLng(17.957184, -102.192652);
        mMap.addMarker(new MarkerOptions().position(Contenedor20).title("Ruta 9 Contenedor #017 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor201 = new LatLng(17.955648, -102.189303);
        mMap.addMarker(new MarkerOptions().position(Contenedor201).title("Ruta 9 Contenedor #009 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor21 = new LatLng(17.956631, -102.192005);
        mMap.addMarker(new MarkerOptions().position(Contenedor21).title("Ruta 9 Contenedor #031 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor22 = new LatLng(17.956759, -102.191100);
        mMap.addMarker(new MarkerOptions().position(Contenedor22).title("Ruta 9 Contenedor #019 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor23 = new LatLng(17.956336, -102.189540);
        mMap.addMarker(new MarkerOptions().position(Contenedor23).title("Ruta 9 Contenedor #008 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor24 = new LatLng(17.957783, -102.192309);
        mMap.addMarker(new MarkerOptions().position(Contenedor24).title("Ruta 9 Contenedor #SN ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor25 = new LatLng(17.957605, -102.191651);
        mMap.addMarker(new MarkerOptions().position(Contenedor25).title("Ruta 9 Contenedor #032 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor26 = new LatLng(17.957355, -102.190830);
        mMap.addMarker(new MarkerOptions().position(Contenedor26).title("Ruta 9 Contenedor #026 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor27 = new LatLng(17.956967, -102.189338);
        mMap.addMarker(new MarkerOptions().position(Contenedor27).title("Ruta 9 Contenedor #012 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor28 = new LatLng(17.956836, -102.188317);
        mMap.addMarker(new MarkerOptions().position(Contenedor28).title("Ruta 9 Contenedor #002 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor29 = new LatLng(17.957468, -102.188672);
        mMap.addMarker(new MarkerOptions().position(Contenedor29).title("Ruta 9 Contenedor #007 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor30 = new LatLng(17.957709, -102.189758);
        mMap.addMarker(new MarkerOptions().position(Contenedor30).title("Ruta 9 Contenedor #020 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor31 = new LatLng(17.958157, -102.191463);
        mMap.addMarker(new MarkerOptions().position(Contenedor31).title("Ruta 9 Contenedor #08 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor32 = new LatLng(17.958885, -102.190547);
        mMap.addMarker(new MarkerOptions().position(Contenedor32).title("Ruta 9 Contenedor #025 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor33 = new LatLng(17.958708, -102.189535);
        mMap.addMarker(new MarkerOptions().position(Contenedor33).title("Ruta 9 Contenedor #021 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

        LatLng Contenedor34 = new LatLng(17.957835, -102.188302);
        mMap.addMarker(new MarkerOptions().position(Contenedor34).title("Ruta 9 Contenedor #003 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contverde)));

    }

    public void contenedorRuta10() {

        LatLng Contenedor1 = new LatLng(17.953134, -102.195945);
        mMap.addMarker(new MarkerOptions().position(Contenedor1).title("Ruta 10 Contenedor #067 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));

        LatLng Contenedor2 = new LatLng(17.953732, -102.196682);
        mMap.addMarker(new MarkerOptions().position(Contenedor2).title("Ruta 10 Contenedor #079 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));
        LatLng Contenedor4 = new LatLng(17.955960, -102.195267);
        mMap.addMarker(new MarkerOptions().position(Contenedor4).title("Ruta 10 Contenedor #074 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));
        LatLng Contenedor6 = new LatLng(170954535, -102.194095);
        mMap.addMarker(new MarkerOptions().position(Contenedor6).title("Ruta 10 Contenedor #069 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));
        LatLng Contenedor7 = new LatLng(17.953593, -102.193411);
        mMap.addMarker(new MarkerOptions().position(Contenedor7).title("Ruta 10 Contenedor #065 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));
        LatLng Contenedor8 = new LatLng(17.952636, -102.192239);
        mMap.addMarker(new MarkerOptions().position(Contenedor8).title("Ruta 10 Contenedor #064 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));
        LatLng Contenedor9 = new LatLng(17.953101, -102.194686);
        mMap.addMarker(new MarkerOptions().position(Contenedor9).title("Ruta 10 Contenedor #066 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));
        LatLng Contenedor10 = new LatLng(17.954333, -102.195541);
        mMap.addMarker(new MarkerOptions().position(Contenedor10).title("Ruta 10 Contenedor #111 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));
        LatLng Contenedor11 = new LatLng(17.954925, -102.196123);
        mMap.addMarker(new MarkerOptions().position(Contenedor11).title("Ruta 10 Contenedor #072 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));
        LatLng Contenedor12 = new LatLng(17.955387, -102.196398);
        mMap.addMarker(new MarkerOptions().position(Contenedor12).title("Ruta 10 Contenedor #075 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));
        LatLng Contenedor13 = new LatLng(17.956267, -102.197105);
        mMap.addMarker(new MarkerOptions().position(Contenedor13).title("Ruta 10 Contenedor #076 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));
        LatLng Contenedor14 = new LatLng(17.956820, -102.197662);
        mMap.addMarker(new MarkerOptions().position(Contenedor14).title("Ruta 10 Contenedor #078 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));
        LatLng Contenedor15 = new LatLng(17.958006, -102.198510);
        mMap.addMarker(new MarkerOptions().position(Contenedor15).title("Ruta 10 Contenedor #080 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));
        LatLng Contenedor16 = new LatLng(17.958318, -102.198616);
        mMap.addMarker(new MarkerOptions().position(Contenedor16).title("Ruta 10 Contenedor #082 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));
        LatLng Contenedor17 = new LatLng(17.958802, -102.199022);
        mMap.addMarker(new MarkerOptions().position(Contenedor17).title("Ruta 10 Contenedor #083 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));
        LatLng Contenedor18 = new LatLng(17.959235, -102.199353);
        mMap.addMarker(new MarkerOptions().position(Contenedor18).title("Ruta 10 Contenedor #084 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));
        LatLng Contenedor19 = new LatLng(17.959807, -102.199789);
        mMap.addMarker(new MarkerOptions().position(Contenedor19).title("Ruta 10 Contenedor #085 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));
        LatLng Contenedor20 = new LatLng(17.960363, -102.200586);
        mMap.addMarker(new MarkerOptions().position(Contenedor20).title("Ruta 10 Contenedor #086 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));

        //----Sin numero

        LatLng Contenedor21 = new LatLng(17.960694, -102.200234);
        mMap.addMarker(new MarkerOptions().position(Contenedor21).title("Ruta 10 Contenedor # 112").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));
        LatLng Contenedor22 = new LatLng(17.953074, -102.193858);
        mMap.addMarker(new MarkerOptions().position(Contenedor22).title("Ruta 10 Contenedor # 078").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));
        LatLng Contenedor23 = new LatLng(17.955776, -102.195921);
        mMap.addMarker(new MarkerOptions().position(Contenedor23).title("Ruta 10 Contenedor # 068").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));

        LatLng Contenedor24 = new LatLng(17.952125, -102.190677);
        mMap.addMarker(new MarkerOptions().position(Contenedor24).title("Ruta 10 Contenedor # 013").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));

        LatLng Contenedor25 = new LatLng(17.952912, -102.191124);
        mMap.addMarker(new MarkerOptions().position(Contenedor25).title("Ruta 10 Contenedor # 014").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));

        LatLng Contenedor26 = new LatLng(17.953394, -102.190473);
        mMap.addMarker(new MarkerOptions().position(Contenedor26).title("Ruta 10 Contenedor # 012").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));

        LatLng Contenedor27 = new LatLng(17.953616, -102.191447);
        mMap.addMarker(new MarkerOptions().position(Contenedor27).title("Ruta 10 Contenedor # 032").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));

        LatLng Contenedor28 = new LatLng(17.954616, -102.192491);
        mMap.addMarker(new MarkerOptions().position(Contenedor28).title("Ruta 10 Contenedor N/N/V").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));

        LatLng Contenedor29 = new LatLng(17.954196, -102.190945);
        mMap.addMarker(new MarkerOptions().position(Contenedor29).title("Ruta 10 Contenedor # 015").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));

        LatLng Contenedor30 = new LatLng(17.953862, -102.189439);
        mMap.addMarker(new MarkerOptions().position(Contenedor30).title("Ruta 10 Contenedor # 011").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));

        LatLng Contenedor31 = new LatLng(17.954446, -102.189262);
        mMap.addMarker(new MarkerOptions().position(Contenedor31).title("Ruta 10 Contenedor # 010").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));

        LatLng Contenedor32 = new LatLng(17.954829, -102.190448);
        mMap.addMarker(new MarkerOptions().position(Contenedor32).title("Ruta 10 Contenedor # 016").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));

        LatLng Contenedor33 = new LatLng(17.955207, -102.192057);
        mMap.addMarker(new MarkerOptions().position(Contenedor33).title("Ruta 10 Contenedor # 028").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));

        LatLng Contenedor34 = new LatLng(17.955596, -102.191877);
        mMap.addMarker(new MarkerOptions().position(Contenedor34).title("Ruta 10 Contenedor N/N/V").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));

        LatLng Contenedor35 = new LatLng(17.955919, -102.192871);
        mMap.addMarker(new MarkerOptions().position(Contenedor35).title("Ruta 10 Contenedor N/N/V").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));

        LatLng Contenedor36 = new LatLng(17.955166, -102.190300);
        mMap.addMarker(new MarkerOptions().position(Contenedor36).title("Ruta 10 Contenedor # 017").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));

        //Nuevos

        LatLng Contenedor37 = new LatLng(17.958665, -102.197286);
        mMap.addMarker(new MarkerOptions().position(Contenedor37).title("Ruta 10 Contenedor #Eti").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));

        LatLng Contenedor38 = new LatLng(17.956802, -102.195906);
        mMap.addMarker(new MarkerOptions().position(Contenedor38).title("Ruta 10 Contenedor # 077").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));

        LatLng Contenedor39 = new LatLng(17.955559, -102.194880);
        mMap.addMarker(new MarkerOptions().position(Contenedor39).title("Ruta 10 Contenedor # 073").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));

        LatLng Contenedor40 = new LatLng(17.954588, -102.194144);
        mMap.addMarker(new MarkerOptions().position(Contenedor40).title("Ruta 10 Contenedor # 069").icon(BitmapDescriptorFactory.fromResource(R.drawable.contre)));


    }

    public void contenedorRuta11() {

        LatLng Contenedor1 = new LatLng(17.963257, -102.193779);
        mMap.addMarker(new MarkerOptions().position(Contenedor1).title("Ruta 11 Contenedor #061 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor2 = new LatLng(17.961918, -102.190906);
        mMap.addMarker(new MarkerOptions().position(Contenedor2).title("Ruta 11 Contenedor #048 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor3 = new LatLng(17.960277, -102.191493);
        mMap.addMarker(new MarkerOptions().position(Contenedor3).title("Ruta 11 Contenedor #045 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor4 = new LatLng(17.960580, -102.190827);
        mMap.addMarker(new MarkerOptions().position(Contenedor4).title("Ruta 11 Contenedor #046 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor5 = new LatLng(17.961485, -102.191522);
        mMap.addMarker(new MarkerOptions().position(Contenedor5).title("Ruta 11 Contenedor #051 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor6 = new LatLng(17.961124, -102.190347);
        mMap.addMarker(new MarkerOptions().position(Contenedor6).title("Ruta 11 Contenedor #047 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor7 = new LatLng(17.961518, -102.189778);
        mMap.addMarker(new MarkerOptions().position(Contenedor7).title("Ruta 11 Contenedor #034 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor8 = new LatLng(17.962230, -102.189894);
        mMap.addMarker(new MarkerOptions().position(Contenedor8).title("Ruta 11 Contenedor #-- ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor9 = new LatLng(17.960510, -102.189040);
        mMap.addMarker(new MarkerOptions().position(Contenedor9).title("Ruta 11 Contenedor #023 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor10 = new LatLng(17.959501, -102.188794);
        mMap.addMarker(new MarkerOptions().position(Contenedor10).title("Ruta 11 Contenedor #022 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor11 = new LatLng(17.959577, -102.188400);
        mMap.addMarker(new MarkerOptions().position(Contenedor11).title("Ruta 11 Contenedor #004 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor12 = new LatLng(17.961535, -102.188385);
        mMap.addMarker(new MarkerOptions().position(Contenedor12).title("Ruta 11 Contenedor #005 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor13 = new LatLng(17.962690, -102.189612);
        mMap.addMarker(new MarkerOptions().position(Contenedor13).title("Ruta 11 Contenedor #006 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor14 = new LatLng(17.963054, -102.191642);
        mMap.addMarker(new MarkerOptions().position(Contenedor14).title("Ruta 11 Contenedor #049 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor15 = new LatLng(17.963205, -102.192428);
        mMap.addMarker(new MarkerOptions().position(Contenedor15).title("Ruta 11 Contenedor #050 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor16 = new LatLng(17.963329, -102.193255);
        mMap.addMarker(new MarkerOptions().position(Contenedor16).title("Ruta 11 Contenedor #060 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor17 = new LatLng(17.967524, -102.202516);
        mMap.addMarker(new MarkerOptions().position(Contenedor17).title("Ruta 11 Contenedor #108 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor18 = new LatLng(17.966947, -102.200684);
        mMap.addMarker(new MarkerOptions().position(Contenedor18).title("Ruta 11 Contenedor #106 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor19 = new LatLng(17.967285, -102.198669);
        mMap.addMarker(new MarkerOptions().position(Contenedor19).title("Ruta 11 Contenedor #105 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor20 = new LatLng(17.967141, -102.196865);
        mMap.addMarker(new MarkerOptions().position(Contenedor20).title("Ruta 11 Contenedor #098 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor21 = new LatLng(17.966952, -102.195878);
        mMap.addMarker(new MarkerOptions().position(Contenedor21).title("Ruta 11 Contenedor #099 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor22 = new LatLng(17.966479, -102.195912);
        mMap.addMarker(new MarkerOptions().position(Contenedor22).title("Ruta 11 Contenedor #100 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor23 = new LatLng(17.967769, -102.196581);
        mMap.addMarker(new MarkerOptions().position(Contenedor23).title("Ruta 11 Contenedor #101 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor24 = new LatLng(17.966702, -102.199786);
        mMap.addMarker(new MarkerOptions().position(Contenedor24).title("Ruta 11 Contenedor #107 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor25 = new LatLng(17.967861, -102.198689);
        mMap.addMarker(new MarkerOptions().position(Contenedor25).title("Ruta 11 Contenedor #104 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor26 = new LatLng(17.968450, -102.198796);
        mMap.addMarker(new MarkerOptions().position(Contenedor26).title("Ruta 11 Contenedor #103 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor27 = new LatLng(17.966505, -102.197897);
        mMap.addMarker(new MarkerOptions().position(Contenedor27).title("Ruta 11 Contenedor #097 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor28 = new LatLng(17.965799, -102.197927);
        mMap.addMarker(new MarkerOptions().position(Contenedor28).title("Ruta 11 Contenedor #096 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor29 = new LatLng(17.965323, -102.197225);
        mMap.addMarker(new MarkerOptions().position(Contenedor29).title("Ruta 11 Contenedor #095 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor30 = new LatLng(17.964982, -102.197063);
        mMap.addMarker(new MarkerOptions().position(Contenedor30).title("Ruta 11 Contenedor #094 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor31 = new LatLng(17.965333, -102.190604);
        mMap.addMarker(new MarkerOptions().position(Contenedor31).title("Ruta 11 Contenedor #089 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor32 = new LatLng(17.963973, -102.189030);
        mMap.addMarker(new MarkerOptions().position(Contenedor32).title("Ruta 11 Contenedor #088 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor33 = new LatLng(17.964624, -102.191225);
        mMap.addMarker(new MarkerOptions().position(Contenedor33).title("Ruta 11 Contenedor #090 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor34 = new LatLng(17.964779, -102.193888);
        mMap.addMarker(new MarkerOptions().position(Contenedor34).title("Ruta 11 Contenedor #092 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor35 = new LatLng(17.964084, -102.194007);
        mMap.addMarker(new MarkerOptions().position(Contenedor35).title("Ruta 11 Contenedor #001 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor36 = new LatLng(17.964554, -102.195807);
        mMap.addMarker(new MarkerOptions().position(Contenedor36).title("Ruta 11 Contenedor #093 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor37 = new LatLng(17.969309, -102.197630);
        mMap.addMarker(new MarkerOptions().position(Contenedor37).title("Ruta 11 Contenedor #102 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor38 = new LatLng(17.959695, -102.190181);
        mMap.addMarker(new MarkerOptions().position(Contenedor38).title("Ruta 11 Contenedor #024 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.contazul)));
        LatLng Contenedor39 = new LatLng(17.965311, -102.198281);
        mMap.addMarker(new MarkerOptions().position(Contenedor39).title("Ruta 11 Cesto #1 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.cesto)));
        LatLng Contenedor40 = new LatLng(17.965463, -102.198638);
        mMap.addMarker(new MarkerOptions().position(Contenedor40).title("Ruta 11 Cesto #2 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.cesto)));
        LatLng Contenedor41 = new LatLng(17.965583, -102.199052);
        mMap.addMarker(new MarkerOptions().position(Contenedor41).title("Ruta 11 Cesto #3").icon(BitmapDescriptorFactory.fromResource(R.drawable.cesto)));
        LatLng Contenedor42 = new LatLng(17.965244, -102.200842);
        mMap.addMarker(new MarkerOptions().position(Contenedor42).title("Ruta 11 Cesto #4 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.cesto)));
        LatLng Contenedor43 = new LatLng(17.965926, -102.201365);
        mMap.addMarker(new MarkerOptions().position(Contenedor43).title("Ruta 11 Cesto #5 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.cesto)));


    }

    public void buscarVerde(String URL) {


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {

                    try {

                        jsonObject = response.getJSONObject(i);
                        LatitudRojo = jsonObject.getString("PLatitud");
                        LongitudRojo = jsonObject.getString("PLongitud");

                        DLatitud = Double.parseDouble(LatitudRojo);
                        DLongitud = Double.parseDouble(LongitudRojo);


                    } catch (JSONException e) {

                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error en la conexion", Toast.LENGTH_SHORT).show();
            }
        }

        );

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }

    public void buscarRojo(String URL) {


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {

                    try {

                        jsonObject = response.getJSONObject(i);
                        LatitudRojos = jsonObject.getString("PLatitud");
                        LongitudRojos = jsonObject.getString("PLongitud");

                        DLatitudrojo = Double.parseDouble(LatitudRojos);
                        DLongitudrojo = Double.parseDouble(LongitudRojos);


                    } catch (JSONException e) {

                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Toast.makeText(getApplicationContext(),"Error en la conexion",Toast.LENGTH_SHORT).show();
            }
        }

        );

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }

    public void buscarAzul(String URL) {


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {

                    try {

                        jsonObject = response.getJSONObject(i);
                        LatitudAzul = jsonObject.getString("PLatitud");
                        LongitudAzul = jsonObject.getString("PLongitud");

                        DLatitudAzul = Double.parseDouble(LatitudAzul);
                        DLongitudAzul = Double.parseDouble(LongitudAzul);


                    } catch (JSONException e) {

                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getApplicationContext(),"Error en la conexion",Toast.LENGTH_SHORT).show();
            }
        }

        );

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }

    public void Conteo() {


        MapaCountDownTimerr = new CountDownTimer(14699999, 30000) {


            public void onTick(long millisUntilFinished) {

                buscarVerde("https://basurapk.com/webservices/bajarCoordenadasVerde.php");
                buscarRojo("https://basurapk.com/webservices/bajarCoordenadasRojo.php");
                buscarAzul("https://basurapk.com/webservices/bajarCoordenadasAzul.php");

                if (DLongitud == null) {

                    Toast.makeText(getApplicationContext(), "Cargando...!", Toast.LENGTH_SHORT).show();

                } else {

                    LatLng CamionVerde = new LatLng(DLatitud, DLongitud);
                    mMap.addMarker(new MarkerOptions().position(CamionVerde).title("Camion Verde 9").icon(BitmapDescriptorFactory.fromResource(R.drawable.camiverde)));

                }

                if (DLongitudrojo == null) {

                    Toast.makeText(getApplicationContext(), "Cargando...!", Toast.LENGTH_SHORT).show();

                } else {

                    LatLng CamionRojo = new LatLng(DLatitudrojo, DLongitudrojo);
                    mMap.addMarker(new MarkerOptions().position(CamionRojo).title("Camion Rojo 10").icon(BitmapDescriptorFactory.fromResource(R.drawable.camirojo)));

                }
                if (DLongitudAzul == null) {

                    Toast.makeText(getApplicationContext(), "Cargando...!", Toast.LENGTH_SHORT).show();

                } else {

                    LatLng CamionAzul = new LatLng(DLatitudAzul, DLongitudAzul);
                    mMap.addMarker(new MarkerOptions().position(CamionAzul).title("Camion Azul 11").icon(BitmapDescriptorFactory.fromResource(R.drawable.camiazul)));

                }
            }

            public void onFinish() {

            }
        }.start();

    }


    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                //  TODO: Prompt with explanation!

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay!
                    if (ActivityCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mMap.setMyLocationEnabled(true);
                    }
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

        }
    }


}



