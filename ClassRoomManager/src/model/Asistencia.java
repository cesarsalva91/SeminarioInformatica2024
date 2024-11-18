import java.io.Serializable;
import java.time.LocalDate;

// serializable para permitir su almacenamiento en un archivo.
public class Asistencia implements Serializable {

    // atributos de la clase Asistencia
    private int id_asistencia;    
    private LocalDate fecha;         
    private String estado;        
    private boolean justificada;  
    private int id_estudiante;   

    // Constructor vacío
    public Asistencia() {}

    // Constructor con parámetros
    public Asistencia(int id_asistencia, LocalDate fecha, String estado, boolean justificada, int id_estudiante) {
        this.id_asistencia = id_asistencia;  // Cambiado a id_asistencia
        this.fecha = fecha;                   
        this.estado = estado;
        this.justificada = justificada;
        this.id_estudiante = id_estudiante; 
    }

    // getters y setters 
    public int getIdAsistencia() {
        return id_asistencia;  
    }

    public void setIdAsistencia(int id_asistencia) {
        this.id_asistencia = id_asistencia;  
    }

    public LocalDate getFecha() {  
        return fecha;
    }

    public void setFecha(LocalDate fecha) {  
        this.fecha = fecha;  
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
        return id_estudiante;  
    }

    public void setIdEstudiante(int id_estudiante) {
        this.id_estudiante = id_estudiante;  
    }

    // toString para representar un objeto Asistencia como una cadena de texto
    @Override
    public String toString() {
        return "Asistencia{" +
                "id_asistencia=" + id_asistencia +
                ", fecha=" + fecha +  
                ", estado='" + estado + '\'' +
                ", justificada=" + justificada +
                ", id_estudiante=" + id_estudiante +
                '}';
    }
}
