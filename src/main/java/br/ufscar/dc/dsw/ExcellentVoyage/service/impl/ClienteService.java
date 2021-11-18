package br.ufscar.dc.dsw.ExcellentVoyage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.ExcellentVoyage.dao.IClienteDAO;
import br.ufscar.dc.dsw.ExcellentVoyage.dao.ICompraDAO;
import br.ufscar.dc.dsw.ExcellentVoyage.domain.Cliente;
import br.ufscar.dc.dsw.ExcellentVoyage.service.spec.IClienteService;

@Service
@Transactional(readOnly = false)
public class ClienteService implements IClienteService {
    @Autowired
    IClienteDAO dao;

    @Autowired
    ICompraDAO compraDAO;

    @Transactional(readOnly = true)
    public Cliente buscarPorId(Long id){
        return dao.findById(id.longValue());
    }

    @Transactional(readOnly = true)
    public List<Cliente> buscarTodos(){
        return dao.findAll();
    }

    public void salvar(Cliente cliente) {
        dao.save(cliente);
    }

    public void excluir(Long id){
        compraDAO.deleteAllByCliente(this.buscarPorId(id));
        dao.deleteById(id);
    }
}
