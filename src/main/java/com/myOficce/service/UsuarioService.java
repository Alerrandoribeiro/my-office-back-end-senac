package com.myOficce.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myOficce.dto.UsuarioDTO;
import com.myOficce.entity.Usuario;
import com.myOficce.mapper.UsuarioMapper;
import com.myOficce.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public UsuarioDTO Cadastrar(UsuarioDTO dto) {
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        Usuario salvo = usuarioRepository.save(UsuarioMapper.toEntity(dto));
        return UsuarioMapper.toDTO(salvo);
    }

    public UsuarioDTO login(UsuarioDTO dto) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(dto.getEmail());

        Usuario usuario = usuarioOptional
                .orElseThrow(() -> new IllegalArgumentException("Email ou senha inválidos"));

        if (!usuario.getSenha().equals(dto.getSenha())) {
            throw new IllegalArgumentException("Email ou senha inválidos");
        }

        UsuarioDTO usuarioDTO = UsuarioMapper.toDTO(usuario);
        usuarioDTO.setSenha(null);
        return usuarioDTO;
    }

    @Transactional()
    public List<UsuarioDTO> Listar() {
        return UsuarioMapper.toDtoList(usuarioRepository.findAll());
    }

    @Transactional()
    public UsuarioDTO buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(UsuarioMapper::toDTO)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado com o id: " + id));
    }

    @Transactional()
    public UsuarioDTO atualizar(Long id, UsuarioDTO dto) {
        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado com o id: " + id));
        existente.setNome(dto.getNome());
        existente.setEmail(dto.getEmail());
        existente.setTelefone(dto.getTelefone());
        existente.setSenha(dto.getSenha());
        return UsuarioMapper.toDTO(usuarioRepository.save(existente));
    }

    @Transactional()
    public void deletar(Long id) {

        if (!usuarioRepository.existsById(id)) {

            throw new NoSuchElementException("Usuário não encontrado com o id: " + id);
        }
        usuarioRepository.deleteById(id);
    }

}