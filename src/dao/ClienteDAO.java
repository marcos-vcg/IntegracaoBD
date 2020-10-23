package dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import model.Cliente;

public class ClienteDAO {
	private DataSource datasource;
	
	public ClienteDAO(DataSource datasource){
		this.datasource = datasource;
	}
	
	public ArrayList<Cliente> readAll(){
		try {
			String SQL = "SELECT * FROM cliente";
			java.sql.PreparedStatement ps = datasource.getConnection().prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			
			ArrayList<Cliente> lista = new ArrayList<Cliente>();
			
			while(rs.next()) {
				Cliente cli = new Cliente();
				cli.setId(rs.getInt("id"));
				cli.setNome(rs.getString("nome"));
				cli.setEmail(rs.getString("email"));
				cli.setTelefone(rs.getString("telefone"));
				lista.add(cli);
			}
			ps.close();
			return lista;
			
		} catch(SQLException ex) {
			System.err.println("Erro ao Recuperar " + ex.getMessage());
		} catch(Exception ex) {
			System.err.println("Erro Geral " + ex.getMessage());
		}
		return null;
	}
}
