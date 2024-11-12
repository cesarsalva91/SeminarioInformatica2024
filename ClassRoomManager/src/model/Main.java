import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Mostrar estudiantes");
            System.out.println("2. Agregar estudiante");
            System.out.println("3. Tomar asistencia");
            System.out.println("4. Mostrar número de inasistencias");
            System.out.println("5. Mostrar calificaciones");
            System.out.println("0. Salir");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    mostrarEstudiantes(); // Llamar al método para mostrar estudiantes
                    break;
                case 2:
                    agregarEstudiante(scanner); // Llamar al método para agregar estudiante
                    break;
                case 3:
                    tomarAsistencia(scanner); // Llamar al método para tomar asistencia
                    break;
                case 4:
                    mostrarNumeroInasistencias(scanner); // Llamar al método para mostrar número de inasistencias
                    break;
                case 5:
                    mostrarCalificaciones(scanner); // Llamar al método para mostrar calificaciones
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

            // Preguntar si desea volver al menú principal
            if (opcion != 0) {
                System.out.print("¿Desea volver al menú principal? (s/n): ");
                String respuesta = scanner.nextLine();
                if (respuesta.equalsIgnoreCase("n")) {
                    opcion = 0; // Salir del bucle
                }
            }
        } while (opcion != 0);

        scanner.close();
    }

    public static void mostrarEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>(); // Lista para almacenar estudiantes
        Connection connection = null;
        try {
            connection = ConexionDB.obtenerConexion(); // Establecer conexión a la base de datos
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM estudiantes"); // Consulta para obtener estudiantes

            // Encabezados de la tabla
            System.out.printf("%-10s %-20s %-20s %-15s %-15s%n", "ID", "Nombre", "Apellido", "Matrícula", "Contacto");
            System.out.println("---------------------------------------------------------------");

            while (resultSet.next()) {
                // Crear un objeto Estudiante a partir de los datos de la base de datos
                Estudiante estudiante = new Estudiante(
                    resultSet.getInt("id_estudiante"),
                    resultSet.getString("nombre"),
                    resultSet.getString("apellido"),
                    resultSet.getString("matricula"),
                    resultSet.getString("contacto")
                );
                estudiantes.add(estudiante); // Agregar el estudiante a la lista

                // Mostrar cada estudiante en formato tabular
                System.out.printf("%-10d %-20s %-20s %-15s %-15s%n", 
                    estudiante.getIdEstudiante(), 
                    estudiante.getNombre(), 
                    estudiante.getApellido(), 
                    estudiante.getMatricula(), 
                    estudiante.getContacto());
            }
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de excepciones
        } finally {
            if (connection != null) {
                ConexionDB.cerrarConexion(connection); // Cerrar conexión
            }
        }
    }

    public static void agregarEstudiante(Scanner scanner) {
        System.out.println("Ingrese los datos del estudiante:");

        // Eliminar la entrada para el ID Estudiante y la matrícula
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();

        // Puedes dejar la matrícula como un campo opcional o eliminarlo
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();

        System.out.print("Contacto: ");
        String contacto = scanner.nextLine();

        // Crear un nuevo objeto Estudiante y guardarlo en la base de datos
        Estudiante nuevoEstudiante = new Estudiante(0, nombre, apellido, matricula, contacto); // ID se puede manejar automáticamente
        nuevoEstudiante.guardar(); // Guardar el estudiante en la base de datos

        System.out.println("Estudiante agregado exitosamente.");
    }

    public static void tomarAsistencia(Scanner scanner) {
        System.out.print("Ingrese el ID del estudiante: ");
        int idEstudiante = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        LocalDate fecha = LocalDate.now();
        System.out.println("Fecha de asistencia: " + fecha);

        System.out.print("Ingrese el estado (s para Presente, n para Ausente): ");
        String estadoInput = scanner.nextLine();
        String estado = estadoInput.equalsIgnoreCase("s") ? "Presente" : "Ausente";

        boolean justificada = false;
        if (estado.equalsIgnoreCase("Ausente")) {
            System.out.print("¿Justificada? (s para sí, n para no): ");
            String justificadaInput = scanner.nextLine();
            justificada = justificadaInput.equalsIgnoreCase("s");
        }

        Asistencia asistencia = new Asistencia(idEstudiante, fecha, estado, justificada);
        if (asistencia.guardar()) {
            System.out.println("Asistencia registrada exitosamente.");
        } else {
            System.out.println("Error al registrar la asistencia.");
        }
    }

    public static void mostrarNumeroInasistencias(Scanner scanner) {
        System.out.print("Ingrese el ID del estudiante: ");
        int idEstudiante = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        int inasistencias = Asistencia.contarInasistenciasPorEstudiante(idEstudiante); // Llamar al método para contar inasistencias
        int inasistenciasJustificadas = Asistencia.contarInasistenciasJustificadasPorEstudiante(idEstudiante); // Llamar al método para contar inasistencias justificadas

        System.out.println("Número de inasistencias del estudiante con ID " + idEstudiante + ": " + inasistencias);
        System.out.println("Número de inasistencias justificadas del estudiante con ID " + idEstudiante + ": " + inasistenciasJustificadas);
    }

    public static void mostrarCalificaciones(Scanner scanner) {
        System.out.print("Ingrese el ID del estudiante: ");
        int idEstudiante = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        List<Calificacion> calificaciones = Calificacion.obtenerCalificacionesPorEstudiante(idEstudiante); // Obtener calificaciones

        // Encabezados de la tabla
        System.out.printf("%-10s %-20s %-15s%n", "ID", "Materia", "Calificación");
        System.out.println("---------------------------------------------------------------");

        if (calificaciones.isEmpty()) {
            System.out.println("No se encontraron calificaciones para el estudiante con ID " + idEstudiante + ".");
        } else {
            for (Calificacion calificacion : calificaciones) {
                // Obtener el nombre de la materia usando el id_materia
                String nombreMateria = obtenerNombreMateria(calificacion.getIdMateria()); // Método que debes implementar
                System.out.printf("%-10d %-20s %-15.2f%n", 
                    calificacion.getIdCalificacion(), 
                    nombreMateria, 
                    calificacion.getNota());
            }
        }
        
        // Agregar un pie de página para mayor claridad
        System.out.println("---------------------------------------------------------------");
        System.out.println("Fin de las calificaciones.");
    }

    // Método para obtener el nombre de la materia
    public static String obtenerNombreMateria(int idMateria) {
        String nombreMateria = "";
        String sql = "SELECT nombre FROM materia WHERE id_materia = ?"; // Asegúrate de que la tabla de materias se llame 'materia'
        
        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idMateria);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                nombreMateria = rs.getString("nombre"); // Cambia 'nombre' al nombre real de la columna
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el nombre de la materia: " + e.getMessage());
        }
        return nombreMateria;
    }
}

class ConexionDB {
    public static Connection obtenerConexion() {
        try {
            // Cambia la URL, usuario y contraseña según tu configuración de base de datos
            String url = "jdbc:mysql://localhost:3306/tu_base_de_datos";
            String user = "tu_usuario";
            String password = "tu_contraseña";
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void cerrarConexion(Connection connection) {
        try {
            if (connection != null) {
                connection.close(); // Cerrar conexión
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
