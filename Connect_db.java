

import java.sql.Connection;
import java.sql.DriverManager;



public class Connect_db {
    
    
      static Connection con=null;
    public static Connection getConnection()
    {
        if (con != null) return con;
        // get db, user, pass from settings file
        String db="Patient";
        String user="postgres";
        String pass="root";        
        
        return getConnection(db, user, pass);
    }

    private static Connection getConnection(String db_name,String user_name,String password)
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            con=DriverManager.getConnection("jdbc:postgresql://localhost:5433/"+db_name,user_name,password);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return con;        
    }
    
    
}
