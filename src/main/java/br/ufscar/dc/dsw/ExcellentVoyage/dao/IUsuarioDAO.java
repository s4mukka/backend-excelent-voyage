package br.ufscar.dc.dsw.ExcellentVoyage.dao;


import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.ExcellentVoyage.domain.Usuario;

@SuppressWarnings("unchecked")
public interface IUsuarioDAO extends CrudRepository<Usuario, Long> {

    public Usuario findByEmail(String email);
    
}
