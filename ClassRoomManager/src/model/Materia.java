import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Materia {
    private int idMateria;
    private String nombre;
    private String descripcion;
    private String codigo;

    public Materia(String nombre, String descripcion, String codigo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.codigo = codigo;
    }

    // Getters y Setters
    public int getIdMateria() {
        return idMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    // Método para guardar la materia en la base de datos
    public void guardar() {
        String sql = "INSERT INTO Materia (nombre, descripcion, codigo) VALUES (?, ?, ?)";
        try (Connection connection = ConexionDB.obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, this.nombre);
            statement.setString(2, this.descripcion);
            statement.setString(3, this.codigo);
            statement.executeUpdate();
            System.out.println("Materia guardada exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al guardar la materia: " + e.getMessage());
        }
    }
}