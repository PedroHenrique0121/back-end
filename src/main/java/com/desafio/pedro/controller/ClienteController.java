package com.desafio.pedro.controller;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.pedro.entity.Cliente;
import com.desafio.pedro.repository.ClienteRepository;
import com.desafio.pedro.service.ClienteService;

@RestController
@ComponentScan()

public class ClienteController {

	
	private ClienteService servico;

	public ClienteController(ClienteService servico) {

		
		this.servico = servico;
	}

	
	@RequestMapping(method = RequestMethod.GET, path = "/clientes")
	public ResponseEntity<?> listar() {
		

		return servico.listar();

	}

	
	@RequestMapping(method = RequestMethod.POST, path = "/cadastrar")
	public ResponseEntity<?> criar(@RequestBody Cliente cliente) {

		return servico.criar(cliente);
	}

	
	@RequestMapping(value = "/deletarCliente/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> excluir(@PathVariable(value = "id") int id) {
		return servico.excluir(id);
	}

	
	@RequestMapping(value = "/alterarCliente/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> alterar(@PathVariable(value = "id") int id, @RequestBody Cliente cliente) {
		          
		return  servico.alterar(id, cliente);
	}

	
	@RequestMapping(value = "/buscarPorNome/{nome}", method = RequestMethod.GET)
	public ResponseEntity<?> buscarPorNome(@PathVariable(value = "nome") String nome) {
		

		return servico.buscarPorNome(nome);
	}

}
