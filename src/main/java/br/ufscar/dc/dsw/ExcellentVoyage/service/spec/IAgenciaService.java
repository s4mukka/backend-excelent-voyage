package br.ufscar.dc.dsw.ExcellentVoyage.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.ExcellentVoyage.domain.Agencia;

public interface IAgenciaService {

    Agencia buscarPorId(Long id);

    List<Agencia> buscarTodos();

    void salvar(Agencia agencia);

    void excluir(Long id);
    
}