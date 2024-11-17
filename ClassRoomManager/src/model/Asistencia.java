// Importación de Serializable para que los objetos de esta clase puedan ser serializados.
import java.io.Serializable;
import java.sql.Date;  // Importar Date para manejar fechas en la base de datos.

// Definición de la clase Asistencia, que implementa Serializable para permitir su almacenamiento en un archivo.
public class Asistencia implements Serializable {

    // Atributos de la clase Asistencia, adaptados a la estructura de la base de datos.
    private int id_asistencia;    // Cambiado a id_asistencia
    private Date fecha;           // Mantener como Date
    private String estado;        
    private boolean justificada;  
    private int id_estudiante;    // Cambiado a id_estudiante

    // Constructor vacío que permite crear un objeto Asistencia sin inicializar sus atributos.
    public Asistencia() {}

    // Constructor con parámetros para inicializar todos los atributos de un objeto Asistencia.
    public Asistencia(int id_asistencia, Date fecha, String estado, boolean justificada, int id_estudiante) {
        this.id_asistencia = id_asistencia;  // Cambiado a id_asistencia
        this.fecha = fecha;                   
        this.estado = estado;
        this.justificada = justificada;
        this.id_estudiante = id_estudiante;  // Cambiado a id_estudiante
    }

    // Getters y Setters para acceder y modificar cada atributo de la clase.
    public int getIdAsistencia() {
        return id_asistencia;  // Cambiado a id_asistencia
    }

    public void setIdAsistencia(int id_asistencia) {
        this.id_asistencia = id_asistencia;  // Cambiado a id_asistencia
    }

    public Date getFecha() {  // Cambiado a Date
        return fecha;
    }

    public void setFecha(Date fecha) {  // Acepta Date
        this.fecha = fecha;  // Asignar directamente el objeto Date
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isJustificada() {
        return justificada;
    }

    public void setJustificada(boolean justificada) {
        this.justificada = justificada;
    }

    public int getIdEstudiante() {
        return id_estudiante;  // Cambiado a id_estudiante
    }

    public void setIdEstudiante(int id_estudiante) {
        this.id_estudiante = id_estudiante;  // Cambiado a id_estudiante
    }

    // Método toString para representar un objeto Asistencia como una cadena de texto.
    @Override
    public String toString() {
        return "Asistencia{" +
                "id_asistencia=" + id_asistencia +
                ", fecha=" + fecha +  // Cambiado a Date
                ", estado='" + estado + '\'' +
                ", justificada=" + justificada +
                ", id_estudiante=" + id_estudiante +
                '}';
    }
}
