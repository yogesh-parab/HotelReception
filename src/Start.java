
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yogeshparab
 */
public class Start {
    public static void main(String[] args) throws SQLException {
        boolean h;
        
        
        Connect c=new Connect();
        h=c.check();
        if(h){
            LogIn ll=new LogIn();
            ll.setVisible(true);
             
        }
        else
        { SignUp ss=new SignUp();
            ss.setVisible(true);}
             
        
    }
    
}
