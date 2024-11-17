import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class Main {
    private static SchoolService schoolService = new SchoolService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== SISTEMA DE GESTIÓN ESCOLAR ===");
            System.out.println("1. Agregar estudiante");
            System.out.println("2. Registrar asistencia");
            System.out.println("3. Asociar materia a estudiante");
            System.out.println("4. Consultar asistencias");
            System.out.println("5. Consultar calificaciones");
            System.out.println("6. Agregar materia");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            
            String input = scanner.nextLine(); // Leer la entrada como String
            int opcion;

            try {
                opcion = Integer.parseInt(input); // Convertir a entero
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
                continue; // Volver al inicio del bucle
            }
            
            switch (opcion) {
                case 1:
                    agregarEstudiante();
                    break;
                case 2:
                    registrarAsistencia();
                    break;
                case 3:
                    asociarMateria();
                    break;
                case 4:
                    consultarAsistencias();
                    break;
                case 5:
                    consultarCalificaciones();
                    break;
                case 6:
                    agregarMateria();
                    break;
                case 7:
                    System.out.println("¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente nuevamente.");
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
        
        String contacto;
        boolean emailValido = false;
        do {
            System.out.print("Email de contacto: ");
            contacto = scanner.nextLine();
            
            if (contacto.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                emailValido = true;
            } else {
                System.out.println("Error: El formato del email no es válido. Por favor, intente nuevamente.");
            }
        } while (!emailValido);

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
                .filter(e -> String.valueOf(e.getMatricula()).equals(matricula))
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

        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();
        java.sql.Date fecha = java.sql.Date.valueOf(fechaActual); // Convertir a java.sql.Date
        
        System.out.println("\nEstado de asistencia:");
        System.out.println("1. Presente");
        System.out.println("2. Ausente");
        System.out.print("Seleccione: ");
        int estadoOpcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer después de leer un entero
        String estado = estadoOpcion == 1 ? "Presente" : "Ausente";

        // Solo preguntar por justificación si está ausente
        boolean justificada = false;
        if (estado.equals("Ausente")) {
            System.out.print("¿Está justificada? (s/n): ");
            justificada = scanner.nextLine().toLowerCase().startsWith("s");
        }

        Asistencia nuevaAsistencia = new Asistencia(nuevoId, fecha, estado, justificada, estudiante.getIdEstudiante());
        asistencias.add(nuevaAsistencia);
        schoolService.guardarAsistencias(asistencias);

        System.out.println("Asistencia registrada exitosamente.");
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
                .filter(e -> String.valueOf(e.getMatricula()).equals(matricula)) // Convertir matrícula a String
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
                        && String.valueOf(c.getMateria()).equalsIgnoreCase(materia)); // Corregido

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
           .filter(e -> String.valueOf(e.getMatricula()).equals(matricula)) // Convertir matrícula a String
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
                .collect(Collectors.toList());

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

    private static void consultarCalificaciones() {
        System.out.println("\n=== CONSULTAR CALIFICACIONES ===");
        
        List<Estudiante> estudiantes = schoolService.cargarEstudiantes();
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }

        System.out.print("Ingrese la matrícula del estudiante: ");
        String matricula = scanner.nextLine();

        // Buscar estudiante por matrícula
        Estudiante estudiante = estudiantes.stream()
                .filter(e -> String.valueOf(e.getMatricula()).equals(matricula)) // Convertir matrícula a String
                .findFirst()
                .orElse(null);

        if (estudiante == null) {
            System.out.println("No se encontró ningún estudiante con esa matrícula.");
            return;
        }

        // Mostrar calificaciones del estudiante
        schoolService.mostrarCalificaciones(estudiante.getIdEstudiante());
    }

    private static void agregarMateria() {
        System.out.println("\n=== AGREGAR MATERIA ===");
        
        System.out.print("Nombre de la materia: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Descripción de la materia: ");
        String descripcion = scanner.nextLine();
        
        // Crear nueva materia
        Materia nuevaMateria = new Materia(nombre, descripcion); // Eliminar 'codigo' si no es necesario
        nuevaMateria.guardar(); // Guardar en la base de datos

        System.out.println("Materia agregada exitosamente.");
    }
}