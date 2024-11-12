import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DataWriter {

    // Método para guardar datos en la base de datos
    public static void saveDataToDatabase(List<Estudiante> estudiantes, List<Asistencia> asistencias,
                                           List<Calificacion> calificaciones, List<Notificacion> notificaciones) {
        try (Connection conn = ConexionDB.obtenerConexion()) {
            if (conn == null) {
                System.err.println("No se pudo establecer la conexión a la base de datos.");
                return;
            }

            // Guardar estudiantes
            String sqlEstudiante = "INSERT INTO estudiantes (id_estudiante, nombre, apellido, matricula, contacto) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlEstudiante)) {
                for (Estudiante e : estudiantes) {
                    pstmt.setInt(1, e.getIdEstudiante());
                    pstmt.setString(2, e.getNombre());
                    pstmt.setString(3, e.getApellido());
                    pstmt.setString(4, e.getMatricula());
                    pstmt.setString(5, e.getContacto());
                    pstmt.executeUpdate();
                }
            }

            // Guardar asistencias
            String sqlAsistencia = "INSERT INTO asistencia (id_estudiante, fecha, estado) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlAsistencia)) {
                for (Asistencia a : asistencias) {
                    pstmt.setInt(1, a.getIdEstudiante());
                    pstmt.setDate(2, java.sql.Date.valueOf(a.getFecha()));
                    pstmt.setString(3, a.getEstado());
                    pstmt.executeUpdate();
                }
            }

            // Guardar calificaciones
            String sqlCalificacion = "INSERT INTO calificaciones (id_calificacion, materia, nota, id_estudiante) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlCalificacion)) {
                for (Calificacion c : calificaciones) {
                    pstmt.setInt(1, c.getIdCalificacion());
                    pstmt.setInt(2, c.getMateria());
                    pstmt.setDouble(3, c.getNota());
                    pstmt.setInt(4, c.getIdEstudiante());
                    pstmt.executeUpdate();
                }
            }

            // Guardar notificaciones
            String sqlNotificacion = "INSERT INTO notificaciones (id_notificacion, mensaje, fecha_envio, id_estudiante) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlNotificacion)) {
                for (Notificacion n : notificaciones) {
                    pstmt.setInt(1, n.getIdNotificacion());
                    pstmt.setString(2, n.getMensaje());
                    pstmt.setDate(3, java.sql.Date.valueOf(n.getFechaEnvio()));
                    pstmt.setInt(4, n.getIdEstudiante());
                    pstmt.executeUpdate();
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al guardar datos en la base de datos: " + e.getMessage());
        }
    }
}
