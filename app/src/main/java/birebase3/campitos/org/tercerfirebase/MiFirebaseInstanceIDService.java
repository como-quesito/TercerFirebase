package birebase3.campitos.org.tercerfirebase;

import android.os.AsyncTask;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by campitos on 8/11/16.
 */

public class MiFirebaseInstanceIDService extends FirebaseInstanceIdService{

    private static final String TAG="MiFirebaseIDService";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String tokenRefrescado= FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"Toquen refrescado:"+tokenRefrescado);
        //Si queremos mandar mensajes a esta instancia de aplciacion desde
        //el lado del servidor debemos mandar el id del toquen a nuestro servidor
        //Con el siguiente metodo
        mandarRegistroAlServidor(tokenRefrescado);
    }

    /**
     * Metodo para persistir los tokens en un servidor de terceros
     *
     * @param token Aqui va el nuevo token de registro
     */
    private  void mandarRegistroAlServidor(String token){
        //Aqui se implementa el codiguito para mandar el token al servidor
        //por ejemplo al mongoDB etc.
    }

    class TareaMensajeria extends AsyncTask<String, Integer, Integer>{

        @Override
        protected Integer doInBackground(String... strings) {
            return null;
        }
    }
}
