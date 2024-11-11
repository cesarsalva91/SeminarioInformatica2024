import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Asistencia {
    private int idAsistencia;
    private int idEstudiante;
    private LocalDate fecha;
    private String estado;

    // Constructor
    public Asistencia(int idEstudiante, LocalDate fecha, String estado) {
        this.idEstudiante = idEstudiante;
        this.fecha = fecha;
        this.estado = estado;
    }

    // Constructor con ID para cuando se recupera de la BD
    public Asistencia(int idAsistencia, int idEstudiante, LocalDate fecha, String estado) {
        this.idAsistencia = idAsistencia;
        this.idEstudiante = idEstudiante;
        this.fecha = fecha;
        this.estado = estado;
    }

    // Métodos CRUD
    public boolean guardar() {
        String sql = "INSERT INTO asistencia (id_estudiante, fecha, estado) VALUES (?, ?, ?)";
        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, idEstudiante);
            stmt.setDate(2, Date.valueOf(fecha));
            stmt.setString(3, estado);
            
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    this.idAsistencia = rs.getInt(1);
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error al guardar asistencia: " + e.getMessage());
            return false;
        }
    }

    public static Asistencia buscarPorId(int id) {
        String sql = "SELECT * FROM asistencia WHERE id_asistencia = ?";
        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Asistencia(
                    rs.getInt("id_asistencia"),
                    rs.getInt("id_estudiante"),
                    rs.getDate("fecha").toLocalDate(),
                    rs.getString("estado")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar asistencia: " + e.getMessage());
        }
        return null;
    }

    public boolean actualizar() {
        String sql = "UPDATE asistencia SET id_estudiante = ?, fecha = ?, estado = ? WHERE id_asistencia = ?";
        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idEstudiante);
            stmt.setDate(2, Date.valueOf(fecha));
            stmt.setString(3, estado);
            stmt.setInt(4, idAsistencia);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar asistencia: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar() {
        String sql = "DELETE FROM asistencia WHERE id_asistencia = ?";
        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idAsistencia);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar asistencia: " + e.getMessage());
            return false;
        }
    }

    // Método para obtener todas las asistencias de un estudiante
    public static List<Asistencia> obtenerAsistenciasPorEstudiante(int idEstudiante) {
        List<Asistencia> asistencias = new ArrayList<>();
        String sql = "SELECT * FROM asistencia WHERE id_estudiante = ? ORDER BY fecha";
        
        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idEstudiante);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                asistencias.add(new Asistencia(
                    rs.getInt("id_asistencia"),
                    rs.getInt("id_estudiante"),
                    rs.getDate("fecha").toLocalDate(),
                    rs.getString("estado")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener asistencias: " + e.getMessage());
        }
        return asistencias;
    }

    // Getters y Setters
    public int getIdAsistencia() { return idAsistencia; }
    public int getIdEstudiante() { return idEstudiante; }
    public LocalDate getFecha() { return fecha; }
    public String getEstado() { return estado; }

    public void setIdEstudiante(int idEstudiante) { this.idEstudiante = idEstudiante; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setEstado(String estado) { this.estado = estado; }
}
