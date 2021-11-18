package br.ufscar.dc.dsw.ExcellentVoyage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufscar.dc.dsw.ExcellentVoyage.domain.*;

@Controller
@RequestMapping("/cadastro")
public class CadastroController {
    
    @GetMapping("")
    public String cadastro(){
        return "cadastro";
    }

    @GetMapping("/agencia")
    public String formsAgencia(Agencia agencia){
        return "cadastroAgencia";
    }

    @GetMapping("/cliente")
    public String formsCliente(Cliente cliente){
        return "cadastroCliente";
    }


}
