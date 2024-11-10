import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestConexion {
    public static void main(String[] args) {
        // Probar la conexión
        Connection conexion = ConexionDB.obtenerConexion();
        
        if (conexion != null) {
            System.out.println("La conexión se estableció correctamente");
            
            try {
                Statement stmt = conexion.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM estudiantes");
                
                // Mostrar los datos de la tabla estudiantes
                while (rs.next()) {
                    System.out.println(
                        "ID: " + rs.getInt("id_estudiante") + 
                        ", Nombre: " + rs.getString("nombre") +
                        ", Apellido: " + rs.getString("apellido") +
                        ", Matrícula: " + rs.getString("matricula") +
                        ", Contacto: " + rs.getString("contacto")
                    );
                }
                
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                System.out.println("Error al consultar la tabla estudiantes: " + e.getMessage());
            } finally {
                ConexionDB.cerrarConexion(conexion);
            }
        } else {
            System.out.println("No se pudo establecer la conexión");
        }
    }
}