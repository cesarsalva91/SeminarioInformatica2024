import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/classroommanagerdb";
    private static final String USER = "root";
    private static final String PASSWORD = "WUppa2-.";

    // establecer la conexión
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // cargar el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el controlador JDBC.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

    // obtener la conexión
    public static Connection obtenerConexion() {
        return getConnection(); 
    }

    // cerrar la conexión
    public static void cerrarConexion(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
