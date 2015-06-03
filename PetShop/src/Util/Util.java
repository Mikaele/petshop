/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Mikaele
 */
//Classe para criar tabelas (subistituir nosso terminal)
public class Util {
    
     public static Connection conecta() throws SQLException{
                      Connection conexao = null;

        String url = "jdbc:mysql://localhost/petshop";  //Nome da base de dados
        String user = "root"; //nome do usuário do MySQL
        String password = "123456"; //senha do MySQL
        try{
            conexao = DriverManager.getConnection(url, user, password);
}catch(SQLException sqlex){
System.out.println("Erro na conexão "+ sqlex);
}
        return conexao;
    }

    public void desconecta(Connection conexao){
    try{
    conexao.close();
    }catch(SQLException sqlex){
    System.out.println("Erro na conexão "+ sqlex);
    }
    }
    public static void criaTabela(String nomeTabela,String atributos) throws SQLException {
            Statement statement = null;
            Connection conexao = null;

        try {
            conexao = conecta();
            statement = conexao.createStatement();

            String createTableSQL = "CREATE TABLE "+nomeTabela+"("+atributos+");";

        	System.out.println(createTableSQL);
                        // executa o comando de criação
        	statement.execute(createTableSQL);

        	System.out.println("Tabela \"nomeTabela\" foi criada com sucesso!");

        } catch (SQLException e) {

        	System.out.println(e.getMessage());

        } finally {   // sempre feche o statement a conexão com banco

        	if (statement != null) {
        		statement.close();
        	}

        	if (conexao != null) {
        		conexao.close();
        	}

        }

    }
    
    public static void criaBanco(String banco) throws SQLException{
        
        String sql="CREATE DATABASE "+banco;
        Connection conxao= conecta();
        Statement stmt = conxao.createStatement();
        stmt.execute(sql);
    
    }
    
    public static void rodaScript(String sql) throws SQLException{
        Connection conxao= conecta();
        Statement stmt = conxao.createStatement();
        stmt.execute(sql);
    }
    
public static void main(String[] args) throws SQLException {
             //cria o banco petshop
            // criaBanco("PetShop");
             //criar as tabelas
            criaTabela("Dono", "`ID` INT NULL AUTO_INCREMENT,"+
                                            "`CPF` VARCHAR(11) NULL,"+
                                           " `Endereco` VARCHAR(45) NULL,"+
                                            "`Telefone` VARCHAR(45) NULL,"+
                                            "`Nome` VARCHAR(45) NULL,"+
                                            "`Nascimento` VARCHAR(45) NULL,"+
                                           " PRIMARY KEY (`ID`)");
             criaTabela("Animal", " `idAnimal` INT NULL AUTO_INCREMENT,"+
  "`Raca` VARCHAR(45) NULL,"+
  "`Cor` VARCHAR(45) NULL,"+
  "`Tamanho` VARCHAR(20) NULL,"+
  "`Obs` VARCHAR(45) NULL,"+
  "`Nome` VARCHAR(45) NULL,"+
  "`Nascimento` VARCHAR(45) NULL,"+
  "`Dono_ID` INT NOT NULL,"+
  "PRIMARY KEY (`idAnimal`, `Dono_ID`),"+
  "INDEX `fk_Animal_Dono1_idx` (`Dono_ID` ASC),"+
  "CONSTRAINT `fk_Animal_Dono1`"+
    "FOREIGN KEY (`Dono_ID`)"+
    "REFERENCES `mydb`.`Dono` (`ID`)"+
    "N DELETE NO ACTION"+
    "ON UPDATE NO ACTION");
             
             criaTabela("Servico", "`ID` INT NULL AUTO_INCREMENT,"+
"  `Nome` VARCHAR(45) NULL,"+
 " `Descricao` VARCHAR(45) NULL,"+
 " PRIMARY KEY (`ID`)");
             
             criaTabela("Agenda", "`Horario` INT NULL,"+
  "`ID` INT NULL AUTO_INCREMENT,"+
  "`Animal_idAnimal` INT NOT NULL,"+
  "`Servico_ID` INT NOT NULL,"+
  "PRIMARY KEY (`ID`, `Animal_idAnimal`, `Servico_ID`),"+
  "INDEX `fk_Agenda_Animal_idx` (`Animal_idAnimal` ASC),"+
  "INDEX `fk_Agenda_Servico1_idx` (`Servico_ID` ASC),"+
  "CONSTRAINT `fk_Agenda_Animal`"+
  "FOREIGN KEY (`Animal_idAnimal`)"+
  " REFERENCES `mydb`.`Animal` (`idAnimal`)"+
   "ON DELETE NO ACTION"+
   "ON UPDATE NO ACTION,"+
  "CONSTRAINT `fk_Agenda_Servico1`"+
    "FOREIGN KEY (`Servico_ID`)"+
    "REFERENCES `mydb`.`Servico` (`ID`)"+
    "ON DELETE NO ACTION"+
    "ON UPDATE NO ACTION");
             
         
             
             

        
    }
}

