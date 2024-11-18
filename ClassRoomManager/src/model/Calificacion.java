import java.io.Serializable;


public class Calificacion implements Serializable {

    // atributos de la clase Calificacion
    private int id_calificacion;    
    private float nota;              
    private int id_estudiante;       
    private int id_materia;          

    // constructor vacío 
    public Calificacion() {}

    // constructor con parámetros
    public Calificacion(int id_calificacion, float nota, int id_estudiante, int id_materia) {
        this.id_calificacion = id_calificacion;  
        this.nota = nota;  
        this.id_estudiante = id_estudiante;  
        this.id_materia = id_materia;  
    }

    // constructor
    public Calificacion(int idCalificacion, String materia, double nota, int idEstudiante) {
        this.id_calificacion = idCalificacion;
        this.nota = (float) nota;
        this.id_estudiante = idEstudiante;
    }

    // getters y setters
    public int getIdCalificacion() {
        return id_calificacion;  
    }

    public void setIdCalificacion(int id_calificacion) {
        this.id_calificacion = id_calificacion;  
    }

    public float getNota() {
        return nota;  
    }

    public void setNota(float nota) {
        this.nota = nota;  
    }

    public int getIdEstudiante() {
        return id_estudiante;  
    }

    public void setIdEstudiante(int id_estudiante) {
        this.id_estudiante = id_estudiante;  
    }

    public int getIdMateria() {  
        return id_materia;
    }

    public void setIdMateria(int id_materia) { 
        this.id_materia = id_materia;
    }

    
    public int getMateria() {  
        return id_materia;  
    }

    // toString para representar un objeto Calificacion como una cadena de texto
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
