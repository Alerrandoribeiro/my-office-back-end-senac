package com.myOficce.controller;

import java.net.URI;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.myOficce.dto.UsuarioDTO;
import com.myOficce.service.UsuarioService;

@CrossOrigin(origins = "*") // evita problemas de CORS, permitindo que o frontend acesse a API mesmo estando em domínios diferentes
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @PostMapping
    public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody UsuarioDTO dto) {

        try {
            UsuarioDTO cadastrado = usuarioService.Cadastrar(dto);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(cadastrado.getId())
                    .toUri();

            return ResponseEntity.created(location).body(cadastrado);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@RequestBody UsuarioDTO dto) {
        try {
            UsuarioDTO usuarioLogado = usuarioService.login(dto);
            return ResponseEntity.ok(usuarioLogado);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ex.getMessage(), ex);
        }
    }
   
     @GetMapping
   public ResponseEntity<List<UsuarioDTO>> listar() {

      return ResponseEntity.ok(usuarioService.Listar());
   }

   @GetMapping("/{id}")
   public ResponseEntity<UsuarioDTO> buscar(@PathVariable Long id){
        
    return ResponseEntity.ok(usuarioService.buscarPorId(id));
   }

   @PutMapping("/{id}")
   public ResponseEntity<UsuarioDTO> atualizar (@PathVariable Long id, @RequestBody UsuarioDTO dto){
        
    return ResponseEntity.ok(usuarioService.atualizar(id, dto));
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deletar (@PathVariable Long id){
    
    usuarioService.deletar(id);

    return ResponseEntity.noContent().build();
   }


}
