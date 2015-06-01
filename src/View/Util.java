/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//classe para criar tabela

public class Util {
    public static Connection conecta() throws SQLException {
    Connection conexao= null;
    String url = "jdbc:mysql://localhost/PetShop"; //Nome da base de dados
    String user = "root"; //nome do usuario do Mysql
    String password = "123456"; // senha do Mysql
    try{
        conexao = DriverManager.getConnection(url, user, password);
    }   catch(SQLException sqlex){
        System.out.println ("Erro na conexão "+ sqlex);
    }
    return conexao;
    }  
    
    public static void criaTabela(String nomeTabela, String atributos) throws SQLException {
    Statement statement = null; 
    Connection conexao = null;
    try {
    conexao=conecta();
        statement = conexao.createStatement();
    String createTableSQL = "CREATE TABLE "+nomeTabela+"("+atributos+");";
    System.out.println(createTableSQL);
        statement.execute(createTableSQL); //executa o comando criação
    System.out.println("Tabela \"nomeTabela\"foi criado com sucesso!");
    } catch (SQLException e) {
    System.out.println(e.getMessage());
    } finally { //sempre fecha o statement a conexao com o banco 
    if (statement!= null) {
    }        
    if (conexao!= null){
        conexao.close();
                }
       }
}   
    public static void criaBanco(String banco) throws SQLException{
    String sql="CREATE DATABASE"+banco;
    Connection conexao= conecta();
    Statement stmt= conexao.createStatement();
    stmt.execute(sql);
    }    
    public static void main (String[] args) throws SQLException{
        criaBanco("PetShop");
        criaTabela("Dono ","ID INT NULL AUTO _INCREMENT,"+
                    "Cpf varchar(11) null,"+
                    "Endereco varchar(45) null"+
                    "Telefone  varchar(45) null"+
                    "Nome varchar (45) null"+
                    "Nascimento varchar(45) null"+
                    "primary key(ID)");                
    
    criaTabela("Animal","IDAnimal INT NULL AUTO_INCREMENT,"+
                "Raca VARCHAR(45) NULL,"+       
                "Cor VARCHAR(45) NULL,"+
                "Tamanho VARCHAR(20) NULL,"+
                "Obs VARCHAR(45) NULL,"+
                "Nome VARCHAR(45) NULL,"+
                "Nascimento VARCHAR(45) NULL,"+
                "Dono_ID INT NOT NULL,"+
                "PRIMARY KEY (idAnimal, Dono_ID),"+
                "INDEX `fk_Animal_Dono1_idx` (`Dono_ID` ASC),"+
                "CONSTRAINT `fk_Animal_Dono1,"+
                "FOREIGN KEY (`Dono_ID`),"+
                "REFERENCES `mydb`.`Dono` (`ID`)"+
                " ON DELETE NO ACTI");
    
    criaTabela("Servico","ID INT NULL AUTO_INCREMENT,"+
               "Nome' VARCHAR(45) NULL,"+
               "Descricao' VARCHAR(45) NULL,"+
               "PRIMARY KEY ('ID')");
    
    criaTabela ("Agenda","Horario' INT NULL,"+
            "ID` INT NULL AUTO_INCREMENT,"+
            "Animal_idAnimal' INT NOT NULL,"+
            "Servico_ID' INT NOT NULL,"+
            "PRIMARY KEY (`ID`, `Animal_idAnimal`, `Servico_ID`),"+
            "INDEX `fk_Agenda_Animal_idx` (`Animal_idAnimal` ASC),"+
            "INDEX `fk_Agenda_Servico1_idx` (`Servico_ID` ASC),"+
            "CONSTRAINT `fk_Agenda_Animal`,"+
            "FOREIGN KEY (`Animal_idAnimal`),"+
            "REFERENCES `mydb`.`Animal` (`idAnimal`),"+
            "ON DELETE NO ACTION,"+
            "ON UPDATE NO ACTION");
    }              
}