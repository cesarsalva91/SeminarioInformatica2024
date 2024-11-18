import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Materia {
    private int id_materia;
    private String nombre;
    private String descripcion;

    public Materia(int id_materia, String nombre, String descripcion) {
        this.id_materia = id_materia;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    public Materia(int id_materia, String nombre) {
        this.id_materia = id_materia;
        this.nombre = nombre;
        
    }
    
    public Materia( String nombre, String descripcion) {
        
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // getters y setters
    public int getIdMateria() {
        return id_materia;
    }

    public void setIdMateria(int id_materia) {
        this.id_materia = id_materia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // guardar la materia en la base de datos
    public void guardar() {
        String sql = "INSERT INTO Materia (nombre, descripcion) VALUES (?, ?)";
        try (Connection connection = ConexionDB.obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, this.nombre);
            statement.setString(2, this.descripcion);
            statement.executeUpdate();
            System.out.println("Materia guardada exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al guardar la materia: " + e.getMessage());
        }
    }

    // toString que sobrescribe el método por defecto de Object
    @Override
    public String toString() {
        return "Materia{" +
                "id_materia=" + id_materia +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}