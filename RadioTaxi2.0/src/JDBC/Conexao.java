package JDBC;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Conexao {
    
    public static String host;
    public static String user;
    public static String pass;
    public static boolean connected;
    
    private static Connection connection;
   
    public Conexao(String host, String user, String pass) {
        Conexao.setConexao(host, user, pass);
        Conexao.connected = false;
    }
    
    public static void setConexao(String host, String user, String pass){
        Conexao.host = host;
        Conexao.user = user;
        Conexao.pass = pass;
    }
    
    public static Connection getConnection() {        
        if (connection==null){
            synchronized (Conexao.class){
                if (connection==null)
                    try {   Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
                            connection = DriverManager.getConnection("jdbc:oracle:thin:@"+ host + ":1521:XE",user, pass);
                            connected = true;

                        } catch( SQLException e ) {
                            System.out.println(e.getMessage());
                        } catch ( ClassNotFoundException e ) {
                            System.out.println(e.getMessage());
                        } catch ( InstantiationException e ) {
                            System.out.println(e.getMessage());
                        } catch ( IllegalAccessException e ) {
                            System.out.println(e.getMessage());
                        }
            }
        }
        return connection;
    }
}




 
    