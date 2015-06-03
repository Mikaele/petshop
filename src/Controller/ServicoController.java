/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Servico;
import View.Util;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author info206
 */
public class ServicoController {
    
        public void inserirServico(Servico s ){
            
        }
        
            /*  Essa função tem por finalidade popular o Combobox*/
    public Vector  getNomes(){
                   Vector s =new Vector();
        try {
            Util util= new Util();
            Connection conexao = util.conecta();
            String sql= "Select * from Servico";
                Statement statement = conexao.createStatement();
          ResultSet result = statement.executeQuery(sql);
          while (result.next()){               
               s.add(result.getString("Nome"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DonoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    
        public int  getIdByNome(String nome){
        int id=-1;
            try {
            Util util= new Util();
            Connection conexao = util.conecta();
            String sql= "Select ID from Servico where Nome like '"+nome+"'";
                Statement statement = conexao.createStatement();
          ResultSet result = statement.executeQuery(sql);
           while (result.next()){               
               id=result.getInt("ID");
            }
       } catch (SQLException ex) {
            Logger.getLogger(DonoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }        

    
}
