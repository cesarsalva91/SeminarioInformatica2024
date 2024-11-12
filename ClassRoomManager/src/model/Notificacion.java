import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Notificacion implements Serializable {

    // Atributos de la clase Notificacion.
    private int idNotificacion;    
    private String mensaje;        
    private String fechaEnvio;        
    private int idEstudiante;     

    // Constructor vacío que permite crear un objeto Notificacion sin inicializar sus atributos.
    public Notificacion() {}

    // Constructor con parámetros para inicializar todos los atributos de un objeto Notificacion.
    public Notificacion(int idNotificacion, String mensaje, String fechaEnvio, int idEstudiante) {
        this.idNotificacion = idNotificacion;
        this.mensaje = mensaje;
        this.fechaEnvio = fechaEnvio;
        this.idEstudiante = idEstudiante;
    }

    // Getters y Setters para acceder y modificar cada atributo de la clase.
    public int getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(int idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(String string) {
        this.fechaEnvio = string;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    // Método toString para representar un objeto Notificacion como una cadena de texto.
    @Override
    public String toString() {
        return "Notificacion{" +
                "idNotificacion=" + idNotificacion +
                ", mensaje='" + mensaje + '\'' +
                ", fechaEnvio=" + fechaEnvio +
                ", idEstudiante=" + idEstudiante +
                '}';
    }

    // Método para guardar la notificación en la base de datos
    public void guardar() {
        Connection conexion = null;
        PreparedStatement ps = null;

        try {
            conexion = ConexionDB.obtenerConexion();
            String sql = "INSERT INTO notificaciones (mensaje, fecha_envio, id_estudiante) VALUES (?, ?, ?)";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, this.mensaje);
            ps.setString(2, this.fechaEnvio);
            ps.setInt(3, this.idEstudiante);
            ps.executeUpdate();
            System.out.println("Notificación guardada exitosamente");
        } catch (SQLException e) {
            System.out.println("Error al guardar la notificación: " + e.getMessage());
        } finally {
            // Cerrar recursos
            ConexionDB.cerrarConexion(conexion);
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar PreparedStatement: " + e.getMessage());
                }
            }
        }
    }
}
