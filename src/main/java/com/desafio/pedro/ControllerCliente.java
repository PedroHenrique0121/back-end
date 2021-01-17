package com.desafio.pedro;

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



@RestController
@ComponentScan()

public class ControllerCliente {

	
	private ClienteRepository cli;

	public ControllerCliente( ClienteRepository cli) {
		
		this.cli = cli;
	}
/*
	@RequestMapping(method = RequestMethod.POST, path = "/parametros/{id_cliente}/{id_pedido}")
	public PedidoProduto pegarParametros(@RequestBody PedidoProduto produtos, @PathVariable("id_cliente") int id_cliente, @PathVariable("id_pedido") int id_pedido) {
		     PedidoProduto pedprod = new PedidoProduto();
		     Pedido pedido = new Pedido();
		     pedido.setId_pedido(id_pedido);
		    
		     Cliente cliente= new Cliente();
		     cliente.setId_cliente(id_cliente);
		     pedido.setCliente(cliente);
             pedprod.setPedido(pedido);
             
             return pedprod;
             
	}
	
	 
	*/
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.GET, path = "/clientes")
	public List<Cliente> buscarClientes() {
		List<Cliente> lista = cli.findAll();
		
		return  lista;
		
		
	}
	
	@CrossOrigin(origins = "http://localhost:3001")
	@RequestMapping(method = RequestMethod.GET, path = "/listar")
	public List<Cliente> buscar() {
		List<Cliente> lista = cli.findAll();
		
		return  lista;
		
		
	}
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.POST, path = "/cadastrar")
	public ResponseEntity<Cliente> CriarClientes( @RequestBody Cliente cliente) {
		Cliente clienteNovo = new Cliente();
		clienteNovo= cliente;

		cli.save(clienteNovo);
		return new ResponseEntity<Cliente>(clienteNovo, HttpStatus.CREATED);
	}

	
	
	
	@CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/deletarCliente/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
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
    public ResponseEntity<Object> Alterar(@PathVariable(value = "id") long id, @RequestBody Cliente cliente)
    {
        Optional<Cliente> clienteNovo = cli.findById((int) id);
        if(clienteNovo.isPresent()){
        	 
             Cliente c = clienteNovo.get();
             c.setNome_cliente(cliente.getNome_cliente());
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
         List<Cliente> lista = cli.findByNomeContaining(nome);
        
        
            return lista;
    }

}


