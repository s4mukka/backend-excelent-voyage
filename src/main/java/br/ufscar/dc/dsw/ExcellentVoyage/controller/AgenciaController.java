package br.ufscar.dc.dsw.ExcellentVoyage.controller;

import java.io.IOException;
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

import br.ufscar.dc.dsw.ExcellentVoyage.domain.Agencia;
import br.ufscar.dc.dsw.ExcellentVoyage.service.spec.IAgenciaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@CrossOrigin
@RestController
@RequestMapping("/agencias")
public class AgenciaController {
  @Autowired
	private IAgenciaService service;

  @Autowired
	private BCryptPasswordEncoder encoder;

  private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}

  private void parse(Agencia agencia, JSONObject json) {
		
		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				agencia.setId(((Integer) id).longValue());
			} else {
				agencia.setId((Long) id);
			}
		}

		agencia.setNome((String) json.get("nome"));
		agencia.setEmail((String) json.get("email"));
    agencia.setSenha(encoder.encode((String) json.get("senha")));
    agencia.setCnpj((String) json.get("cnpj"));
    agencia.setTipo("ROLE_agencia");
    agencia.setDescricao((String) json.get("descricao"));
	}

  @PostMapping("")
  @ResponseBody
  public ResponseEntity<Agencia> create(@RequestBody JSONObject json) {
    try {
			if (isJSONValid(json.toString())) {
				Agencia agencia = new Agencia();
				parse(agencia, json);
				service.salvar(agencia);
				return ResponseEntity.ok(agencia);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
  }

  @GetMapping("")
  public ResponseEntity<List<Agencia>> index() {
    return ResponseEntity.ok(service.buscarTodos());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Agencia> show(@PathVariable("id") Long id) {
    Agencia agencia = service.buscarPorId(id);
    if (agencia == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(agencia);
  }

  @PutMapping("/{id}")
  @ResponseBody
  public ResponseEntity<Agencia> update(@PathVariable("id") long id, @RequestBody JSONObject json) {
    try {
			if (isJSONValid(json.toString())) {
				Agencia agencia = service.buscarPorId(id);
        if (agencia == null) {
          return ResponseEntity.notFound().build();
        } else {
          parse(agencia, json);
          service.salvar(agencia);
          return ResponseEntity.ok(agencia);
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
    Agencia agencia = service.buscarPorId(id);
		if (agencia == null) {
			return ResponseEntity.notFound().build();
		} else {
			service.excluir(id);
			return ResponseEntity.noContent().build();
		}
  }
}
