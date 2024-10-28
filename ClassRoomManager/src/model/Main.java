import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    private static SchoolService schoolService = new SchoolService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Inicializar la estructura de archivos
        initializeFileStructure();

        while (true) {
            System.out.println("\n=== SISTEMA DE GESTIÓN ESCOLAR ===");
            System.out.println("1. Agregar estudiante");
            System.out.println("2. Registrar asistencia");
            System.out.println("3. Asociar materia a estudiante");
            System.out.println("4. Consultar asistencias");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            
            switch (opcion) {
                case 1 -> agregarEstudiante();
                case 2 -> registrarAsistencia();
                case 3 -> asociarMateria();
                case 4 -> consultarAsistencias();
                case 5 -> {
                    System.out.println("¡Hasta luego!");
                    return;
                }
                default -> System.out.println("Opción no válida");
            }
        }
    }

    private static void agregarEstudiante() {
        System.out.println("\n=== AGREGAR ESTUDIANTE ===");
        
        List<Estudiante> estudiantes = schoolService.cargarEstudiantes();
        int nuevoId = estudiantes.isEmpty() ? 1 : estudiantes.get(estudiantes.size() - 1).getIdEstudiante() + 1;

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();
        
        System.out.print("Email de contacto: ");
        String contacto = scanner.nextLine();

        Estudiante nuevoEstudiante = new Estudiante(nuevoId, nombre, apellido, matricula, contacto);
        estudiantes.add(nuevoEstudiante);
        schoolService.guardarEstudiantes(estudiantes);

        System.out.println("Estudiante agregado exitosamente.");
    }

    private static void registrarAsistencia() {
        System.out.println("\n=== REGISTRAR ASISTENCIA ===");
        
        List<Estudiante> estudiantes = schoolService.cargarEstudiantes();
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }

        System.out.print("Ingrese la matrícula del estudiante: ");
        String matricula = scanner.nextLine();

        // Buscar estudiante por matrícula
        Estudiante estudiante = estudiantes.stream()
                .filter(e -> e.getMatricula().equals(matricula))
                .findFirst()
                .orElse(null);

        if (estudiante == null) {
            System.out.println("No se encontró ningún estudiante con esa matrícula.");
            return;
        }

        // Mostrar datos del estudiante encontrado
        System.out.println("\nEstudiante encontrado:");
        System.out.println("Nombre: " + estudiante.getNombre() + " " + estudiante.getApellido());
        System.out.println("Matrícula: " + estudiante.getMatricula());

        List<Asistencia> asistencias = schoolService.cargarAsistencias();
        int nuevoId = asistencias.isEmpty() ? 1 : asistencias.get(asistencias.size() - 1).getIdAsistencia() + 1;

        String fecha = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
        
        System.out.println("\nEstado de asistencia:");
        System.out.println("1. Presente");
        System.out.println("2. Ausente");
        System.out.print("Seleccione: ");
        int estadoOpcion = scanner.nextInt();
        String estado = estadoOpcion == 1 ? "Presente" : "Ausente";

        // Solo preguntar por justificación si está ausente
        boolean justificada = false;
        if (estado.equals("Ausente")) {
            System.out.print("¿Está justificada? (s/n): ");
            justificada = scanner.next().toLowerCase().startsWith("s");
        }

        Asistencia nuevaAsistencia = new Asistencia(nuevoId, fecha, estado, justificada, estudiante.getIdEstudiante());
        asistencias.add(nuevaAsistencia);
        schoolService.guardarAsistencias(asistencias);

        System.out.println("Asistencia registrada exitosamente.");
    }

    private static void agregarMateria() {
        System.out.println("\n=== AGREGAR MATERIA ===");
        
        System.out.print("Nombre de la materia: ");
        String nombreMateria = scanner.nextLine();
        
        System.out.print("Descripción de la materia: ");
        String descripcion = scanner.nextLine();
        
        // Aquí puedes agregar la lógica para guardar la materia en un archivo JSON
        // Similar a como se hace con estudiantes y asistencias
        
        System.out.println("Materia agregada exitosamente.");
    }

    private static void asociarMateria() {
        System.out.println("\n=== ASOCIAR MATERIA ===");
        
        List<Estudiante> estudiantes = schoolService.cargarEstudiantes();
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }

        System.out.print("Ingrese la matrícula del estudiante: ");
        String matricula = scanner.nextLine();

        // Buscar estudiante por matrícula
        Estudiante estudiante = estudiantes.stream()
                .filter(e -> e.getMatricula().equals(matricula))
                .findFirst()
                .orElse(null);

        if (estudiante == null) {
            System.out.println("No se encontró ningún estudiante con esa matrícula.");
            return;
        }

        // Mostrar datos del estudiante encontrado
        System.out.println("\nEstudiante encontrado:");
        System.out.println("Nombre: " + estudiante.getNombre() + " " + estudiante.getApellido());
        System.out.println("Matrícula: " + estudiante.getMatricula());

        List<Calificacion> calificaciones = schoolService.cargarCalificaciones();
        
        System.out.print("Nombre de la materia: ");
        String materia = scanner.nextLine();

        // Verificar si el estudiante ya tiene esta materia asociada
        boolean materiaExistente = calificaciones.stream()
                .anyMatch(c -> c.getIdEstudiante() == estudiante.getIdEstudiante() 
                        && c.getMateria().equalsIgnoreCase(materia));

        if (materiaExistente) {
            System.out.println("El estudiante ya tiene esta materia asociada.");
            return;
        }

        // Si la materia no existe, proceder a asociarla
        int nuevoId = calificaciones.isEmpty() ? 1 : calificaciones.get(calificaciones.size() - 1).getIdCalificacion() + 1;

        System.out.print("Nota inicial (0-10): ");
        double nota = scanner.nextDouble();
        
        // Validar que la nota esté en el rango correcto
        if (nota < 0 || nota > 10) {
            System.out.println("La nota debe estar entre 0 y 10.");
            return;
        }

        Calificacion nuevaCalificacion = new Calificacion(nuevoId, materia, nota, estudiante.getIdEstudiante());
        calificaciones.add(nuevaCalificacion);
        schoolService.guardarCalificaciones(calificaciones);

        System.out.println("Materia asociada exitosamente.");
    }

    private static void consultarAsistencias() {
        System.out.println("\n=== CONSULTAR ASISTENCIAS ===");
        
        List<Estudiante> estudiantes = schoolService.cargarEstudiantes();
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }

        System.out.print("Ingrese la matrícula del estudiante: ");
        String matricula = scanner.nextLine();

        // Buscar estudiante por matrícula
        Estudiante estudiante = estudiantes.stream()
                .filter(e -> e.getMatricula().equals(matricula))
                .findFirst()
                .orElse(null);

        if (estudiante == null) {
            System.out.println("No se encontró ningún estudiante con esa matrícula.");
            return;
        }

        // Mostrar datos del estudiante encontrado
        System.out.println("\nEstudiante encontrado:");
        System.out.println("Nombre: " + estudiante.getNombre() + " " + estudiante.getApellido());
        System.out.println("Matrícula: " + estudiante.getMatricula());

        // Buscar todas las asistencias del estudiante
        List<Asistencia> asistencias = schoolService.cargarAsistencias();
        List<Asistencia> asistenciasEstudiante = asistencias.stream()
                .filter(a -> a.getIdEstudiante() == estudiante.getIdEstudiante())
                .toList();

        if (asistenciasEstudiante.isEmpty()) {
            System.out.println("\nEl estudiante no tiene asistencias registradas.");
            return;
        }

        // Mostrar todas las asistencias
        System.out.println("\nHistorial de asistencias:");
        System.out.println("Fecha\t\tEstado\t\tJustificada");
        System.out.println("----------------------------------------");
        for (Asistencia asistencia : asistenciasEstudiante) {
            System.out.printf("%s\t%s\t\t%s%n", 
                asistencia.getFecha(), 
                asistencia.getEstado(),
                asistencia.isJustificada() ? "Sí" : "No"
            );
        }

        // Calcular estadísticas
        long presentes = asistenciasEstudiante.stream()
                .filter(a -> a.getEstado().equals("Presente"))
                .count();
        long ausentes = asistenciasEstudiante.size() - presentes;
        double porcentajeAsistencia = (double) presentes / asistenciasEstudiante.size() * 100;

        System.out.println("\nResumen:");
        System.out.printf("Total de clases: %d%n", asistenciasEstudiante.size());
        System.out.printf("Presentes: %d%n", presentes);
        System.out.printf("Ausentes: %d%n", ausentes);
        System.out.printf("Porcentaje de asistencia: %.2f%%%n", porcentajeAsistencia);
    }
}
