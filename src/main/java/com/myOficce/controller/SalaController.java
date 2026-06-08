package com.myOficce.controller;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.myOficce.dto.SalaDTO;
import com.myOficce.service.SalaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/salas")
public class SalaController {
    
    @Autowired
    private SalaService salaService;
    
    @PostMapping
public ResponseEntity<SalaDTO> cadastrar(@RequestBody SalaDTO dto) {
    // dto.imagem virá como String em Base64
    // Processa normalmente
    SalaDTO cadastrada = salaService.Cadastrar(dto);
    
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(cadastrada.getId_sala())
        .toUri();

    return ResponseEntity.created(location).body(cadastrada);
}
   
     @GetMapping
   public ResponseEntity<List<SalaDTO>> listar() {

      return ResponseEntity.ok(salaService.Listar());
   }

   @GetMapping("/{id}")
   public ResponseEntity<SalaDTO> buscar(@PathVariable Long id){
        
    return ResponseEntity.ok(salaService.buscarPorId(id));
   }

   @PutMapping("/{id}")
   public ResponseEntity<SalaDTO> atualizar (@PathVariable Long id, @RequestBody SalaDTO dto){
        
    return ResponseEntity.ok(salaService.atualizar(id, dto));
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deletar (@PathVariable Long id){
    
    salaService.deletar(id);

    return ResponseEntity.noContent().build();
   }

}