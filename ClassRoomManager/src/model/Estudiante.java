import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// Definición de la clase Estudiante, que implementa Serializable para poder guardar objetos de esta clase en archivos.
public class Estudiante implements Serializable {

    // Atributos privados de la clase Estudiante.
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

    // Método para guardar un estudiante en la base de datos
    public void guardar() {
        String sql = "INSERT INTO estudiantes (id_estudiante, nombre, apellido, matricula, contacto) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.obtenerConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, this.idEstudiante);
            pstmt.setString(2, this.nombre);
            pstmt.setString(3, this.apellido);
            pstmt.setString(4, this.matricula);
            pstmt.setString(5, this.contacto);
            pstmt.executeUpdate();
            System.out.println("Estudiante guardado exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para recuperar un estudiante por ID
    public static Estudiante obtenerPorId(int id) {
        String sql = "SELECT * FROM estudiantes WHERE id_estudiante = ?";
        Estudiante estudiante = null;
        try (Connection conn = ConexionDB.obtenerConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                estudiante = new Estudiante(
                    rs.getInt("id_estudiante"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("matricula"),
                    rs.getString("contacto")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estudiante;
    }
}
