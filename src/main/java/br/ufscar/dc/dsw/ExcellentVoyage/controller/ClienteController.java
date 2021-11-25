package br.ufscar.dc.dsw.ExcellentVoyage.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufscar.dc.dsw.ExcellentVoyage.domain.Cliente;
import br.ufscar.dc.dsw.ExcellentVoyage.service.spec.IClienteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
@RequestMapping("/clientes")
public class ClienteController {
  @Autowired
	private IClienteService service;

  @Autowired
	private BCryptPasswordEncoder encoder;

  private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}

  private void parse(Cliente cliente, JSONObject json) throws ParseException {
		
		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				cliente.setId(((Integer) id).longValue());
			} else {
				cliente.setId((Long) id);
			}
		}

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		cliente.setNome((String) json.get("nome"));
		cliente.setEmail((String) json.get("email"));
    cliente.setSenha(encoder.encode((String) json.get("senha")));
    cliente.setCpf((String) json.get("cpf"));
    cliente.setTipo("ROLE_cliente");
    cliente.setTelefone((String) json.get("telefone"));
	  cliente.setSexo((String) json.get("sexo"));
    cliente.setDataNascimento(sdf.parse((String) json.get("dataNascimento")));
	}

  @PostMapping("")
  @ResponseBody
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

  @GetMapping("")
  public ResponseEntity<List<Cliente>> index() {
    return ResponseEntity.ok(service.buscarTodos());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Cliente> show(@PathVariable("id") Long id) {
    Cliente cliente = service.buscarPorId(id);
    if (cliente == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(cliente);
  }

  @PutMapping("/{id}")
  @ResponseBody
  public ResponseEntity<Cliente> update(@PathVariable("id") long id, @RequestBody JSONObject json) {
    try {
			if (isJSONValid(json.toString())) {
				Cliente cliente = service.buscarPorId(id);
        if (cliente == null) {
          return ResponseEntity.notFound().build();
        } else {
          parse(cliente, json);
          service.salvar(cliente);
          return ResponseEntity.ok(cliente);
        }
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
  }

  @DeleteMapping("/{id}")
  @ResponseBody
  public ResponseEntity<Boolean> delete(@PathVariable("id") long id) {
    Cliente cliente = service.buscarPorId(id);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		} else {
			service.excluir(id);
			return ResponseEntity.noContent().build();
		}
  }
}
