

public class JsonAlumno {
    public static void main(String[] args) {
        // Definimos una cadena JSON de ejemplo que representa datos de un estudiante
        
        String jsonString = "{\"id\":1,\"nombre\":\"Juan\",\"apellido\":\"Pérez\",\"matricula\":\"M001\"}";

      
        jsonString = jsonString.substring(1, jsonString.length() - 1);
        
        String[] pairs = jsonString.split(",");
        
        // Declaramos variables para almacenar los datos extraídos
        // Estas variables guardarán los valores una vez procesado el JSON
        int id = 0;
        String nombre = "";
        String apellido = "";
        String matricula = "";
        
        // Iteramos sobre cada par clave-valor
        for (String pair : pairs) {
            // Separamos cada par en clave y valor usando : como separador
            // Ejemplo: ["id", "1"]
            String[] keyValue = pair.split(":");
            
            // Eliminamos las comillas dobles de la clave y el valor
            // replace("\"", "") reemplaza todas las comillas dobles con nada
            String key = keyValue[0].replace("\"", "");    // Obtenemos la clave (ej: id)
            String value = keyValue[1].replace("\"", "");  // Obtenemos el valor (ej: 1)
            
            // Asignamos el valor a la variable correspondiente según la clave
            switch (key) {
                case "id" -> id = Integer.parseInt(value);         // Convertimos el valor a entero
                case "nombre" -> nombre = value;                   // Asignamos directamente el string
                case "apellido" -> apellido = value;              // Asignamos directamente el string
                case "matricula" -> matricula = value;            // Asignamos directamente el string
            }
        }

        // Imprimimos los resultados para verificar que todo funcionó correctamente
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("Matrícula: " + matricula);
    }
}
