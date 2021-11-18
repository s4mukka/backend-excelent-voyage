package br.ufscar.dc.dsw.ExcellentVoyage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.ExcellentVoyage.dao.ICompraDAO;
import br.ufscar.dc.dsw.ExcellentVoyage.domain.Cliente;
import br.ufscar.dc.dsw.ExcellentVoyage.domain.Compra;
import br.ufscar.dc.dsw.ExcellentVoyage.domain.PacoteTuristico;
import br.ufscar.dc.dsw.ExcellentVoyage.service.spec.ICompraService;

@Service
@Transactional(readOnly = false)
public class CompraService implements ICompraService {
  @Autowired
  ICompraDAO dao;

  public void salvar(Compra compra) {
    dao.save(compra);
  }

  public Compra buscarPeloClienteEPeloPacoteTuristico (Cliente cliente, PacoteTuristico pacoteTuristico) {
    return dao.findByClienteAndPacoteTuristico(cliente, pacoteTuristico);
  }

  public List<Compra> buscarPeloCliente(Cliente cliente) {
    return dao.findByCliente(cliente);
  }
}
