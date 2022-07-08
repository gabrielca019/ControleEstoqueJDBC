package model.dao;

import java.util.List;

import model.entities.Produto;

public interface ProdutoDAO {
	
	void cadastrarProdutoIdAutoincrementado(Produto produto);
	void editarProdutoIdSelecionado(Produto produto);
	void excluirProdutoIdSelecionado(Integer id);
	Produto listarUmProdutoIdSelecionado(Integer id);
	List<Produto> listarTodosProduto();

}