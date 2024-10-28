//service;

import java.util.List;


public class SchoolService {

    private JsonFileReader jsonFileReader = new JsonFileReader();
    private JsonFileWriter jsonFileWriter = new JsonFileWriter();

    private String estudiantesPath = "data/estudiantes.json";
    private String asistenciasPath = "data/asistencias.json";
    private String calificacionesPath = "data/calificaciones.json";
    private String notificacionesPath = "data/notificaciones.json";

    // Cargar estudiantes
    public List<Estudiante> cargarEstudiantes() {
        return jsonFileReader.leerEstudiantes(estudiantesPath);
    }

    // Guardar estudiantes
    public void guardarEstudiantes(List<Estudiante> estudiantes) {
        jsonFileWriter.escribirEstudiantes(estudiantes, estudiantesPath);
    }

    // Cargar y guardar métodos similares para Asistencia, Calificación y Notificación
    public List<Asistencia> cargarAsistencias() {
        return jsonFileReader.leerAsistencias(asistenciasPath);
    }

    public void guardarAsistencias(List<Asistencia> asistencias) {
        jsonFileWriter.escribirAsistencias(asistencias, asistenciasPath);
    }

    public List<Calificacion> cargarCalificaciones() {
        return jsonFileReader.leerCalificaciones(calificacionesPath);
    }

    public void guardarCalificaciones(List<Calificacion> calificaciones) {
        jsonFileWriter.escribirCalificaciones(calificaciones, calificacionesPath);
    }

    public List<Notificacion> cargarNotificaciones() {
        return jsonFileReader.leerNotificaciones(notificacionesPath);
    }

    public void guardarNotificaciones(List<Notificacion> notificaciones) {
        jsonFileWriter.escribirNotificaciones(notificaciones, notificacionesPath);
    }
}