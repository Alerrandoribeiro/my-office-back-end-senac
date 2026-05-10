package com.myOficce.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myOficce.dto.SalaDTO;
import com.myOficce.entity.Sala;
import com.myOficce.mapper.SalaMapper;
import com.myOficce.repository.SalaRepository;

import jakarta.transaction.Transactional;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    public SalaDTO Cadastrar(SalaDTO dto) {
        Sala salvo = salaRepository.save(SalaMapper.toEntity(dto));
        return SalaMapper.toDTO(salvo);
    }

    @Transactional()
    public List<SalaDTO> Listar() {
        return SalaMapper.toDtoList(salaRepository.findAll());
    }

    @Transactional()
    public SalaDTO buscarPorId(Long id) {
        return salaRepository.findById(id)
                .map(SalaMapper::toDTO)
                .orElseThrow(() -> new NoSuchElementException("Sala não encontrada com o id: " + id));
    }

    @Transactional()
    public SalaDTO atualizar(Long id, SalaDTO dto) {
        Sala existente = salaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Sala não encontrada com o id: " + id));
        existente.setCep(dto.getCep());
        existente.setEstado(dto.getEstado());
        existente.setCidade(dto.getCidade());
        existente.setBairro(dto.getBairro());
        existente.setRua(dto.getRua());
        existente.setNumero(dto.getNumero());
        existente.setPreco(dto.getPreco());
        existente.setCapacidade(dto.getCapacidade());
        existente.setTipo_sala(dto.getTipo_sala());
        existente.setDescricao(dto.getDescricao());
        existente.setImagem(dto.getImagem());
        existente.setLatitude(dto.getLatitude());
        existente.setLongitude(dto.getLongitude());
        return SalaMapper.toDTO(salaRepository.save(existente));
    }

    @Transactional()
    public void deletar(Long id) {

        if (!salaRepository.existsById(id)) {

            throw new NoSuchElementException("Sala não encontrada com o id: " + id);
        }
        salaRepository.deleteById(id);
    }

}