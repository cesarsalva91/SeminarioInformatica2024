import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    // Datos de conexión
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/classroommanagerdb";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "WUppa2-.";
    
    // Método para establecer la conexión
    public static Connection obtenerConexion() {
        Connection conexion = null;
        
        try {
            // Registrar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establecer la conexión
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("Conexión exitosa a la base de datos");
            
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el driver: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
        
        return conexion;
    }
    
    // Método para cerrar la conexión
    public static void cerrarConexion(Connection conexion) {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión cerrada exitosamente");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    public static String getCONTRASEÑA() {
        return CONTRASENA;
    }
}
