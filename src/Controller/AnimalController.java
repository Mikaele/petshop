/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Animal;
import View.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AnimalController {

public void inserirAnimal(Animal a) throws SQLException {
try {
    
    Util util= new Util();
    Connection conexao = util.conecta();  
    String sql = "INSERT INTO Animal (Dono_ID,nome, cor, tamanho, raca, nascimento) VALUES (?,?, ?, ?, ?, ?)";
    PreparedStatement statement= conexao.prepareStatement(sql);
        statement.setInt(1, a.getDonoId());
        statement.setString(2, a.getNome());
        statement.setString(3, a.getCor());
        statement.setString(4, a.getTamanho ());
        statement.setString(5, a.getRaca());
        statement.setString(6, a.getNascimento());
        System.out.println (statement);
    int rowsInserted = statement.executeUpdate(); 
    if (rowsInserted > 0){
    System.out.println ("Novo animal inserido com sucesso");

    }
        statement.close();
        conexao.close();
} catch (SQLException e){
    
    System.out.println(e.getMessage());
    }
}

public void selectAnimal()throws SQLException {
    
            String sql = "SELECT * FROM animal";
            Util util= new Util();
            Connection conexao = util.conecta();

          Statement statement = conexao.createStatement();
          ResultSet result = statement.executeQuery(sql);
            
            int count = 0;
            while (result.next()){
                
                String nome = result.getString("nome");
                String cor = result.getString("cor");
                String tamanho = result.getString("tamanho");
                String raca = result.getString ("raca");
                String nascimento = result.getString("nascimento");
                
                String output = "Animal #%d: %s - %s - %s - %s - %s";
                System.out.println(String.format(output, ++count, nome, cor, tamanho, raca, nascimento));
 
            statement.close();
                conexao.close();
        }
    }
}
