package br.ufscar.dc.dsw.ExcellentVoyage.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

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
import org.springframework.web.bind.annotation.RequestPart;
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

    @Autowired
	  ServletContext context;

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
        List<PacoteTuristico> listaPacotes= service.listarPelaAgencia(agencia, false);

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

    @PostMapping(path = "/agencias/{id}", consumes = { "multipart/form-data" })
    public ResponseEntity<PacoteTuristico> create(
        @PathVariable("id") Long id,
        @RequestPart("descricao") MultipartFile descricao,
        @RequestPart("fotos") MultipartFile[] fotos,
        @RequestParam("destinoCidade") String destinoCidade,
        @RequestParam("destinoEstado") String destinoEstado,
        @RequestParam("destinoPais") String destinoPais,
        @RequestParam("dataPartida") String dataPartida,
        @RequestParam("duracaoDias") Integer duracaoDias,
        @RequestParam("valor") BigDecimal valor
    ) {
        try {
          PacoteTuristico pacoteTuristico = new PacoteTuristico();
          pacoteTuristico.setAgencia(agenciaService.buscarPorId(id));
          DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
          pacoteTuristico.setDataPartida(sdf.parse(dataPartida));
          pacoteTuristico.setDestinoCidade(destinoCidade);
          pacoteTuristico.setDestinoEstado(destinoEstado);
          pacoteTuristico.setDestinoPais(destinoPais);
          pacoteTuristico.setDuracaoDias(duracaoDias);
          pacoteTuristico.setValor(valor);
          pacoteTuristico.setDescricao(addFile(descricao));
  
          List<Foto> listaFotos = new ArrayList<Foto>();
  
          for(int i = 0; i < fotos.length; i++) {
            Foto foto = new Foto(addFile(fotos[i]));
            listaFotos.add(foto);
          }
  
          pacoteTuristico.setFotos(listaFotos);
          pacoteTuristico.setQtdFoto(listaFotos.size());
  
          service.salvar(pacoteTuristico);
  
          return ResponseEntity.ok(pacoteTuristico);
        } catch (Exception e) {
          return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    private String addFile(MultipartFile file) throws IOException {
      String fileName = file.getOriginalFilename().split("\\.")[0] + "-" + UUID.randomUUID().toString() + "." + file.getOriginalFilename().split("\\.")[1];
  
      String uploadPath = context.getRealPath("") + File.separator + "upload";
      File uploadDir = new File(uploadPath);
  
      if (!uploadDir.exists()) {
        uploadDir.mkdir();
      }
  
      file.transferTo(new File(uploadDir, fileName));
  
      return File.separator + "upload" + File.separator + fileName;
    }
}
