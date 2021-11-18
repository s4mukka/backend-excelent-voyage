package br.ufscar.dc.dsw.ExcellentVoyage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.ExcellentVoyage.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.ExcellentVoyage.domain.Usuario;
import br.ufscar.dc.dsw.ExcellentVoyage.service.spec.IUsuarioService;

@Service
@Transactional(readOnly = false)
public class UsuarioService implements IUsuarioService{

    @Autowired
    IUsuarioDAO dao;
    
    @Transactional(readOnly = true)
    public Usuario buscarPorEmail(String email) {
        return dao.findByEmail(email);
    }
    
}
