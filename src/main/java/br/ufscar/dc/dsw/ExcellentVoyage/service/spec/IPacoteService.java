package br.ufscar.dc.dsw.ExcellentVoyage.service.spec;

import java.util.Date;
import java.util.List;

import br.ufscar.dc.dsw.ExcellentVoyage.domain.Agencia;
import br.ufscar.dc.dsw.ExcellentVoyage.domain.PacoteTuristico;

public interface IPacoteService {
  void salvar(PacoteTuristico pacoteTuristico);

  PacoteTuristico buscarPeloId(long id);

  List<PacoteTuristico> listarPelaAgencia(Agencia agencia, Boolean vigente);

  List<PacoteTuristico> buscarPorFiltro(String destino, String nomeAgencia, Date dataPartida);
}
