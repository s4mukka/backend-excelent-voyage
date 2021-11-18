package br.ufscar.dc.dsw.ExcellentVoyage.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.ExcellentVoyage.domain.Cliente;
import br.ufscar.dc.dsw.ExcellentVoyage.domain.Compra;
import br.ufscar.dc.dsw.ExcellentVoyage.domain.PacoteTuristico;

public interface ICompraService {
  void salvar(Compra compra);

  Compra buscarPeloClienteEPeloPacoteTuristico(Cliente cliente, PacoteTuristico pacoteTuristico);

  List<Compra> buscarPeloCliente(Cliente cliente);
}
