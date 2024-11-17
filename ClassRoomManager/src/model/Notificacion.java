import java.io.Serializable;
import java.sql.Timestamp;

// Definición de la clase Notificacion, que implementa Serializable para permitir su almacenamiento en un archivo.
public class Notificacion implements Serializable {

    // Atributos privados de la clase Notificacion, adaptados a la estructura de la base de datos.
    private int id_notificacion;  // id_notificacion como INT
    private String mensaje;       // mensaje como TEXT
    private Timestamp fecha_envio; // fecha_envio como TIMESTAMP
    private int idEstudiante;     // idEstudiante como INT (relación con Estudiante)

    // Constructor vacío que permite crear un objeto Notificacion sin inicializar sus atributos.
    public Notificacion() {}

    // Constructor con parámetros, permite crear un objeto Notificacion e inicializar sus atributos.
    public Notificacion(int id_notificacion, String mensaje, Timestamp fecha_envio, int idEstudiante) {
        this.id_notificacion = id_notificacion;  
        this.mensaje = mensaje;
        this.fecha_envio = fecha_envio;  
        this.idEstudiante = idEstudiante;
    }

    // Métodos Getters y Setters para cada atributo.
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

    // Método para establecer el ID del estudiante
    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante; // Asegúrate de que la variable idEstudiante esté definida en la clase
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    // Método toString que sobrescribe el método por defecto de Object.
    @Override
    public String toString() {
        return "Notificacion{" +
                "id_notificacion=" + id_notificacion +
                ", mensaje='" + mensaje + '\'' +
                ", fecha_envio=" + fecha_envio +
                '}';
    }
}
