package com.myOficce.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myOficce.dto.ReservaDTO;
import com.myOficce.entity.Reserva;
import com.myOficce.entity.Sala;
import com.myOficce.entity.Usuario;
import com.myOficce.mapper.ReservaMapper;
import com.myOficce.repository.ReservaRepository;
import com.myOficce.repository.SalaRepository;
import com.myOficce.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ReservaDTO Cadastrar(ReservaDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));

        Sala sala = salaRepository.findById(dto.getSalaId())
                .orElseThrow(() -> new NoSuchElementException("Sala não encontrada"));

        boolean salaJaReservada = reservaRepository.existsBySalaAndData(sala, dto.getData());

        if (salaJaReservada) {
            throw new IllegalArgumentException("Esta sala já está reservada para esta data");
        }

        Reserva salvo = reservaRepository.save(ReservaMapper.toEntity(dto, usuario, sala));
        return ReservaMapper.toDTO(salvo);
    }

    @Transactional()
    public List<ReservaDTO> Listar() {
        return ReservaMapper.toDtoList(reservaRepository.findAll());
    }

    @Transactional()
    public ReservaDTO buscarPorId(Long id) {
        return reservaRepository.findById(id)
                .map(ReservaMapper::toDTO)
                .orElseThrow(() -> new NoSuchElementException("Reserva não encontrada com o id: " + id));
    }

    @Transactional()
    public ReservaDTO atualizar(Long id, ReservaDTO dto) {

        Reserva existente = reservaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Reserva não encontrada com o id: " + id));

        if (dto.getUsuarioId() != null && !existente.getUsuario().getId_usuario().equals(dto.getUsuarioId())) {
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));
            existente.setUsuario(usuario);
        }

        if (dto.getSalaId() != null && !existente.getSala().getId_sala().equals(dto.getSalaId())) {
            Sala sala = salaRepository.findById(dto.getSalaId())
                    .orElseThrow(() -> new NoSuchElementException("Sala não encontrada"));
            existente.setSala(sala);
        }

        if (dto.getData() != null && !existente.getData().equals(dto.getData())) {
            existente.setData(dto.getData());
        }

        Sala salaVerificacao = existente.getSala();
        boolean salaJaReservada = reservaRepository.existsBySalaAndData(salaVerificacao, existente.getData());

        if (salaJaReservada && !existente.getId_reserva().equals(id)) {
            throw new IllegalArgumentException("Esta sala já está reservada para esta data");
        }

        return ReservaMapper.toDTO(reservaRepository.save(existente));
    }

    @Transactional()
    public void deletar(Long id) {

        if (!reservaRepository.existsById(id)) {
            throw new NoSuchElementException("Reserva não encontrada com o id: " + id);
        }
        reservaRepository.deleteById(id);
    }
}
