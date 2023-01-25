package br.com.sevensoft.utils.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sevensoft.utils.model.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Integer>{
	
	public List<Receita> findByTitulo(String titulo);
}
