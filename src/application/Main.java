package application;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import DAO.ProdutoDAO;
import database.DB;
import database.DbException;
import modal.Produto;

public class Main {
	
	static ProdutoDAO produtoDAO = new ProdutoDAO();

	/* Transações
	 setAutoCommit(false) = cada operação não está confirmada, só finalizar até eu confirmar todas operações
	 commit() = confirmar a transação
	 rollback() = desfazer o que foi feito até então
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