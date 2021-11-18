package br.ufscar.dc.dsw.ExcellentVoyage.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.ExcellentVoyage.domain.Agencia;

@SuppressWarnings("unchecked")
public interface IAgenciaDAO extends CrudRepository<Agencia, Long>{

	Agencia findById(long id);

	List<Agencia> findAll();
	
	Agencia save(Agencia agencia);

	void deleteById(Long id);
}