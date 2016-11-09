package birebase3.campitos.org.tercerfirebase;




import java.io.Serializable;

/**
 * Created by campitos on 8/11/16.
 */


public class Clave{

    Long id;

    String token;
    String nombre;


    public Clave() {
    }

    public Clave(Long id) {
        this.id = id;
    }

    public Clave(String token, String nombre) {
        this.token = token;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
