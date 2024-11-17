import java.io.Serializable;

// Definición de la clase Estudiante, que implementa Serializable para poder guardar objetos de esta clase en archivos.
public class Estudiante implements Serializable {

    // Atributos privados de la clase Estudiante, adaptados a la estructura de la base de datos.
    private int id_estudiante;  // id_estudiante como INT
    private String nombre;      // nombre como VARCHAR(100)
    private String apellido;    // apellido como VARCHAR(100)
    private int matricula;      // matricula como INT
    private String contacto;     // contacto como VARCHAR(100)

    // Constructor vacío, permite crear un objeto Estudiante sin inicializar los atributos.
    public Estudiante() {}

    // Constructor con parámetros, permite crear un objeto Estudiante e inicializar sus atributos.
    public Estudiante(int id_estudiante, String nombre, String apellido, int matricula, String contacto) {
        this.id_estudiante = id_estudiante;  
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;  
        this.contacto = contacto;
    }

    // Agregar este constructor a la clase Estudiante
    public Estudiante(int idEstudiante, String nombre, String apellido, String matricula, String contacto) {
        this.id_estudiante = idEstudiante;
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = Integer.parseInt(matricula);
        this.contacto = contacto;
    }

    // Métodos Getters y Setters para cada atributo.
    public int getIdEstudiante() {
        return id_estudiante;  
    }

    public void setIdEstudiante(int id_estudiante) {
        this.id_estudiante = id_estudiante;  
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getMatricula() {
        return matricula;  
    }

    public void setMatricula(int matricula) {  
        this.matricula = matricula;  
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    // Método toString que sobrescribe el método por defecto de Object.
    @Override
    public String toString() {
        return "Estudiante{" +
                "id_estudiante=" + id_estudiante +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", matricula=" + matricula +  // Cambiado a int
                ", contacto='" + contacto + '\'' +
                '}';
    }
}
