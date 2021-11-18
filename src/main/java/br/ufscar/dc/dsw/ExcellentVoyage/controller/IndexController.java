package br.ufscar.dc.dsw.ExcellentVoyage.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufscar.dc.dsw.ExcellentVoyage.domain.PacoteTuristico;
import br.ufscar.dc.dsw.ExcellentVoyage.service.spec.IPacoteService;

@Controller
@RequestMapping("/")
public class IndexController {
  @Autowired
  IPacoteService pacoteService;

  @GetMapping("/")
  public String index(
    @RequestParam(name = "destino", required = false) String destino,
    @RequestParam(name = "nomeAgencia", required = false) String nomeAgencia,
    @RequestParam(name = "dataPartida", required = false) String data,
    Model model) throws ParseException {

    Date dataPartida = null;
    if (data != null && !data.isEmpty()) {
      dataPartida = new SimpleDateFormat("yyyy-MM-dd").parse(data);
    }

    List<PacoteTuristico> listaPacotes = pacoteService.buscarPorFiltro(destino, nomeAgencia, dataPartida);

    model.addAttribute("listaPacotes", listaPacotes);

    model.addAttribute("destino", destino);
    model.addAttribute("nomeAgencia", nomeAgencia);
    model.addAttribute("dataPartida", data);

    return "index";
  }
}
