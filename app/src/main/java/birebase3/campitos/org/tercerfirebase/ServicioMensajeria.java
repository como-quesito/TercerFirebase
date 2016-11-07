package birebase3.campitos.org.tercerfirebase;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by campitos on 7/11/16.
 */

public class ServicioMensajeria {


    public static  void guardar(Clave clave, Context ctx) throws Exception{


        FileOutputStream fileOutputStream=ctx.openFileOutput("clave",MODE_PRIVATE);

        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(clave);


    }

    public static  Clave buscarClave(Context ctx) throws Exception{
        FileInputStream fileInputStream=ctx.openFileInput("clave");
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
        Clave clave= (Clave) objectInputStream.readObject();
        return clave;
    }




}
