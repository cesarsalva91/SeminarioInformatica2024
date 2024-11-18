import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;


public class SchoolService {

    // cargar estudiantes desde la base de datos
    public List<Estudiante> cargarEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        Connection connection = null;

        try {
            connection = ConexionDB.obtenerConexion();
            String query = "SELECT id_estudiante, nombre, apellido, matricula, contacto FROM estudiante";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Estudiante estudiante = new Estudiante(
                    rs.getInt("id_estudiante"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("matricula"),
                    rs.getString("contacto")
                );
                estudiantes.add(estudiante);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar estudiantes: " + e.getMessage());
        } finally {
            ConexionDB.cerrarConexion(connection);
        }

        return estudiantes;
    }

    // guardar estudiantes en la base de datos
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

    // cargar asistencias desde la base de datos
    public List<Asistencia> cargarAsistencias() {
        List<Asistencia> asistencias = new ArrayList<>();
        Connection connection = null;

        try {
            connection = ConexionDB.obtenerConexion();
            String query = "SELECT id_asistencia, fecha, estado, justificada, id_estudiante FROM asistencia";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Asistencia asistencia = new Asistencia(
                    rs.getInt("id_asistencia"),
                    rs.getDate("fecha").toLocalDate(),
                    rs.getString("estado"),
                    rs.getBoolean("justificada"),
                    rs.getInt("id_estudiante")
                );
                asistencias.add(asistencia);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar asistencias: " + e.getMessage());
        } finally {
            ConexionDB.cerrarConexion(connection);
        }

        return asistencias;
    }

    // guardar asistencias en la base de datos
    public void guardarAsistencias(List<Asistencia> asistencias) {
        String sql = "INSERT INTO Asistencia (fecha, estado, justificada, id_estudiante) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionDB.obtenerConexion(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Asistencia asistencia : asistencias) {
                pstmt.setDate(1, java.sql.Date.valueOf(asistencia.getFecha()));
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

    // cargar calificaciones desde la base de datos
    public List<Calificacion> cargarCalificaciones() {
        List<Calificacion> calificaciones = new ArrayList<>();
        Connection connection = null;

        try {
            connection = ConexionDB.obtenerConexion();
            String query = "SELECT id_calificacion, materia, nota, id_estudiante FROM calificaciones";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Calificacion calificacion = new Calificacion(
                    rs.getInt("id_calificacion"),
                    rs.getString("materia"),
                    rs.getDouble("nota"),
                    rs.getInt("id_estudiante")
                );
                calificaciones.add(calificacion);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar calificaciones: " + e.getMessage());
        } finally {
            ConexionDB.cerrarConexion(connection);
        }

        return calificaciones;
    }

    // guardar calificaciones en la base de datos
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

    // cargar notificaciones desde la base de datos
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

    // guardar notificaciones en la base de datos
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

    // mostrar calificaciones de un estudiante
    public void mostrarCalificaciones(int idEstudiante) {
        List<Calificacion> calificaciones = cargarCalificaciones();
        Estudiante estudiante = cargarEstudiantePorId(idEstudiante);

        System.out.println("Calificaciones del estudiante: " + estudiante.getNombre() + " " + estudiante.getApellido());
        if (calificaciones.isEmpty()) {
            System.out.println("No se encontraron calificaciones para este estudiante.");
        } else {
            for (Calificacion calificacion : calificaciones) {
                if (calificacion.getIdEstudiante() == idEstudiante) {
                    Materia materia = cargarMateriaPorId(calificacion.getIdMateria());
                    System.out.println("Materia: " + materia.getNombre() + ", Calificación: " + calificacion.getNota());
                }
            }
        }
    }

    // cargar materia por ID desde la base de datos
    public Materia cargarMateriaPorId(int idMateria) {
        Materia materia = null;
        String sql = "SELECT * FROM Materia WHERE id_materia = ?";
        try (Connection conn = ConexionDB.obtenerConexion(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idMateria);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                materia = new Materia(
                    rs.getInt("id_materia"),
                    rs.getString("nombre")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materia;
    }

    // agregar metodo para cargar estudiante por ID
    public Estudiante cargarEstudiantePorId(int idEstudiante) {
        Estudiante estudiante = null;
        String sql = "SELECT * FROM Estudiante WHERE id_estudiante = ?";
        try (Connection conn = ConexionDB.obtenerConexion(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idEstudiante);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                estudiante = new Estudiante(
                    rs.getInt("id_estudiante"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getInt("matricula"),
                    rs.getString("contacto")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estudiante;
    }

}