package com.desafio.pedro.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.pedro.entity.Cliente;
import com.desafio.pedro.repository.ClienteRepository;



@RestController
@ComponentScan()

public class ClienteController {

	
	private ClienteRepository cli;

	public ClienteController( ClienteRepository cli) {
		
		this.cli = cli;
	}
	
	
	 
	
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.GET, path = "/clientes")
	public List<Cliente> listar() {
		List<Cliente> lista = cli.findAll();
		
		return  lista;
		
		
	}
	
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.POST, path = "/cadastrar")
	public ResponseEntity<Cliente> criar( @RequestBody Cliente cliente) {
		Cliente clienteNovo ;
		clienteNovo= cliente;

		cli.save(clienteNovo);
		return new ResponseEntity<Cliente>(clienteNovo, HttpStatus.CREATED);
	}

	
	
	
	@CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/deletarCliente/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> excluir(@PathVariable(value = "id") long id)
    {
        Optional<Cliente> cliente = cli.findById((int) id);
        if(cliente.isPresent()){
            cli.delete(cliente.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/alterarCliente/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> alterar(@PathVariable(value = "id") long id, @RequestBody Cliente cliente)
    {
        Optional<Cliente> clienteNovo = cli.findById((int) id);
        if(clienteNovo.isPresent()){
        	 
             Cliente c = clienteNovo.get();
             c.setNomeCliente(cliente.getNomeCliente());
             cli.save(c);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/buscarPorNome/{nome}", method = RequestMethod.GET)
    public  List<Cliente> buscarPorNome(@PathVariable(value = "nome") String nome)
    {
         List<Cliente> lista = cli.findByNomeClienteContaining(nome);
        
        
            return lista;
    }

}


