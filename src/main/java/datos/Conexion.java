package datos;

import java.sql.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {

    /* constante de conexión hacia la base de datos */
    private static final String JDBC_URL = "jdbc:mysql://localhost:3307/control_clientes?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "admin1994";

    private static BasicDataSource dataSource;

    // Método para recuperar la conexión hacia la base de datos
    public static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new BasicDataSource();
            dataSource.setUrl(JDBC_URL);
            dataSource.setUsername(JDBC_USER);
            dataSource.setPassword(JDBC_PASSWORD);
            // se define el tamaño inicial del pool de conexiones
            dataSource.setInitialSize(50);
        }

        return dataSource;
    }

    // Método para obtener una conexión hacia la base de datos
    public static Connection getConnection() throws SQLException {
        // se obtiene una conexión a travéz del pool de conexiones establecido en el método getDataSource
        return getDataSource().getConnection();
    }

    // Método para cerrar el objeto ResultSet 
    public static void close(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    // Método para cerrar el objeto PreparedStatement
    public static void close(PreparedStatement stmt) {
        try {
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    // Método para cerrar el objeto de conexion
    public static void close(Connection conn) {
        try {
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
