package br.ufscar.dc.dsw.ExcellentVoyage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.ExcellentVoyage.dao.IAgenciaDAO;
import br.ufscar.dc.dsw.ExcellentVoyage.dao.ICompraDAO;
import br.ufscar.dc.dsw.ExcellentVoyage.dao.IFotoDAO;
import br.ufscar.dc.dsw.ExcellentVoyage.dao.IPacoteTuristicoDAO;
import br.ufscar.dc.dsw.ExcellentVoyage.service.spec.IAgenciaService;
import br.ufscar.dc.dsw.ExcellentVoyage.domain.Agencia;
import br.ufscar.dc.dsw.ExcellentVoyage.domain.PacoteTuristico;

@Service
@Transactional(readOnly = false)
public class AgenciaService implements IAgenciaService{

    @Autowired
    IAgenciaDAO dao;

    @Autowired
    ICompraDAO compraDAO;

    @Autowired
    IFotoDAO fotoDAO;

    @Autowired
    IPacoteTuristicoDAO pacoteDAO;

    @Transactional(readOnly = true)
    public Agencia buscarPorId(Long id){
        return dao.findById(id.longValue());
    }

    @Transactional(readOnly = true)
    public List<Agencia> buscarTodos(){
        return dao.findAll();
    }

    public void salvar(Agencia agencia) {
        dao.save(agencia);
    }

    public void excluir(Long id){
        Agencia agencia = this.buscarPorId(id);

        for (PacoteTuristico pacote : pacoteDAO.findAllByAgencia(agencia)) {
            compraDAO.deleteAllByPacoteTuristico(pacote);
            fotoDAO.deleteAllByPacoteTuristico(pacote);
        }

        pacoteDAO.deleteAllByAgencia(agencia);

        dao.deleteById(id);
    }
}
