package br.com.sevensoft.utils.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sevensoft.utils.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Integer> {
	
	// Derived Query:
	
	// Esta nomenclaura pesquisa em qualquer parte do nome (Containing):
	public List<Contato> findByNomeContainingOrTelefoneContaining(String nome, String telefone);
	
	// Esta nomenclatura pesquisa pelo início do nome (StartingWith):  
	public List<Contato> findByNomeStartingWith(String prefix);

}
