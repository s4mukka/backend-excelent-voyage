package br.ufscar.dc.dsw.ExcellentVoyage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.ufscar.dc.dsw.ExcellentVoyage.domain.PacoteTuristico;
import br.ufscar.dc.dsw.ExcellentVoyage.domain.Agencia;
import br.ufscar.dc.dsw.ExcellentVoyage.domain.Cliente;
import br.ufscar.dc.dsw.ExcellentVoyage.domain.Compra;
import br.ufscar.dc.dsw.ExcellentVoyage.service.spec.IAgenciaService;
import br.ufscar.dc.dsw.ExcellentVoyage.service.spec.IClienteService;
import br.ufscar.dc.dsw.ExcellentVoyage.service.spec.ICompraService;
import br.ufscar.dc.dsw.ExcellentVoyage.service.spec.IPacoteService;
import br.ufscar.dc.dsw.ExcellentVoyage.domain.Foto;

@CrossOrigin
@RestController
@RequestMapping("/pacotes")
public class PacoteTuristicoController {

    @Autowired
    private IPacoteService service;

    @Autowired
    private ICompraService compraService;

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IAgenciaService agenciaService;

    @GetMapping("")
    public ResponseEntity<List<PacoteTuristico>> lista() {
        List<PacoteTuristico> lista = service.buscarTodos();

        return ResponseEntity.ok(lista);
    }

    
    @GetMapping("/clientes/{id}")
    public ResponseEntity<List<PacoteTuristico>> showByCliente(@PathVariable("id") Long id) {
        Cliente cliente = clienteService.buscarPorId(id);

        if(cliente == null){
            return ResponseEntity.notFound().build();
        }

        List<Compra> listCompras = compraService.buscarPeloCliente(cliente);
        List<PacoteTuristico> listaPacotes = new ArrayList<PacoteTuristico>();
        
        for (Compra compra : listCompras){
            listaPacotes.add(compra.getPacoteTuristico());
        }

        return ResponseEntity.ok(listaPacotes);
    }
    

    @GetMapping("/agencias/{id}")
    public ResponseEntity<List<PacoteTuristico>> showByAgencia(@PathVariable("id") Long id) {
        Agencia agencia = agenciaService.buscarPorId(id);
        List<PacoteTuristico> listaPacotes= service.listarPelaAgencia(agencia, true);

        if (agencia == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listaPacotes);
    }

    @GetMapping("/destinos/{nome}")
    public ResponseEntity<List<PacoteTuristico>> showByDestino(@PathVariable("nome") String nome) {
        List<PacoteTuristico> listaPacotes = service.buscarPorDestino(nome);

        return ResponseEntity.ok(listaPacotes); 

    }

    @PostMapping("/agencias/{id}")
    public ResponseEntity<PacoteTuristico> create(
        @RequestBody JSONObject json, 
        @PathVariable("id") Long id, 
        @RequestParam("descriptionFile") MultipartFile descricao,
        @RequestParam("fotos") MultipartFile fotos){

            System.out.println(descricao);

            return null;
        }


/*
    public ResponseEntity<Cliente> create(@RequestBody JSONObject json) {
        try {
                if (isJSONValid(json.toString())) {
                    Cliente cliente = new Cliente();
                    parse(cliente, json);
            System.out.println("dataNascimento: " + cliente.getDataNascimento());
                    service.salvar(cliente);
                    return ResponseEntity.ok(cliente);
                } else {
                    return ResponseEntity.badRequest().body(null);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
            }
      }
      */


    
}
