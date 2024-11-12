//service;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SchoolService {

    // Cargar estudiantes desde la base de datos
    public List<Estudiante> cargarEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes";
        try (Connection conn = ConexionDB.obtenerConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Estudiante estudiante = new Estudiante(
                    rs.getInt("idEstudiante"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("matricula"),
                    rs.getString("contacto")
                );
                estudiantes.add(estudiante);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estudiantes;
    }

    // Guardar estudiantes en la base de datos
    public void guardarEstudiantes(List<Estudiante> estudiantes) {
        String sql = "INSERT INTO estudiantes (idEstudiante, nombre, apellido, matricula, contacto) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.obtenerConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Estudiante estudiante : estudiantes) {
                pstmt.setInt(1, estudiante.getIdEstudiante());
                pstmt.setString(2, estudiante.getNombre());
                pstmt.setString(3, estudiante.getApellido());
                pstmt.setString(4, estudiante.getMatricula());
                pstmt.setString(5, estudiante.getContacto());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Cargar asistencias desde la base de datos
    public List<Asistencia> cargarAsistencias() {
        List<Asistencia> asistencias = new ArrayList<>();
        String sql = "SELECT * FROM asistencias";
        try (Connection conn = ConexionDB.obtenerConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Asistencia asistencia = new Asistencia(
                    rs.getInt("idAsistencia"),
                    rs.getInt("idEstudiante"),
                    rs.getDate("fecha").toLocalDate(),
                    rs.getString("estado"),
                    rs.getBoolean("justificada")
                );
                asistencias.add(asistencia);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return asistencias;
    }

    // Guardar asistencias en la base de datos
    public void guardarAsistencias(List<Asistencia> asistencias) {
        String sql = "INSERT INTO asistencias (idAsistencia, fecha, estado, idEstudiante) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionDB.obtenerConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Asistencia asistencia : asistencias) {
                pstmt.setInt(1, asistencia.getIdAsistencia());
                pstmt.setDate(2, java.sql.Date.valueOf(asistencia.getFecha()));
                pstmt.setString(3, asistencia.getEstado());
                pstmt.setInt(4, asistencia.getIdEstudiante());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Cargar calificaciones desde la base de datos
    public List<Calificacion> cargarCalificaciones() {
        List<Calificacion> calificaciones = new ArrayList<>();
        String sql = "SELECT * FROM calificacion";
        try (Connection conn = ConexionDB.obtenerConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Calificacion calificacion = new Calificacion(
                    rs.getInt("id_calificacion"),
                    rs.getInt("id_estudiante"),
                    rs.getInt("id_materia"),
                    rs.getFloat("nota")
                );
                calificaciones.add(calificacion);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return calificaciones;
    }

    // Guardar calificaciones en la base de datos
    public void guardarCalificaciones(List<Calificacion> calificaciones) {
        String sql = "INSERT INTO calificacion (id_estudiante, id_materia, nota) VALUES (?, ?, ?)";
        try (Connection conn = ConexionDB.obtenerConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Calificacion calificacion : calificaciones) {
                pstmt.setInt(1, calificacion.getIdEstudiante());
                pstmt.setInt(2, calificacion.getIdMateria());
                pstmt.setFloat(3, calificacion.getNota());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Cargar notificaciones desde la base de datos
    public List<Notificacion> cargarNotificaciones() {
        List<Notificacion> notificaciones = new ArrayList<>();
        String sql = "SELECT * FROM notificaciones";
        try (Connection conn = ConexionDB.obtenerConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Notificacion notificacion = new Notificacion(
                    rs.getInt("idNotificacion"),
                    rs.getString("mensaje"),
                    rs.getString("fechaEnvio"),
                    rs.getInt("idEstudiante")
                );
                notificaciones.add(notificacion);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notificaciones;
    }

    // Guardar notificaciones en la base de datos
    public void guardarNotificaciones(List<Notificacion> notificaciones) {
        String sql = "INSERT INTO notificaciones (idNotificacion, mensaje, fechaEnvio, idEstudiante) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionDB.obtenerConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Notificacion notificacion : notificaciones) {
                pstmt.setInt(1, notificacion.getIdNotificacion());
                pstmt.setString(2, notificacion.getMensaje());
                pstmt.setString(3, notificacion.getFechaEnvio());
                pstmt.setInt(4, notificacion.getIdEstudiante());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para mostrar calificaciones de un estudiante
    public void mostrarCalificaciones(int idEstudiante) {
        List<Calificacion> calificaciones = Calificacion.obtenerCalificacionesPorEstudiante(idEstudiante);
        
        System.out.println("Calificaciones del estudiante con ID " + idEstudiante + ":");
        if (calificaciones.isEmpty()) {
            System.out.println("No se encontraron calificaciones para este estudiante.");
        } else {
            for (Calificacion calificacion : calificaciones) {
                System.out.println("Materia: " + calificacion.getIdMateria() + ", Calificación: " + calificacion.getNota());
            }
        }
    }
}