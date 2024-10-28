import java.io.Serializable;
//import java.util.Date;


public class Notificacion implements Serializable {

    // Atributos de la clase Notificacion.
    private int idNotificacion;    
    private String mensaje;        
    private String fechaEnvio;        
    private int idEstudiante;     

    // Constructor vacío que permite crear un objeto Notificacion sin inicializar sus atributos.
    public Notificacion() {}

    // Constructor con parámetros para inicializar todos los atributos de un objeto Notificacion.
    public Notificacion(int idNotificacion, String mensaje, String fechaEnvio, int idEstudiante) {
        this.idNotificacion = idNotificacion;
        this.mensaje = mensaje;
        this.fechaEnvio = fechaEnvio;
        this.idEstudiante = idEstudiante;
    }

    // Getters y Setters para acceder y modificar cada atributo de la clase.
    public int getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(int idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(String string) {
        this.fechaEnvio = string;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    // Método toString para representar un objeto Notificacion como una cadena de texto.
    @Override
    public String toString() {
        return "Notificacion{" +
                "idNotificacion=" + idNotificacion +
                ", mensaje='" + mensaje + '\'' +
                ", fechaEnvio=" + fechaEnvio +
                ", idEstudiante=" + idEstudiante +
                '}';
    }
}
