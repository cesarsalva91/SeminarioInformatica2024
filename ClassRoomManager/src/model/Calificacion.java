// Importación de Serializable para que los objetos de esta clase puedan ser serializados.
import java.io.Serializable;

// Definición de la clase Calificacion, que implementa Serializable para permitir su almacenamiento en un archivo.
public class Calificacion implements Serializable {

    // Atributos de la clase Calificacion, adaptados a la estructura de la base de datos.
    private int id_calificacion;    // Cambiado a id_calificacion
    private float nota;              // Cambiado a float para coincidir con la base de datos
    private int id_estudiante;       // Cambiado a id_estudiante
    private int id_materia;          // Añadido id_materia para la relación con Materia

    // Constructor vacío que permite crear un objeto Calificacion sin inicializar sus atributos.
    public Calificacion() {}

    // Constructor con parámetros para inicializar todos los atributos de un objeto Calificacion.
    public Calificacion(int id_calificacion, float nota, int id_estudiante, int id_materia) {
        this.id_calificacion = id_calificacion;  
        this.nota = nota;  // Cambiado a float
        this.id_estudiante = id_estudiante;  
        this.id_materia = id_materia;  // Inicializar id_materia
    }

    // Agregar este constructor a la clase Calificacion
    public Calificacion(int idCalificacion, String materia, double nota, int idEstudiante) {
        this.id_calificacion = idCalificacion;
        this.nota = (float) nota;
        this.id_estudiante = idEstudiante;
    }

    // Getters y Setters adaptados a los nuevos nombres de atributos
    public int getIdCalificacion() {
        return id_calificacion;  // Cambiado a id_calificacion
    }

    public void setIdCalificacion(int id_calificacion) {
        this.id_calificacion = id_calificacion;  // Cambiado a id_calificacion
    }

    public float getNota() {
        return nota;  // Mantener como float
    }

    public void setNota(float nota) {
        this.nota = nota;  // Mantener como float
    }

    public int getIdEstudiante() {
        return id_estudiante;  // Cambiado a id_estudiante
    }

    public void setIdEstudiante(int id_estudiante) {
        this.id_estudiante = id_estudiante;  // Cambiado a id_estudiante
    }

    public int getIdMateria() {  // Añadido getter para id_materia
        return id_materia;
    }

    public void setIdMateria(int id_materia) {  // Añadido setter para id_materia
        this.id_materia = id_materia;
    }

    // Añadido getter para id_materia
    public int getMateria() {  
        return id_materia;  // Retorna el id_materia
    }

    // Método toString para representar un objeto Calificacion como una cadena de texto.
    @Override
    public String toString() {
        return "Calificacion{" +
                "id_calificacion=" + id_calificacion +
                ", nota=" + nota +
                ", id_estudiante=" + id_estudiante +
                ", id_materia=" + id_materia +
                '}';
    }
}
