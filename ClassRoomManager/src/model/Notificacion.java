import java.io.Serializable;
import java.sql.Timestamp;


public class Notificacion implements Serializable {

   
    private int id_notificacion;  
    private String mensaje;       
    private Timestamp fecha_envio; 
    private int idEstudiante;     

    // Constructor vacío 
    public Notificacion() {}

    // Constructor con parametros
    public Notificacion(int id_notificacion, String mensaje, Timestamp fecha_envio, int idEstudiante) {
        this.id_notificacion = id_notificacion;  
        this.mensaje = mensaje;
        this.fecha_envio = fecha_envio;  
        this.idEstudiante = idEstudiante;
    }

    // getters y setters
    public int getIdNotificacion() {
        return id_notificacion;  
    }

    public void setIdNotificacion(int id_notificacion) {
        this.id_notificacion = id_notificacion;  
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Timestamp getFechaEnvio() {
        return fecha_envio;
    }

    public void setFechaEnvio(Timestamp fecha_envio) {
        this.fecha_envio = fecha_envio;
    }

    // establecer el ID del estudiante
    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante; 
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    // toString que sobrescribe el método por defecto de Object
    @Override
    public String toString() {
        return "Notificacion{" +
                "id_notificacion=" + id_notificacion +
                ", mensaje='" + mensaje + '\'' +
                ", fecha_envio=" + fecha_envio +
                '}';
    }
}
