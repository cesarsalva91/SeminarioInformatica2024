import java.io.Serializable;


public class Estudiante implements Serializable {

    // Atributos 
    private int id_estudiante;  
    private String nombre;      
    private String apellido;    
    private int matricula;      
    private String contacto;    

    // constructor vacio
    public Estudiante() {}

    // constructor con parametros
    public Estudiante(int id_estudiante, String nombre, String apellido, int matricula, String contacto) {
        this.id_estudiante = id_estudiante;  
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;  
        this.contacto = contacto;
    }

    // constructor 
    public Estudiante(int idEstudiante, String nombre, String apellido, String matricula, String contacto) {
        this.id_estudiante = idEstudiante;
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = Integer.parseInt(matricula);
        this.contacto = contacto;
    }

    // getters y setters
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

    // toString que sobrescribe el método por defecto de Object
    @Override
    public String toString() {
        return "Estudiante{" +
                "id_estudiante=" + id_estudiante +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", matricula=" + matricula +  
                ", contacto='" + contacto + '\'' +
                '}';
    }
}
