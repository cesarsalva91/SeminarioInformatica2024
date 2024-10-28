
// Importación de Serializable para que los objetos de esta clase puedan ser serializados.
import java.io.Serializable;

// Definición de la clase Calificacion, que implementa Serializable para permitir su almacenamiento en un archivo.
public class Calificacion implements Serializable {

    // Atributos de la clase Calificacion.
    private int idCalificacion;    
    private String materia;        
    private double nota;          
    private int idEstudiante;     

    // Constructor vacío que permite crear un objeto Calificacion sin inicializar sus atributos.
    public Calificacion() {}

    // Constructor con parámetros para inicializar todos los atributos de un objeto Calificacion.
    public Calificacion(int idCalificacion, String materia, double nota, int idEstudiante) {
        this.idCalificacion = idCalificacion;
        this.materia = materia;
        this.nota = nota;
        this.idEstudiante = idEstudiante;
    }

    // Getters y Setters para acceder y modificar cada atributo de la clase.
    public int getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(int idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    // Método toString para representar un objeto Calificacion como una cadena de texto.
    @Override
    public String toString() {
        return "Calificacion{" +
                "idCalificacion=" + idCalificacion +
                ", materia='" + materia + '\'' +
                ", nota=" + nota +
                ", idEstudiante=" + idEstudiante +
                '}';
    }
}
