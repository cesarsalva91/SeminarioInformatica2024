import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class DataReader {

    // Método para obtener todas las asistencias de un estudiante
    public List<Asistencia> obtenerAsistenciasPorEstudiante(int idEstudiante) {
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

    // Método para obtener todas las calificaciones de un estudiante
    public List<Calificacion> obtenerCalificacionesPorEstudiante(int idEstudiante) {
        List<Calificacion> calificaciones = new ArrayList<>();
        String sql = "SELECT * FROM calificaciones WHERE id_estudiante = ? ORDER BY materia";
        
        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idEstudiante);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                calificaciones.add(new Calificacion(
                    rs.getInt("id_calificacion"),
                    rs.getInt("id_estudiante"),
                    rs.getString("materia"),
                    rs.getDouble("nota")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener calificaciones: " + e.getMessage());
        }
        return calificaciones;
    }

    // Método para agregar una nueva asistencia
    public boolean agregarAsistencia(int idEstudiante, LocalDate fecha, String estado) {
        Asistencia asistencia = new Asistencia(idEstudiante, fecha, estado);
        return asistencia.guardar();
    }

    // Método para agregar una nueva calificación
    public boolean agregarCalificacion(int idEstudiante, String materia, double nota) {
        Calificacion calificacion = new Calificacion(idEstudiante, materia, nota);
        return calificacion.guardar();
    }
}
