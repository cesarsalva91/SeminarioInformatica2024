//service;



import java.util.List;

public class JsonDatabase {

    private static final String ESTUDIANTES_FILE = "estudiantes.json";
    private static final String ASISTENCIAS_FILE = "asistencias.json";
    private static final String CALIFICACIONES_FILE = "calificaciones.json";
    private static final String NOTIFICACIONES_FILE = "notificaciones.json";

    private JsonFileReader jsonReader;
    private JsonFileWriter jsonWriter;

    // Constructor
    public JsonDatabase() {
        this.jsonReader = new JsonFileReader();
        this.jsonWriter = new JsonFileWriter();
    }

    // Cargar datos desde los archivos JSON
    public List<Estudiante> cargarEstudiantes() {
        return jsonReader.leerEstudiantes(ESTUDIANTES_FILE);
    }

    public List<Asistencia> cargarAsistencias() {
        return jsonReader.leerAsistencias(ASISTENCIAS_FILE);
    }

    public List<Calificacion> cargarCalificaciones() {
        return jsonReader.leerCalificaciones(CALIFICACIONES_FILE);
    }

    public List<Notificacion> cargarNotificaciones() {
        return jsonReader.leerNotificaciones(NOTIFICACIONES_FILE);
    }

    // Guardar datos en los archivos JSON
    public void guardarEstudiantes(List<Estudiante> estudiantes) {
        jsonWriter.escribirEstudiantes(estudiantes, ESTUDIANTES_FILE);
    }

    public void guardarAsistencias(List<Asistencia> asistencias) {
        jsonWriter.escribirAsistencias(asistencias, ASISTENCIAS_FILE);
    }

    public void guardarCalificaciones(List<Calificacion> calificaciones) {
        jsonWriter.escribirCalificaciones(calificaciones, CALIFICACIONES_FILE);
    }

    public void guardarNotificaciones(List<Notificacion> notificaciones) {
        jsonWriter.escribirNotificaciones(notificaciones, NOTIFICACIONES_FILE);
    }
}
