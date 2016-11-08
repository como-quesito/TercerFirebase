package birebase3.campitos.org.tercerfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.registrarse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Clave clave=new Clave();
                clave.setNombre("Juan Carlos");
                try {
                    ServicioMensajeria.guardar(clave,getApplicationContext());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplication(), "Registrado",Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.obtener).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Clave clave=ServicioMensajeria.buscarClave(getApplicationContext());
                    Toast.makeText(getApplication(), "Clave es:"+clave.getNombre(),Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        //Acompañamos cualquier posible notificacion
        if(getIntent().getExtras()!=null){
            for(String key:getIntent().getExtras().keySet()){
                Object value=getIntent().getExtras().get(key);
                Log.d(TAG,"Key:"+key+" Value: "+value);
            }
        }

        //Terminamos con el  manejo de posibles extras


        //Primer boton de suscripcion para invocar la suscripcion

        findViewById(R.id.subscribeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseMessaging.getInstance().subscribeToTopic("news");
                //Termina topico a suscribirse

                //Log con Toast
                String msg=getString(R.string.msg_subscribed);
                Log.d(TAG, msg);

            }
        });

        //El boton de logueo de toquen

        findViewById(R.id.logTokenButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Obtenemos el token
                String token= FirebaseInstanceId.getInstance().getToken();

                //Obtenemos el Log y Toast
                String msg=getString(R.string.msg_token_fmt, token);
                Log.d(TAG,msg);
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_LONG).show();
            }
        });

    }
}
