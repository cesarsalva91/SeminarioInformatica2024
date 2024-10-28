//service;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonFileReader {

    // Método para leer los datos de estudiantes desde un archivo JSON
    public List<Estudiante> leerEstudiantes(String filepath) {
        List<Estudiante> estudiantes = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Convertimos el JSON en objetos de la clase Estudiante
                if (line.contains("\"idEstudiante\"")) {
                    int idEstudiante = Integer.parseInt(getValue(line));
                    String nombre = getValue(reader.readLine());
                    String apellido = getValue(reader.readLine());
                    String matricula = getValue(reader.readLine());
                    String contacto = getValue(reader.readLine());

                    estudiantes.add(new Estudiante(idEstudiante, nombre, apellido, matricula, contacto));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
        }
        
        return estudiantes;
    }

    // Método para leer los datos de asistencias desde un archivo JSON
    public List<Asistencia> leerAsistencias(String filepath) {
        List<Asistencia> asistencias = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("\"idAsistencia\"")) {
                    int idAsistencia = Integer.parseInt(getValue(line));
                    String fecha = getValue(reader.readLine());
                    String estado = getValue(reader.readLine());
                    boolean justificada = Boolean.parseBoolean(getValue(reader.readLine()));
                    int idEstudiante = Integer.parseInt(getValue(reader.readLine()));

                    asistencias.add(new Asistencia(idAsistencia, fecha, estado, justificada, idEstudiante));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
        }

        return asistencias;
    }

    // Método para leer los datos de calificaciones desde un archivo JSON
    public List<Calificacion> leerCalificaciones(String filepath) {
        List<Calificacion> calificaciones = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("\"idCalificacion\"")) {
                    int idCalificacion = Integer.parseInt(getValue(line));
                    String materia = getValue(reader.readLine());
                    float nota = Float.parseFloat(getValue(reader.readLine()));
                    int idEstudiante = Integer.parseInt(getValue(reader.readLine()));

                    calificaciones.add(new Calificacion(idCalificacion, materia, nota, idEstudiante));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
        }

        return calificaciones;
    }

    // Método para leer los datos de notificaciones desde un archivo JSON
    public List<Notificacion> leerNotificaciones(String filepath) {
        List<Notificacion> notificaciones = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("\"idNotificacion\"")) {
                    int idNotificacion = Integer.parseInt(getValue(line));
                    String mensaje = getValue(reader.readLine());
                    String fechaEnvio = getValue(reader.readLine());
                    int idEstudiante = Integer.parseInt(getValue(reader.readLine()));

                    notificaciones.add(new Notificacion(idNotificacion, mensaje, fechaEnvio, idEstudiante));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
        }

        return notificaciones;
    }

    // Método auxiliar para extraer el valor de un campo en JSON
    private String getValue(String line) {
        String[] parts = line.split(":");
        return parts[1].replace("\"", "").replace(",", "").trim();
    }
}
