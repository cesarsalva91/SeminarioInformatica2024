import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Calificacion {
    private int idCalificacion;
    private int idEstudiante;
    private int idMateria;
    private float nota;

    // Constructor
    public Calificacion(int idEstudiante, int idMateria, float nota) {
        this.idEstudiante = idEstudiante;
        this.idMateria = idMateria;
        this.nota = nota;
    }

    // Constructor con ID para cuando se recupera de la BD
    public Calificacion(int idCalificacion, int idEstudiante, int idMateria, float nota) {
        this.idCalificacion = idCalificacion;
        this.idEstudiante = idEstudiante;
        this.idMateria = idMateria;
        this.nota = nota;
    }

    // Métodos CRUD
    public boolean guardar() {
        String sql = "INSERT INTO calificaciones (id_estudiante, id_materia, nota) VALUES (?, ?, ?)";
        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, idEstudiante);
            stmt.setInt(2, idMateria);
            stmt.setFloat(3, nota);
            
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    this.idCalificacion = rs.getInt(1);
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error al guardar calificación: " + e.getMessage());
            return false;
        }
    }

    public static Calificacion buscarPorId(int id) {
        String sql = "SELECT * FROM calificaciones WHERE id_calificacion = ?";
        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Calificacion(
                    rs.getInt("id_calificacion"),
                    rs.getInt("id_estudiante"),
                    rs.getInt("id_materia"),
                    rs.getFloat("nota")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar calificación: " + e.getMessage());
        }
        return null;
    }

    public boolean actualizar() {
        String sql = "UPDATE calificaciones SET id_estudiante = ?, id_materia = ?, nota = ? WHERE id_calificacion = ?";
        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idEstudiante);
            stmt.setInt(2, idMateria);
            stmt.setFloat(3, nota);
            stmt.setInt(4, idCalificacion);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar calificación: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar() {
        String sql = "DELETE FROM calificaciones WHERE id_calificacion = ?";
        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idCalificacion);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar calificación: " + e.getMessage());
            return false;
        }
    }

    // Método para obtener todas las calificaciones de un estudiante
    public static List<Calificacion> obtenerCalificacionesPorEstudiante(int idEstudiante) {
        List<Calificacion> calificaciones = new ArrayList<>();
        String sql = "SELECT * FROM calificacion WHERE id_estudiante = ?";
        
        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idEstudiante);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                calificaciones.add(new Calificacion(
                    rs.getInt("id_calificacion"),
                    rs.getInt("id_estudiante"),
                    rs.getInt("id_materia"),
                    rs.getFloat("nota")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener calificaciones: " + e.getMessage());
        }
        return calificaciones;
    }

    // Getters y Setters
    public int getIdCalificacion() { return idCalificacion; }
    public int getIdEstudiante() { return idEstudiante; }
    public int getIdMateria() { return idMateria; }
    public float getNota() { return nota; }

    public void setIdEstudiante(int idEstudiante) { this.idEstudiante = idEstudiante; }
    public void setMateria(int idMateria) { this.idMateria = idMateria; }
    public void setNota(float nota) { this.nota = nota; }

    // Agregar este método a la clase Calificacion
    public int getMateria() {
        return idMateria; // Devuelve el idMateria
    }
}
