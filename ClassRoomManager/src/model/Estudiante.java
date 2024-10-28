import java.io.Serializable;

// Definición de la clase Estudiante, que implementa Serializable para poder guardar objetos de esta clase en archivos.
public class Estudiante implements Serializable {

    // Atributos privados de la clase Estudiante.
    // Estos atributos representan los datos que se almacenan para cada estudiante.
    private int idEstudiante;  
    private String nombre;      
    private String apellido;    
    private String matricula;   
    private String contacto;    

    // Constructor vacío, permite crear un objeto Estudiante sin inicializar los atributos.
    public Estudiante() {}

    // Constructor con parámetros, permite crear un objeto Estudiante e inicializar sus atributos.
    public Estudiante(int idEstudiante, String nombre, String apellido, String matricula, String contacto) {
        this.idEstudiante = idEstudiante;
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
        this.contacto = contacto;
    }

    // Métodos Getters y Setters para cada atributo.
    // Estos métodos permiten acceder y modificar los valores de los atributos desde fuera de la clase, manteniendo la encapsulación.
    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    // Método toString que sobrescribe el método por defecto de Object.
    // Este método permite representar el objeto Estudiante como un String, mostrando todos sus atributos.
    // Es útil para imprimir el objeto y verificar su contenido en un formato legible.
    @Override
    public String toString() {
        return "Estudiante{" +
                "idEstudiante=" + idEstudiante +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", matricula='" + matricula + '\'' +
                ", contacto='" + contacto + '\'' +
                '}';
    }
}
