import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DataWriter {

    private static final String FILE_PATH = "data.json";

    // Método general para guardar todos los datos en el archivo JSON
    public static void saveData(List<Estudiante> estudiantes, List<Asistencia> asistencias,
                                List<Calificacion> calificaciones, List<Notificacion> notificaciones) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write("{\n");
            writer.write("\"estudiantes\": " + listToJson(estudiantes) + ",\n");
            writer.write("\"asistencias\": " + listToJson(asistencias) + ",\n");
            writer.write("\"calificaciones\": " + listToJson(calificaciones) + ",\n");
            writer.write("\"notificaciones\": " + listToJson(notificaciones) + "\n");
            writer.write("}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Convertir una lista de objetos a formato JSON
    private static String listToJson(List<?> list) {
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            json.append(objectToJson(list.get(i)));
            if (i < list.size() - 1) json.append(",");
        }
        json.append("]");
        return json.toString();
    }

    // Convertir un objeto individual a formato JSON
    private static String objectToJson(Object obj) {
        if (obj instanceof Estudiante) {
            Estudiante e = (Estudiante) obj;
            return String.format(
                "{\"idEstudiante\": %d, \"nombre\": \"%s\", \"apellido\": \"%s\", \"matricula\": \"%s\", \"contacto\": \"%s\"}",
                e.getIdEstudiante(), e.getNombre(), e.getApellido(), e.getMatricula(), e.getContacto()
            );
        } else if (obj instanceof Asistencia) {
            Asistencia a = (Asistencia) obj;
            return String.format(
                "{\"idAsistencia\": %d, \"fecha\": \"%s\", \"estado\": \"%s\", \"justificada\": %b, \"idEstudiante\": %d}",
                a.getIdAsistencia(), a.getFecha(), a.getEstado(), a.getIdEstudiante()
            );
        } else if (obj instanceof Calificacion) {
            Calificacion c = (Calificacion) obj;
            return String.format(
                "{\"idCalificacion\": %d, \"materia\": \"%s\", \"nota\": %.2f, \"idEstudiante\": %d}",
                c.getIdCalificacion(), c.getMateria(), c.getNota(), c.getIdEstudiante()
            );
        } else if (obj instanceof Notificacion) {
            Notificacion n = (Notificacion) obj;
            return String.format(
                "{\"idNotificacion\": %d, \"mensaje\": \"%s\", \"fechaEnvio\": \"%s\", \"idEstudiante\": %d}",
                n.getIdNotificacion(), n.getMensaje(), n.getFechaEnvio(), n.getIdEstudiante()
            );
        }
        return "{}";
    }

    // Método para guardar datos en la base de datos
    public static void saveDataToDatabase(List<Estudiante> estudiantes, List<Asistencia> asistencias,
                                           List<Calificacion> calificaciones, List<Notificacion> notificaciones) {
        String url = "jdbc:mysql://localhost:3306/tu_base_de_datos"; // Cambia esto a tu URL de base de datos
        String user = "tu_usuario"; // Cambia esto a tu usuario de base de datos
        String password = "tu_contraseña"; // Cambia esto a tu contraseña de base de datos

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Guardar estudiantes
            String sqlEstudiante = "INSERT INTO estudiantes (idEstudiante, nombre, apellido, matricula, contacto) VALUES (?, ?, ?, ?, ?)";
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

            // Similar para asistencias, calificaciones y notificaciones...
            // ... (código omitido para otras inserciones)

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
