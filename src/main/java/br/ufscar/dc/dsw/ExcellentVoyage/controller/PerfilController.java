package br.ufscar.dc.dsw.ExcellentVoyage.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufscar.dc.dsw.ExcellentVoyage.domain.Agencia;
import br.ufscar.dc.dsw.ExcellentVoyage.domain.PacoteTuristico;
import br.ufscar.dc.dsw.ExcellentVoyage.domain.Cliente;
import br.ufscar.dc.dsw.ExcellentVoyage.domain.Compra;
import br.ufscar.dc.dsw.ExcellentVoyage.service.spec.IPacoteService;
import br.ufscar.dc.dsw.ExcellentVoyage.service.spec.*;

@Controller
@RequestMapping("/perfil")
public class PerfilController {
  @Autowired
  IPacoteService pacoteService;

  @Autowired
  IUsuarioService usuarioService;

  @Autowired
  IClienteService clienteService;

  @Autowired
  ICompraService compraService;

  @Autowired
  IAgenciaService agenciaService;
  
  @GetMapping("")
  public String onLoad(@RequestParam(name = "vigente", required = false) String vigente, Authentication auth, Model model) {
      String role = auth.getAuthorities().toArray()[0].toString();

      switch (role) {
        case "ROLE_agencia":
          {
            if (vigente != null) {
              System.out.println("opa: " + vigente);
            }
            Agencia agencia = (Agencia) usuarioService.buscarPorEmail(auth.getName());
            List<PacoteTuristico> listaPacotes = pacoteService.listarPelaAgencia(agencia, Boolean.valueOf(vigente));
            model.addAttribute("listaPacotes", listaPacotes);
          }
          break;

        case "ROLE_cliente":
          {
            Cliente cliente = (Cliente) usuarioService.buscarPorEmail(auth.getName());
            List<Compra> compras = compraService.buscarPeloCliente(cliente);

            List<PacoteTuristico> listaPacotes = new ArrayList<PacoteTuristico>();

            for (Compra compra : compras) {
              listaPacotes.add(compra.getPacoteTuristico());
            }

            model.addAttribute("listaPacotes", listaPacotes);
          }
          break;
        
        case "ROLE_admin":
            List<Agencia> listaAgencia = agenciaService.buscarTodos();
            List<Cliente> listaCliente = clienteService.buscarTodos();
            model.addAttribute("listaAgencia", listaAgencia);
            model.addAttribute("listaCliente", listaCliente);
          break;
      
        default:
          break;
      }

      return "perfil";
  }
}
