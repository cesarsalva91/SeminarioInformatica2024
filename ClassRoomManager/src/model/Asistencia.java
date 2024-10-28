// Importación de Serializable para que los objetos de esta clase puedan ser serializados.
import java.io.Serializable;
//import java.util.Date;

// Definición de la clase Asistencia, que implementa Serializable para permitir su almacenamiento en un archivo.
public class Asistencia implements Serializable {

    // Atributos de la clase Asistencia.
    private int idAsistencia;    
    private String fecha;          
    private String estado;       
    private boolean justificada; 
    private int idEstudiante;    

    // Constructor vacío que permite crear un objeto Asistencia sin inicializar sus atributos.
    public Asistencia() {}

    // Constructor con parámetros para inicializar todos los atributos de un objeto Asistencia.
    public Asistencia(int idAsistencia, String fecha, String estado, boolean justificada, int idEstudiante) {
        this.idAsistencia = idAsistencia;
        this.fecha = fecha;
        this.estado = estado;
        this.justificada = justificada;
        this.idEstudiante = idEstudiante;
    }

    // Getters y Setters para acceder y modificar cada atributo de la clase.
    public int getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String string) {
        this.fecha = string;
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
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    // Método toString para representar un objeto Asistencia como una cadena de texto.
    @Override
    public String toString() {
        return "Asistencia{" +
                "idAsistencia=" + idAsistencia +
                ", fecha=" + fecha +
                ", estado='" + estado + '\'' +
                ", justificada=" + justificada +
                ", idEstudiante=" + idEstudiante +
                '}';
    }
}
