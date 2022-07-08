package application;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import DAO.ProdutoDAO;
import database.DB;
import database.DbException;
import modal.Produto;

public class Main {
	
	static ProdutoDAO produtoDAO = new ProdutoDAO();

	/* Transa��es
	 setAutoCommit(false) = cada opera��o n�o est� confirmada, s� finalizar at� eu confirmar todas opera��es
	 commit() = confirmar a transa��o
	 rollback() = desfazer o que foi feito at� ent�o
	 */
	
	public static void main(String[] args) {
		//listarProdutosRecuperadosBanco();
		//cadastrarProdutosBanco();
		//editarDadosProdutosBanco();
		//excluirDadosProdutosBanco();
		testeCommit();
	}

	public static void listarProdutosRecuperadosBanco() { //ok
		String lista = "";
		for(Produto p : produtoDAO.consultarTodosDadosProdutos()) {
			lista += p.toString();
		}
		JOptionPane.showMessageDialog(null, lista, "Listar Produtos", 2);
	}

	private static void cadastrarProdutosBanco() { //ok
		produtoDAO.cadastrarProdutoIdAutoIncrementado();
	}
	
	private static void editarDadosProdutosBanco() { //ok
		produtoDAO.editarProdutoIdSelecionado();
	}
	
	private static void excluirDadosProdutosBanco() { //ok
		produtoDAO.excluirProdutoIdSelecionado();
	}
	
	private static void testeCommit() {
		produtoDAO.testeCommit();
	}
}