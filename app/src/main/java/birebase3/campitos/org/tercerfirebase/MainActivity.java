package birebase3.campitos.org.tercerfirebase;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import org.springframework.web.client.RestTemplate;

public class MainActivity extends AppCompatActivity {
String miToquensito="nana";
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


TareaMensajeria tareaMensajeria=new TareaMensajeria();
                tareaMensajeria.execute(null,null,null);


            }
        });

        //El boton de logueo de toquen

        findViewById(R.id.logTokenButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Obtenemos el token
                miToquensito= FirebaseInstanceId.getInstance().getToken();

                //Obtenemos el Log y Toast
                String msg=getString(R.string.msg_token_fmt, miToquensito);
                Log.d(TAG,msg);
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_LONG).show();


            }
        });

    }


    class TareaMensajeria extends AsyncTask<String, Integer, Integer> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

        }

        @Override
        protected Integer doInBackground(String... strings) {


            FirebaseMessaging.getInstance().subscribeToTopic("news");
            //Termina topico a suscribirse
            miToquensito=FirebaseInstanceId.getInstance().getToken();
            RestTemplate restTemplate=new RestTemplate();
            String mensaje=   restTemplate.postForObject("http://192.168.1.77:9090/registro-mensajes",new Clave(miToquensito,"Juan carlos"), String.class);
            Log.i("ZAZAZAZ","rEGISTROSAFOOddddddddddddddO");
            //Log con Toast
            String msg=getString(R.string.msg_subscribed);
            Log.d(TAG, msg);
            return null;
        }
    }
}
