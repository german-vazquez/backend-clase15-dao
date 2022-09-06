package presencial.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BD {
    private static final String DRIVER="org.h2.Driver";
    private static final String URL="jdbc:h2:~/clase15";
    private static final String USER="sa";
    private static final String PASS="";

    private static final String SQL_CREATE_TABLE="drop table if exists medicamentos; create table medicamentos (id int auto_increment primary key, nombre varchar(100), laboratorio varchar(100), cantidad int, precio numeric(10,2))";

    // método estático para levantar la conexión con la db
    public static Connection getConnection() throws Exception {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL,USER,PASS);
    }

    // crear una sentencia para la creación la de db  (tabla)
    public static void crearBD() throws Exception{
        Connection connection=getConnection();
        Statement statement=connection.createStatement();
        statement.execute(SQL_CREATE_TABLE);
    }

}
