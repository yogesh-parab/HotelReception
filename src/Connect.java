/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author yogeshparab
 */
public class Connect {
    
    Connection con;
    PreparedStatement pstmt;
    String query;
    
    Connect(){
        try {
Class.forName ("com.mysql.cj.jdbc.Driver");
String connectionUrl ="jdbc:mysql://localhost:3306/test?user=root&password=yogesh@123";

     con = DriverManager.getConnection (connectionUrl);
    }   catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{System.out.println("connected");} 
    }  
    void signUp(String[] array) throws SQLException{
       
        
        pstmt= con.prepareStatement("Insert into manager values (?,?,?,?,?)");
        pstmt.setString(1,array[0]);
        pstmt.setString(2,array[1]);
        pstmt.setString(3,array[2]);
        pstmt.setString(4,array[3]);
        pstmt.setString(5,array[4]);
        int a=pstmt.executeUpdate();
        
        int b=Integer.parseInt(array[4]);
        pstmt= con.prepareStatement("Insert into hotel values (?,?,?,?,?,?)");
        for (int i=1;i<=b;i++)
        {   pstmt.setString(1,Integer.toString(i));
            pstmt.setString(2,"true");
            pstmt.setString(3,"null");
            pstmt.setString(4,"null");
            pstmt.setString(5,"null");
            pstmt.setString(6,"null");
            pstmt.executeUpdate();
        }
        
        
    }
    
    boolean logIn(String pass) throws SQLException{
        pstmt=con.prepareStatement("select Pass from manager");
        pstmt.execute();
        ResultSet rs = pstmt.getResultSet();
        rs.next();
        String passw=rs.getString(1);
        if (passw.equals(pass))
            return true;
        else
            return false;   
        
    }
    boolean resetPassword(String ans) throws SQLException{
    pstmt=con.prepareStatement("select Sanswer from manager");
    pstmt.execute();
    ResultSet rs=pstmt.getResultSet();
    rs.next();
    String answer=rs.getString(1);
    if (answer.equals(ans))
        return true;
    else
        return false;
    
    
    }
    boolean resetPassword(char[] a) throws SQLException{
    String pass="";
        for (int i = 0; i < a.length; i++) 
            pass+=a[i];
        pstmt=con.prepareStatement("select Pass from manager");
        pstmt.execute();
        ResultSet rs = pstmt.getResultSet();
        rs.next();
        String passw=rs.getString(1);
        if (passw.equals(pass))
            return true;
        else
            return false;  
            
            
        }
    void changePass(String pass) throws SQLException{
        pstmt=con.prepareStatement("update manager set Pass=?");
        pstmt.setString(1, pass);
        pstmt.execute();
        
        

}
    boolean check() throws SQLException{
        pstmt=con.prepareStatement("select Mname from manager");
        pstmt.execute();
        ResultSet rs=pstmt.getResultSet();
        if(rs.next()==false)       
            return false;
        else
            return true;
        
    }
    String squestion() throws SQLException{
        pstmt=con.prepareStatement("select Squestion from manager");
        pstmt.execute();
        ResultSet rs=pstmt.getResultSet();
        rs.next();
        String aa=rs.getString(1);
        return aa;
                
    }
    boolean newCustomer(String[] array) throws SQLException{
        
        String aa=Integer.toString(Integer.parseInt(array[1])*500);
        
        pstmt=con.prepareStatement("update hotel set Availability=? ,NameC=?,Id=?,Bill=?,TimeP=? where RoomNo=?");
        pstmt.setString(1,"false");
        pstmt.setString(2,array[0]);
        pstmt.setString(3,array[2]);
        pstmt.setString(5,array[1]);
        pstmt.setString(4,(String)aa);
        pstmt.setString(6,array[3]);
        int c=pstmt.executeUpdate();
        if(c!=0)
            return true;
        else
            return false;
    }
    ArrayList<String> availability() throws SQLException{
    ArrayList<String> list=new ArrayList<>();//Creating arraylist    
    pstmt=con.prepareStatement("select RoomNo from hotel where Availability=?");
    pstmt.setString(1,"true");
    pstmt.execute();
    ResultSet rs=pstmt.getResultSet();
    while (rs.next()) {               // Position the cursor                 3 
             String empNo = rs.getString(1); 
             list.add(empNo);                            // Print the column value
}
    return list;
    } 
    boolean updateCost(String[] aa) throws SQLException{
        pstmt=con.prepareStatement("select Bill from hotel where RoomNo=?");
        pstmt.setString(1, aa[0]);
        pstmt.execute();
        ResultSet rs=pstmt.getResultSet();
        rs.next();
        int bill=Integer.parseInt(rs.getString(1));    
        bill+=Integer.parseInt(aa[1]);
        String bb=Integer.toString(bill);
        pstmt=con.prepareStatement("update hotel set bill=? where RoomNo=?");
        pstmt.setString(2, aa[0]);
        pstmt.setString(1,bb);
        int c=pstmt.executeUpdate();
        if (c!=0)
            return true;
        else
            return false;
    }
    String invoice(String room) throws SQLException{
        pstmt=con.prepareStatement("select bill from hotel where RoomNo=?");
        pstmt.setString(1,room);
        pstmt.execute();
        ResultSet rs=pstmt.getResultSet();
        rs.next();
        String bill=rs.getString(1);
        return bill;
 
    }
    boolean checkOut(String room) throws SQLException{
        pstmt=con.prepareStatement("update hotel set Availability=?,NameC=?,Id=?,Bill=?,timeP=? where RoomNo=?");
        pstmt.setString(1, "true");
        pstmt.setString(2, "null");
        pstmt.setString(3, "null");
        pstmt.setString(4, "null");
        pstmt.setString(5, "null");
        pstmt.setString(6, room);
        int h=pstmt.executeUpdate();
        if(h!=0)
            return true;
        else
            return false;
     
    }
    public static void main(String[] args) throws SQLException {
        Connect c=new Connect();
        String[] a={"3","240"};
        boolean t=c.checkOut("3");
        System.out.println(t);
}
}
