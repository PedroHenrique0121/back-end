package com.desafio.pedro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.desafio.pedro.entity.Cliente;
import com.desafio.pedro.repository.ClienteRepository;

@Service
public class ClienteService {

	private ClienteRepository repositorio;

	public ClienteService(ClienteRepository repositorio) {
		this.repositorio = repositorio;
	}

	public ResponseEntity<Cliente> criar(Cliente cliente) {
		repositorio.save(cliente);
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(cliente);

	}

	public ResponseEntity<?> excluir(int id) {

		Optional<Cliente> cliente = repositorio.findById(id);
		if (cliente.isPresent()) {
			repositorio.delete(cliente.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	public ResponseEntity<?> alterar(int id, Cliente cliente) {

		Optional<Cliente> clienteNovo = repositorio.findById((int) id);
		if (clienteNovo.isPresent()) {

			Cliente c = clienteNovo.get();
			c.setNomeCliente(cliente.getNomeCliente());
			repositorio.save(c);
			return ResponseEntity.status(HttpStatus.OK.value()).body(c);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	public List<Cliente> listar() {
		return repositorio.findAll();
		
	}

	public ResponseEntity<?> buscarPorNome(String nome) {

		List<Cliente> lista = repositorio.findByNomeClienteContaining(nome);
		if (lista.size() != 0) {
			return ResponseEntity.status(HttpStatus.OK.value()).body(lista);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(lista);
		}
	}
}
