package com.myOficce.service;

import java.util.List;
import java.util.NoSuchElementException;

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
        Usuario salvo = usuarioRepository.save(UsuarioMapper.toEntity(dto));
        return UsuarioMapper.toDTO(salvo);
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