import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConexionDB {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Establecer la conexión
            connection = ConexionDB.getConnection();
            if (connection != null) {
                System.out.println("Conexión exitosa a la base de datos.");

                // Crear un Statement para ejecutar la consulta
                statement = connection.createStatement();
                String query = "SELECT * FROM Estudiante";
                resultSet = statement.executeQuery(query);

                // Mostrar la lista de estudiantes
                System.out.println("Lista de Estudiantes:");
                while (resultSet.next()) {
                    int id = resultSet.getInt("id_estudiante");
                    String nombre = resultSet.getString("nombre");
                    String apellido = resultSet.getString("apellido");
                    String matricula = resultSet.getString("matricula");
                    String contacto = resultSet.getString("contacto");

                    System.out.printf("ID: %d, Nombre: %s, Apellido: %s, Matrícula: %s, Contacto: %s%n",
                            id, nombre, apellido, matricula, contacto);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }
    }
}
