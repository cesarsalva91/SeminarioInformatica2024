import java.io.FileWriter;
import java.io.IOException;
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
                a.getIdAsistencia(), a.getFecha(), a.getEstado(), a.isJustificada(), a.getIdEstudiante()
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
}
