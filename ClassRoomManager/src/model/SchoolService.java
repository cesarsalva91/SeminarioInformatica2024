//service;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SchoolService {
    private Connection connection;

    public SchoolService() {
        // Establecer la conexión a la base de datos
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tu_base_de_datos", "usuario", "contraseña");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para establecer la conexión a la base de datos
    private Connection conectar() throws Exception {
        String url = "jdbc:mysql://localhost:3306/tu_base_de_datos"; // Cambia esto a tu URL de base de datos
        String usuario = "tu_usuario"; // Cambia esto a tu usuario
        String contrasena = "tu_contrasena"; // Cambia esto a tu contraseña
        return DriverManager.getConnection(url, usuario, contrasena);
    }

    // Cargar estudiantes desde la base de datos
    public List<Estudiante> cargarEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes";
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Asistencia asistencia = new Asistencia(
                    rs.getInt("idAsistencia"),
                    rs.getInt("idEstudiante"),
                    rs.getDate("fecha").toLocalDate(),
                    rs.getString("estado")
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
        String sql = "INSERT INTO asistencias (idAsistencia, fecha, estado, justificada, idEstudiante) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Asistencia asistencia : asistencias) {
                pstmt.setInt(1, asistencia.getIdAsistencia());
                pstmt.setString(2, asistencia.getFecha());
                pstmt.setString(3, asistencia.getEstado());
                pstmt.setBoolean(4, asistencia.isJustificada());
                pstmt.setInt(5, asistencia.getIdEstudiante());
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
        String sql = "SELECT * FROM calificaciones";
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Calificacion calificacion = new Calificacion(
                    rs.getInt("idCalificacion"),
                    rs.getInt("idEstudiante"),
                    rs.getString("materia"),
                    rs.getDouble("nota")
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
        String sql = "INSERT INTO calificaciones (idCalificacion, idEstudiante, nota, materia) VALUES (?, ?, ?, ?)";
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Calificacion calificacion : calificaciones) {
                pstmt.setInt(1, calificacion.getIdCalificacion());
                pstmt.setInt(2, calificacion.getIdEstudiante());
                pstmt.setDouble(3, calificacion.getNota());
                pstmt.setString(4, calificacion.getMateria());
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
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Notificacion notificacion = new Notificacion(
                    rs.getInt("idNotificacion"),
                    rs.getString("mensaje"),
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
        String sql = "INSERT INTO notificaciones (idNotificacion, mensaje, idEstudiante) VALUES (?, ?, ?)";
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Notificacion notificacion : notificaciones) {
                pstmt.setInt(1, notificacion.getIdNotificacion());
                pstmt.setString(2, notificacion.getMensaje());
                pstmt.setInt(3, notificacion.getIdEstudiante());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para cerrar la conexión
    public void cerrarConexion() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}