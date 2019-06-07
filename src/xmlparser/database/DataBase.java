package xmlparser.database;

import org.apache.log4j.Logger;
import validator.XsdValidator;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


public class DataBase {


    public Connection connect(){
        Logger logger= Logger.getLogger(DataBase.class);

        Connection connection = null;
        Properties properties = new Properties();


        try {
            logger.info("database connection start");
            properties.load(new FileReader("db.properties"));
            Class.forName(properties.getProperty("jdbc.driver"));
           String url = properties.getProperty("jdbc.url");
           String username=properties.getProperty("jdbc.username");
           String password = properties.getProperty("jdbc.password");

           connection = DriverManager.getConnection(url,username,password);
           connection.setAutoCommit(false);
            System.out.println("coonection yarandi");

        } catch (IOException e) {
            logger.error(e);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return  connection;
    }




        public static  void close(ResultSet rs , PreparedStatement ps, Connection connection){

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }    }



