package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DbException;
import model.dao.FactoryDAO;
import model.dao.ProdutoDAO;
import model.entities.Categoria;
import model.entities.Produto;

public class ProdutoDaoJDBC implements ProdutoDAO {

	@Override
	public void cadastrarProdutoIdAutoincrementado(Produto produto) {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = FactoryDAO.getConnection();
			st = conn.prepareStatement("INSERTO INTO PRODUTO (Nome, Preco, Quantidade, CategoriaId) "
												   + "VALUES (?,?,?,?)");
			st.setString(1, produto.getNome());
			st.setDouble(1, produto.getPreco());
			st.setInt(3, produto.getQuantidade());
			st.setInt(4, produto.getCategoria().getId());
			st.executeUpdate();
		} catch(SQLException e) {
			throw new DbException("Erro ao inserir");
		} finally {
			fecharObjetosConnectionResultSetStatement(null, st);
		}
	}

	@Override
	public void editarProdutoIdSelecionado(Produto produto) {
		
	}

	@Override
	public void excluirProdutoIdSelecionado(Integer id) {
		Connection conn = null;
		PreparedStatement st = null;
		
		/*try {
			
		} catch(SQLException e) {
			throw new DbException("Erro ao excluir produto selecionado " + e.getMessage());
		} finally {
			
		} */
	}

	@Override
	public Produto listarUmProdutoIdSelecionado(Integer id) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Produto produtoRetornado = new Produto();
		
		try {
			conn = FactoryDAO.getConnection();
			st = conn.prepareStatement("SELECT PRODUTO.*, CATEGORIA.* "
									 + "FROM PRODUTO "
									 + "INNER JOIN CATEGORIA cat"
									 + "ON PRODUTO.CategoriaId = cat.Id"
									 + "WHERE PRODUTO.Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			while(rs.next()) {
				produtoRetornado.setId(rs.getInt(0));
				produtoRetornado.setNome(rs.getString(1));
				produtoRetornado.setPreco(rs.getDouble(2));
				produtoRetornado.setQuantidade(rs.getInt(3));
				produtoRetornado.setCategoria(new Categoria(rs.getInt(4), rs.getString(5)));
			}
			return produtoRetornado;
			
		} catch(SQLException e) {
			throw new DbException("Erro ao selecionar produto por ID selecionado " + e.getMessage());
		} finally {
			fecharObjetosConnectionResultSetStatement(rs, st);
		}
	}

	@Override
	public List<Produto> listarTodosProduto() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		List<Produto> listaProduto = new ArrayList<Produto>();
		
		try {
			conn = FactoryDAO.getConnection();
			st = conn.prepareStatement("SELECT * "
									+ "FROM PRODUTO "
									+ "INNER JOIN CATEGORIA cat "
									+ "ON PRODUTO.CategoriaId = cat.Id");
			rs = st.executeQuery(null);
			while(rs.next()) {
				Produto umProduto = new Produto();
				umProduto.setId(rs.getInt(0));
				umProduto.setNome(rs.getString(1));
				umProduto.setPreco(rs.getDouble(2));
				umProduto.setQuantidade(rs.getInt(3));
				umProduto.setCategoria(new Categoria(rs.getInt(4), rs.getString(5)));
				listaProduto.add(umProduto);
			}
			return listaProduto;
		} catch(SQLException e) {
			throw new DbException("Erro ao listar todos os produtos " + e.getMessage());
		} finally {
			fecharObjetosConnectionResultSetStatement(rs, st);
		}
	}
	
	public void fecharObjetosConnectionResultSetStatement(ResultSet rs, Statement st) {
		fecharObjetoConnection();
		fecharObjetoResultSet(rs);
		fecharObjetoStatement(st);
	}

	public void fecharObjetoConnection() {
		FactoryDAO.closeConnection();
	}
	
	public void fecharObjetoResultSet(ResultSet rs) {
		FactoryDAO.closeResultSet(rs);
	}
	
	public void fecharObjetoStatement(Statement st) {
		FactoryDAO.closeStatement(st);
	}
}