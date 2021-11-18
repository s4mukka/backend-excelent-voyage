package br.ufscar.dc.dsw.ExcellentVoyage.dao;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.ExcellentVoyage.domain.Foto;
import br.ufscar.dc.dsw.ExcellentVoyage.domain.PacoteTuristico;

@SuppressWarnings("unchecked")
public interface IFotoDAO extends CrudRepository<Foto, Long> {
    Foto save(Foto foto);

    void deleteAllByPacoteTuristico(PacoteTuristico pacoteTuristico);
}
