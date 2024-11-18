import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DataWriter {

    private static final String FILE_PATH = "data.json";

    // guardar todos los datos en el archivo JSON
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

    // lista de objetos a formato JSON
    private static String listToJson(List<?> list) {
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            json.append(objectToJson(list.get(i)));
            if (i < list.size() - 1) json.append(",");
        }
        json.append("]");
        return json.toString();
    }

    // objeto a formato JSON
    private static String objectToJson(Object obj) {
        if (obj instanceof Estudiante) {
            Estudiante e = (Estudiante) obj;
            return String.format(
                "{\"id_estudiante\": %d, \"nombre\": \"%s\", \"apellido\": \"%s\", \"matricula\": \"%s\", \"contacto\": \"%s\"}",
                e.getIdEstudiante(), e.getNombre(), e.getApellido(), e.getMatricula(), e.getContacto()
            );
        } else if (obj instanceof Asistencia) {
            Asistencia a = (Asistencia) obj;
            return String.format(
                "{\"id_asistencia\": %d, \"fecha\": \"%s\", \"estado\": \"%s\", \"justificada\": %b, \"id_estudiante\": %d}",
                a.getIdAsistencia(), a.getFecha(), a.getEstado(), a.isJustificada(), a.getIdEstudiante()
            );
        } else if (obj instanceof Calificacion) {
            Calificacion c = (Calificacion) obj;
            return String.format(
                "{\"id_calificacion\": %d, \"materia\": %d, \"nota\": %.2f, \"id_estudiante\": %d}",
                c.getIdCalificacion(), c.getMateria(), c.getNota(), c.getIdEstudiante()
            );
        } else if (obj instanceof Notificacion) {
            Notificacion n = (Notificacion) obj;
            return String.format(
                "{\"id_notificacion\": %d, \"mensaje\": \"%s\", \"fechaEnvio\": \"%s\", \"id_estudiante\": %d}",
                n.getIdNotificacion(), n.getMensaje(), n.getFechaEnvio(), n.getIdEstudiante()
            );
        }
        return "{}";
    }
}
