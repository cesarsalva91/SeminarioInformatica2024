import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;


public class SchoolService {

    // Cargar estudiantes desde la base de datos
    public List<Estudiante> cargarEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        String sql = "SELECT * FROM Estudiante";
        try (Connection conn = ConexionDB.obtenerConexion(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Estudiante estudiante = new Estudiante(
                    rs.getInt("id_estudiante"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getInt("matricula"),
                    rs.getString("contacto")
                );
                estudiantes.add(estudiante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estudiantes;
    }

    // Guardar estudiantes en la base de datos
    public void guardarEstudiantes(List<Estudiante> estudiantes) {
        String sql = "INSERT INTO Estudiante (nombre, apellido, matricula, contacto) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionDB.obtenerConexion(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Estudiante estudiante : estudiantes) {
                pstmt.setString(1, estudiante.getNombre());
                pstmt.setString(2, estudiante.getApellido());
                pstmt.setInt(3, estudiante.getMatricula());
                pstmt.setString(4, estudiante.getContacto());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Cargar asistencias desde la base de datos
    public List<Asistencia> cargarAsistencias() {
        List<Asistencia> asistencias = new ArrayList<>();
        String sql = "SELECT * FROM Asistencia";
        try (Connection conn = ConexionDB.obtenerConexion(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Asistencia asistencia = new Asistencia(
                    rs.getInt("id_asistencia"),
                    rs.getDate("fecha"),
                    rs.getString("estado"),
                    rs.getBoolean("justificada"),
                    rs.getInt("id_estudiante")
                );
                asistencias.add(asistencia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return asistencias;
    }

    // Guardar asistencias en la base de datos
    public void guardarAsistencias(List<Asistencia> asistencias) {
        String sql = "INSERT INTO Asistencia (fecha, estado, justificada, id_estudiante) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionDB.obtenerConexion(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Asistencia asistencia : asistencias) {
                pstmt.setDate(1, asistencia.getFecha());
                pstmt.setString(2, asistencia.getEstado());
                pstmt.setBoolean(3, asistencia.isJustificada());
                pstmt.setInt(4, asistencia.getIdEstudiante());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Cargar calificaciones desde la base de datos
    public List<Calificacion> cargarCalificaciones() {
        List<Calificacion> calificaciones = new ArrayList<>();
        String sql = "SELECT * FROM Calificacion";
        try (Connection conn = ConexionDB.obtenerConexion(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Calificacion calificacion = new Calificacion(
                    rs.getInt("id_calificacion"),
                    rs.getFloat("nota"),
                    rs.getInt("id_estudiante"),
                    rs.getInt("id_materia")
                );
                calificaciones.add(calificacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return calificaciones;
    }

    // Guardar calificaciones en la base de datos
    public void guardarCalificaciones(List<Calificacion> calificaciones) {
        String sql = "INSERT INTO Calificacion (nota, id_estudiante, id_materia) VALUES (?, ?, ?)";
        try (Connection conn = ConexionDB.obtenerConexion(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Calificacion calificacion : calificaciones) {
                pstmt.setFloat(1, calificacion.getNota());
                pstmt.setInt(2, calificacion.getIdEstudiante());
                pstmt.setInt(3, calificacion.getIdMateria());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Cargar notificaciones desde la base de datos
    public List<Notificacion> cargarNotificaciones() {
        List<Notificacion> notificaciones = new ArrayList<>();
        String sql = "SELECT * FROM Notificacion";
        try (Connection conn = ConexionDB.obtenerConexion(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Notificacion notificacion = new Notificacion(
                    rs.getInt("id_notificacion"),
                    rs.getString("mensaje"),
                    rs.getTimestamp("fecha_envio"),
                    rs.getInt("id_estudiante")
                );
                notificaciones.add(notificacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notificaciones;
    }

    // Guardar notificaciones en la base de datos
    public void guardarNotificaciones(List<Notificacion> notificaciones) {
        String sql = "INSERT INTO Notificacion (mensaje, fecha_envio) VALUES (?, ?)";
        try (Connection conn = ConexionDB.obtenerConexion(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Notificacion notificacion : notificaciones) {
                pstmt.setString(1, notificacion.getMensaje());
                pstmt.setTimestamp(2, notificacion.getFechaEnvio());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para mostrar calificaciones de un estudiante
    public void mostrarCalificaciones(int idEstudiante) {
        List<Calificacion> calificaciones = cargarCalificaciones();
        
        System.out.println("Calificaciones del estudiante con ID " + idEstudiante + ":");
        if (calificaciones.isEmpty()) {
            System.out.println("No se encontraron calificaciones para este estudiante.");
        } else {
            for (Calificacion calificacion : calificaciones) {
                if (calificacion.getIdEstudiante() == idEstudiante) {
                    System.out.println("Materia: " + calificacion.getMateria() + ", Calificación: " + calificacion.getNota());
                }
            }
        }
    }

    
}