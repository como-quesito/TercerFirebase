package birebase3.campitos.org.tercerfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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
    }
}
