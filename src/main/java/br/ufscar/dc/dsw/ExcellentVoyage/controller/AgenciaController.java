package br.ufscar.dc.dsw.ExcellentVoyage.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufscar.dc.dsw.ExcellentVoyage.domain.Agencia;
import br.ufscar.dc.dsw.ExcellentVoyage.service.spec.IAgenciaService;

@Controller
@RequestMapping("/agencia")
public class AgenciaController {

    @Autowired
    IAgenciaService service;

    @Autowired
	private BCryptPasswordEncoder encoder;

    @PostMapping("/salvar")
    public String salvar(@Valid Agencia agencia, BindingResult result, @RequestParam("confirmarSenha") String confirmarSenha, Model model) {
        Boolean error = false;
        if (!agencia.getSenha().equals(confirmarSenha)) {
            model.addAttribute("confiramarSenhaErro", "As senhas estão diferentes");
            error = true;
        }

        if (result.hasErrors() || error) {
            model.addAttribute("result", result);
            return "cadastroAgencia";
        }

        agencia.setSenha(encoder.encode(agencia.getSenha()));
        agencia.setTipo("ROLE_agencia");

        service.salvar(agencia);

        return "index";
    }

    @GetMapping("/delete/{id}")
    public String excluir(@PathVariable("id") long id){
        service.excluir(id);

        return "redirect:/perfil";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model){
        model.addAttribute("agencia", service.buscarPorId(id));

        return "admin/formularioEdicaoAgencia";
    }

    @PostMapping("/editar")
    public String editar(@Valid Agencia agencia, BindingResult result, @RequestParam("confirmarSenha") String confirmarSenha, Model model){
        Boolean error = false;
        if (!agencia.getSenha().equals(confirmarSenha)) {
            model.addAttribute("confiramarSenhaErro", "As senhas estão diferentes");
            error = true;
        }

        if (result.hasErrors() || error) {
            model.addAttribute("result", result);
            return "admin/formularioEdicaoAgencia";
        }

        agencia.setSenha(encoder.encode(agencia.getSenha()));
        service.salvar(agencia);
        return "redirect:/perfil";
    }
}
