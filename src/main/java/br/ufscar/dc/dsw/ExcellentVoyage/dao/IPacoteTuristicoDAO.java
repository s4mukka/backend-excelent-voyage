package br.ufscar.dc.dsw.ExcellentVoyage.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.ExcellentVoyage.domain.Agencia;
import br.ufscar.dc.dsw.ExcellentVoyage.domain.PacoteTuristico;

@SuppressWarnings("unchecked")
public interface IPacoteTuristicoDAO extends CrudRepository<PacoteTuristico, Long>{

	PacoteTuristico findById(long id);

	List<PacoteTuristico> findAll();

	List<PacoteTuristico> findAllByAgencia(Agencia agencia);

	List<PacoteTuristico> findAllByAgenciaAndDataPartidaGreaterThan(Agencia agencia, Date currentDate);

	List<PacoteTuristico> findAllByDestinoCidadeOrDestinoEstadoOrDestinoPaisContains(String cidade, String estado, String pais);

	List<PacoteTuristico> findAllByDataPartida(Date dataPartida);

	List<PacoteTuristico> findAllByAgencia_NomeContains(String nomeAgencia);

	void deleteAllByAgencia(Agencia agencia);

	PacoteTuristico save(PacoteTuristico compra);
}
