package model.dao;

import java.util.List;

import model.entities.Categoria;

public interface CategoriaDAO {
	
	void cadastrarCategoriaIdAutoincrementado(Categoria categoria);
	void editarCategoriaIdSelecionado(Categoria categoria);
	void excluirCategoriaIdSelecionado(Integer id);
	Categoria listarUmaCategoriaIdSelecionado(Integer id);
	List<Categoria> listarTodasCategoria();

}