package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.DB;
import database.DbException;
import model.entities.Produto;

public class ProdutoDAO {
	
	public ArrayList<Produto> consultarTodosDadosProdutos() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Produto> arrayListProduto = new ArrayList<Produto>();
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM PRODUTO");
			while(rs.next()) {
				Produto produtoAddArrayList = new Produto();
				produtoAddArrayList.setId(rs.getInt("Id"));
				produtoAddArrayList.setNome(rs.getString("Nome"));
				produtoAddArrayList.setPreco(rs.getDouble("Preco"));
				produtoAddArrayList.setQuantidade(rs.getInt("Quantidade"));
				arrayListProduto.add(produtoAddArrayList);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			DB.closeConnection();
		}
		return arrayListProduto;
	}
	
	public void cadastrarProdutoIdAutoIncrementado() {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("INSERT INTO PRODUTO (Nome, Preco, Quantidade) VALUES (?, ?, ?)");
			st.setString(1, "Comida Nova");
			st.setDouble(2, 45.55);
			st.setInt(3, 10);
			st.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
	
	public void editarProdutoIdSelecionado() {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("UPDATE PRODUTO SET Quantidade = Quantidade + ? WHERE (Id = ?)");
			st.setInt(1, 5);
			st.setInt(2, 1);
			st.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluirProdutoIdSelecionado() {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("DELETE FROM PRODUTO WHERE (Nome=?)");
			st.setString(1, "Comida Nova");
			st.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
	
	public void testeCommit() {
		Connection conn = null;		
		Statement st = null;
		try {
			conn = DB.getConnection();
			conn.setAutoCommit(false); //todas confirmações vao ficar pendentes do programador
			st = conn.createStatement();
			
			st.executeUpdate("UPDATE PRODUTO SET Quantidade = Quantidade - 1 WHERE (Id=2)");
			int x = 1;
			if(x < 2)
				throw new SQLException("Fake error");
			st.executeUpdate("UPDATE PRODUTO SET Quantidade = Quantidade - 1 WHERE (Id=3)");
			
			conn.commit(); //se chegar até aqui as operações são confirmadas
		} catch(SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Transação não concluida! erro: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Erro ao dar o rollback! " + e1.getMessage()); 
			}
		}
	}

}