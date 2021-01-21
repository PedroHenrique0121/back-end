package com.desafio.pedro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.desafio.pedro.entity.Cliente;

@Repository
public interface ClienteRepository  extends JpaRepository<Cliente, Integer> {
    
  List<Cliente> findByNomeClienteContaining(String nome);
	
	

}
