package application;

import model.dao.FactoryDAO;
import model.dao.ProdutoDAO;
import model.entities.Categoria;
import model.entities.Produto;

public class Main {
	
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
		//testeCommit();
		Categoria categoria = new Categoria(1, "Manga");
		Produto produto = new Produto(1, "Naruto", 34.90, 5, categoria);
		
		ProdutoDAO produtoDAO = FactoryDAO.criarProdutoDAO();
		System.out.println(produto);
	}

	/*public static void listarProdutosRecuperadosBanco() { //ok
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
	}*/
}