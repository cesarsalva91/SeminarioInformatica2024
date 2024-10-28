import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Main {
    private static final String DATA_DIR = "data";
    private static final String FILE_PATH = DATA_DIR + "/estudiantes.json";

    private static void initializeFileStructure() {
        // Crear directorio data si no existe
        File directory = new File(DATA_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Crear archivo estudiantes.json si no existe
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
                // Escribir estructura JSON inicial
                FileWriter writer = new FileWriter(file);
                writer.write("{\"estudiantes\":[]}");
                writer.close();
                System.out.println("Archivo JSON inicializado correctamente.");
            } catch (IOException e) {
                System.err.println("Error al crear el archivo JSON inicial: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        // Inicializar la estructura de archivos
        initializeFileStructure();

        SchoolService schoolService = new SchoolService();

        // Cargar estudiantes desde el archivo JSON
        List<Estudiante> estudiantes = schoolService.cargarEstudiantes();
        System.out.println("Estudiantes cargados: " + estudiantes);

        // Agregar un nuevo estudiante y guardar la lista actualizada
        Estudiante nuevoEstudiante = new Estudiante(4, "Ana", "Ramírez", "M004", "ana.ramirez@example.com");
        estudiantes.add(nuevoEstudiante);
        schoolService.guardarEstudiantes(estudiantes);

        System.out.println("Nuevo estudiante agregado y guardado en el archivo JSON.");
    }
}
