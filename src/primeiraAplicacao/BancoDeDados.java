package primeiraAplicacao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class BancoDeDados {
	private Connection connection = null;
	private java.sql.Statement statement = null;
	private ResultSet resultset = null;
	
	public void connect(){
		String servidor = "jdbc:mysql://localhost:3306/lista_de_compras";
		String usuario = "root";
		String senha = "Crazy2603";
		String driver = "com.mysql.jdbc.Driver";
		try{
			Class.forName(driver);
			this.connection = DriverManager.getConnection(servidor,usuario,senha);
			this.statement = this.connection.createStatement();
		}catch (Exception e){
			System.out.println("Erro: " + e.getMessage());
		}
	}
	public boolean isConnected(){
		if(this.connection != null){
			return true;
		}else{
			return false;
		}
	}
	
	public void listItems(){
		try{
			String query = "SELECT * FROM item ORDER BY quantidade";
			this.resultset = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			while(this.resultset.next()){
				System.out.println("Nome: "+this.resultset.getString("nome") + " - Quantidade: "+this.resultset.getString("quantidade"));
			}	
		}catch (Exception e){
			System.out.println("Erro: 4" + e.getMessage());
		}
	}
	
	public void insertItem(String nome, int quantidade){
		try{
			String query = "INSERT INTO item(nome,quantidade) VALUES ('"+nome+"',"+quantidade+");";
			this.statement.executeUpdate(query);
		}catch (Exception e){
			System.out.println("Erro: 3"+e.getMessage());
		}
	}
	public void insertItemsimult(final String nome,final int quantidade,final String nome2,final int quantidade2){
		insertItem(nome,quantidade);
    	insertItem(nome2, quantidade2);
	}
	
	public void removeItem(String nome){
		try{
			String query = "DELETE from item WHERE nome = '" + nome +"';";
			this.statement.executeUpdate(query);
		}catch (Exception e){
			System.out.println("Erro: 2"+e.getMessage());
		}
	}
	public void removeItemsimult(final String nome,final String nome2){
		removeItem(nome);
    	removeItem(nome2);
	}
	
	
	public void disconnect(){
		try{
			this.connection.close();
		}catch (Exception e){
			System.out.println("Erro: 1"+e.getMessage());
		}
	}
}
