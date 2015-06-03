/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Dono;
import View.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author info206
 */
public class DonoController {
    
    public  void inserir( Dono d) throws SQLException{
        try{
          Util util= new Util();
    Connection conexao = util.conecta();  
    String sql = "INSERT INTO Dono (Nome, CPF, Endereco, Telefone, Nascimento) VALUES (?, ?, ?, ?, ?)";
    PreparedStatement statement= conexao.prepareStatement(sql);
        statement.setString(1, d.getNome());
        statement.setString(2, d.getCpf());
        statement.setString(3, d.getEndereco());
        statement.setString(4, d.getTelefone());
        statement.setString(5, d.getTelefone());
         int rowsInserted = statement.executeUpdate(); 
    if (rowsInserted > 0){
    System.out.println ("Novo Dono inserido com sucesso");
}
        statement.close();
        conexao.close();
} catch (SQLException e){
    
    System.out.println(e.getMessage());
    }
    }
    /*  Essa função tem por finalidade popular o Combobox*/
    public Vector  getNomes(){
                   Vector s =new Vector();
        try {
            Util util= new Util();
            Connection conexao = util.conecta();
            String sql= "Select * from Dono";
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
            String sql= "Select ID from Dono where Nome like '"+nome+"'";
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
