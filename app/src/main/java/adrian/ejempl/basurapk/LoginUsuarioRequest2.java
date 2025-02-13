package adrian.ejempl.basurapk;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginUsuarioRequest2 extends StringRequest {

    private static final String ruta = "https://basurapk.com/webservices/%20loginChofer.php";

    private Map<String, String> parametros;

    public LoginUsuarioRequest2(String usuario, String contra, Response.Listener<String> listener){
        super(Request.Method.POST, ruta, listener, null);
        parametros = new HashMap<>();

        parametros.put("Usuario",usuario);
        parametros.put("Contrasena",contra);
    }
    protected Map<String, String> getParams() {
        return parametros;
    }
}
