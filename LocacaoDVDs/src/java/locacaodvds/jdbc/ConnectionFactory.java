
package locacaodvds.jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectionFactory {
    
    public static Connection getConnection() throws SQLException{
    
        return DriverManager.getConnection(
                "jdbc:mariadb://localhost/locacao_dvds",
                "root",
                ""
        );
        
    
    }
    
}
