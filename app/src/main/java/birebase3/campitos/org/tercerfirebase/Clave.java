
package birebase3.campitos.org.tercerfirebase;

import java.io.Serializable;

/**
 * Created by campitos on 7/11/16.
 */

public class Clave implements Serializable {
    String id;
    String nombre;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
