import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class DataReader {

    private static final String FILE_PATH = "data.json";

    
    public static void loadData(List<Estudiante> estudiantes, List<Asistencia> asistencias,
                                List<Calificacion> calificaciones, List<Notificacion> notificaciones) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            StringBuilder jsonContent = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
            parseJsonData(jsonContent.toString(), estudiantes, asistencias, calificaciones, notificaciones);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // JSON 
    private static void parseJsonData(String json, List<Estudiante> estudiantes, List<Asistencia> asistencias,
                                      List<Calificacion> calificaciones, List<Notificacion> notificaciones) {
        if (json.contains("\"estudiantes\":")) {
            estudiantes.addAll(parseEstudiantes(extractArray(json, "\"estudiantes\":")));
        }
        if (json.contains("\"asistencias\":")) {
            asistencias.addAll(parseAsistencias(extractArray(json, "\"asistencias\":")));
        }
        if (json.contains("\"calificaciones\":")) {
            calificaciones.addAll(parseCalificaciones(extractArray(json, "\"calificaciones\":")));
        }
        if (json.contains("\"notificaciones\":")) {
            notificaciones.addAll(parseNotificaciones(extractArray(json, "\"notificaciones\":")));
        }
    }

    // extraer una lista en formato JSON
    private static String extractArray(String json, String key) {
        int start = json.indexOf(key) + key.length();
        int end = json.indexOf("]", start) + 1;
        return json.substring(start, end).trim();
    }

    // datos de Estudiantes
    private static List<Estudiante> parseEstudiantes(String json) {
        List<Estudiante> list = new ArrayList<>();
        String[] items = json.replace("[", "").replace("]", "").split("},");
        for (String item : items) {
            item = item.trim() + "}";
            Estudiante e = new Estudiante();
            e.setIdEstudiante(Integer.parseInt(extractValue(item, "idEstudiante")));
            e.setNombre(extractValue(item, "nombre"));
            e.setApellido(extractValue(item, "apellido"));
            e.setMatricula(Integer.parseInt(extractValue(item, "matricula")));
            e.setContacto(extractValue(item, "contacto"));
            list.add(e);
        }
        return list;
    }

    // datos de Asistencias
    private static List<Asistencia> parseAsistencias(String json) {
        List<Asistencia> list = new ArrayList<>();
        String[] items = json.replace("[", "").replace("]", "").split("},");
        for (String item : items) {
            item = item.trim() + "}";
            Asistencia a = new Asistencia();
            a.setIdAsistencia(Integer.parseInt(extractValue(item, "idAsistencia")));
            a.setFecha(LocalDate.parse(extractValue(item, "fecha")));
            a.setEstado(extractValue(item, "estado"));
            a.setJustificada(Boolean.parseBoolean(extractValue(item, "justificada")));
            a.setIdEstudiante(Integer.parseInt(extractValue(item, "idEstudiante")));
            list.add(a);
        }
        return list;
    }

    // datos de Calificaciones
    private static List<Calificacion> parseCalificaciones(String json) {
        List<Calificacion> list = new ArrayList<>();
        String[] items = json.replace("[", "").replace("]", "").split("},");
        for (String item : items) {
            item = item.trim() + "}";
            Calificacion c = new Calificacion();
            c.setIdCalificacion(Integer.parseInt(extractValue(item, "idCalificacion")));
            c.setNota(Float.parseFloat(extractValue(item, "nota")));
            c.setIdEstudiante(Integer.parseInt(extractValue(item, "idEstudiante")));
            list.add(c);
        }
        return list;
    }

    // datos de Notificaciones
    private static List<Notificacion> parseNotificaciones(String json) {
        List<Notificacion> list = new ArrayList<>();
        String[] items = json.replace("[", "").replace("]", "").split("},");
        for (String item : items) {
            item = item.trim() + "}";
            Notificacion n = new Notificacion();
            n.setIdNotificacion(Integer.parseInt(extractValue(item, "idNotificacion")));
            n.setMensaje(extractValue(item, "mensaje"));
            n.setFechaEnvio(Timestamp.valueOf(extractValue(item, "fechaEnvio")));
            n.setIdEstudiante(Integer.parseInt(extractValue(item, "idEstudiante")));
            list.add(n);
        }
        return list;
    }

    // extraer un valor de un objeto JSON
    private static String extractValue(String json, String key) {
        int start = json.indexOf("\"" + key + "\":") + key.length() + 3;
        int end = json.indexOf(",", start);
        if (end == -1) end = json.indexOf("}", start);
        return json.substring(start, end).replace("\"", "").trim();
    }
}
