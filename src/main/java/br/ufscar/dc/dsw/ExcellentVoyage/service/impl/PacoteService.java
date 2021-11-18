package br.ufscar.dc.dsw.ExcellentVoyage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.ExcellentVoyage.dao.IFotoDAO;
import br.ufscar.dc.dsw.ExcellentVoyage.dao.IPacoteTuristicoDAO;
import br.ufscar.dc.dsw.ExcellentVoyage.domain.Agencia;
import br.ufscar.dc.dsw.ExcellentVoyage.domain.Foto;
import br.ufscar.dc.dsw.ExcellentVoyage.domain.PacoteTuristico;
import br.ufscar.dc.dsw.ExcellentVoyage.service.spec.IPacoteService;

@Service
@Transactional(readOnly = false)
public class PacoteService implements IPacoteService {
  @Autowired
  IPacoteTuristicoDAO pacoteDAO;

  @Autowired
  IFotoDAO fotoDAO;

  @Override
  public void salvar(PacoteTuristico pacoteTuristico) {
    PacoteTuristico p = pacoteDAO.save(pacoteTuristico);

    for (Foto foto : pacoteTuristico.getFotos()) {
      foto.setPacoteTuristico(p);
    }

    for (Foto foto : pacoteTuristico.getFotos()) {
      fotoDAO.save(foto);
    }
  }

  public PacoteTuristico buscarPeloId(long id) {
    return pacoteDAO.findById(id);
  }

  public List<PacoteTuristico> listarPelaAgencia(Agencia agencia, Boolean vigente) {
    if (vigente)
      return pacoteDAO.findAllByAgenciaAndDataPartidaGreaterThan(agencia, new Date());

    return pacoteDAO.findAllByAgencia(agencia);
  }

  public List<PacoteTuristico> buscarPorFiltro(String destino, String nomeAgencia, Date dataPartida) {
    List<PacoteTuristico> listaPacoteTodos = pacoteDAO.findAll();

    if ((destino == null || destino.isEmpty()) && (nomeAgencia == null || nomeAgencia.isEmpty()) && dataPartida == null) {
      return pacoteDAO.findAll();
    }

    List<PacoteTuristico> listaPacotePeloDestino = null;
    List<PacoteTuristico> listaPacotePelaAgencia = null;
    List<PacoteTuristico> listaPacotePelaDataPartida = null;

    List<PacoteTuristico> listaPacotes = new ArrayList<PacoteTuristico>();

    if (destino != null && !destino.isEmpty()) {
      listaPacotePeloDestino = pacoteDAO.findAllByDestinoCidadeOrDestinoEstadoOrDestinoPaisContains(destino, destino, destino);
    }

    if (nomeAgencia != null && !nomeAgencia.isEmpty()) {
      listaPacotePelaAgencia = pacoteDAO.findAllByAgencia_NomeContains(nomeAgencia);
    }

    if (dataPartida != null) {
      listaPacotePelaDataPartida = pacoteDAO.findAllByDataPartida(dataPartida);
    }

    for (PacoteTuristico pacote : listaPacoteTodos) {
      if (
        (listaPacotePeloDestino == null || listaPacotePeloDestino.contains(pacote)) &&
        (listaPacotePelaAgencia == null || listaPacotePelaAgencia.contains(pacote)) &&
        (listaPacotePelaDataPartida == null || listaPacotePelaDataPartida.contains(pacote))) {
        listaPacotes.add(pacote);
      }
    }

    return listaPacotes;
  }
}
