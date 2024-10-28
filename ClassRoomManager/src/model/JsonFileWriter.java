//service;


import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonFileWriter {

    // Método para escribir la lista de estudiantes en un archivo JSON
    public void escribirEstudiantes(List<Estudiante> estudiantes, String filepath) {
        try (FileWriter writer = new FileWriter(filepath)) {
            writer.write("[\n");
            for (int i = 0; i < estudiantes.size(); i++) {
                Estudiante estudiante = estudiantes.get(i);
                writer.write("  {\n");
                writer.write("    \"idEstudiante\": " + estudiante.getIdEstudiante() + ",\n");
                writer.write("    \"nombre\": \"" + estudiante.getNombre() + "\",\n");
                writer.write("    \"apellido\": \"" + estudiante.getApellido() + "\",\n");
                writer.write("    \"matricula\": \"" + estudiante.getMatricula() + "\",\n");
                writer.write("    \"contacto\": \"" + estudiante.getContacto() + "\"\n");
                writer.write("  }" + (i < estudiantes.size() - 1 ? "," : "") + "\n");
            }
            writer.write("]");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo JSON: " + e.getMessage());
        }
    }

    // Método para escribir la lista de asistencias en un archivo JSON
    public void escribirAsistencias(List<Asistencia> asistencias, String filepath) {
        try (FileWriter writer = new FileWriter(filepath)) {
            writer.write("[\n");
            for (int i = 0; i < asistencias.size(); i++) {
                Asistencia asistencia = asistencias.get(i);
                writer.write("  {\n");
                writer.write("    \"idAsistencia\": " + asistencia.getIdAsistencia() + ",\n");
                writer.write("    \"fecha\": \"" + asistencia.getFecha() + "\",\n");
                writer.write("    \"estado\": \"" + asistencia.getEstado() + "\",\n");
                writer.write("    \"justificada\": " + asistencia.isJustificada() + ",\n");
                writer.write("    \"idEstudiante\": " + asistencia.getIdEstudiante() + "\n");
                writer.write("  }" + (i < asistencias.size() - 1 ? "," : "") + "\n");
            }
            writer.write("]");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo JSON: " + e.getMessage());
        }
    }

    // Método para escribir la lista de calificaciones en un archivo JSON
    public void escribirCalificaciones(List<Calificacion> calificaciones, String filepath) {
        try (FileWriter writer = new FileWriter(filepath)) {
            writer.write("[\n");
            for (int i = 0; i < calificaciones.size(); i++) {
                Calificacion calificacion = calificaciones.get(i);
                writer.write("  {\n");
                writer.write("    \"idCalificacion\": " + calificacion.getIdCalificacion() + ",\n");
                writer.write("    \"materia\": \"" + calificacion.getMateria() + "\",\n");
                writer.write("    \"nota\": " + calificacion.getNota() + ",\n");
                writer.write("    \"idEstudiante\": " + calificacion.getIdEstudiante() + "\n");
                writer.write("  }" + (i < calificaciones.size() - 1 ? "," : "") + "\n");
            }
            writer.write("]");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo JSON: " + e.getMessage());
        }
    }

    // Método para escribir la lista de notificaciones en un archivo JSON
    public void escribirNotificaciones(List<Notificacion> notificaciones, String filepath) {
        try (FileWriter writer = new FileWriter(filepath)) {
            writer.write("[\n");
            for (int i = 0; i < notificaciones.size(); i++) {
                Notificacion notificacion = notificaciones.get(i);
                writer.write("  {\n");
                writer.write("    \"idNotificacion\": " + notificacion.getIdNotificacion() + ",\n");
                writer.write("    \"mensaje\": \"" + notificacion.getMensaje() + "\",\n");
                writer.write("    \"fechaEnvio\": \"" + notificacion.getFechaEnvio() + "\",\n");
                writer.write("    \"idEstudiante\": " + notificacion.getIdEstudiante() + "\n");
                writer.write("  }" + (i < notificaciones.size() - 1 ? "," : "") + "\n");
            }
            writer.write("]");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo JSON: " + e.getMessage());
        }
    }
}
