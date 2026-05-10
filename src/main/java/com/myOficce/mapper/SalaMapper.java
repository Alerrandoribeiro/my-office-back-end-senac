package com.myOficce.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.myOficce.dto.SalaDTO;
import com.myOficce.entity.Sala;

public class SalaMapper {

    private SalaMapper() {
    }

    public static SalaDTO toDTO(Sala entity) {

        if (entity == null) {
            return null;
        }

        return new SalaDTO(
                entity.getId_sala(),
                entity.getCep(),
                entity.getEstado(),
                entity.getCidade(),
                entity.getBairro(),
                entity.getRua(),
                entity.getNumero(),
                entity.getPreco(),
                entity.getCapacidade(),
                entity.getTipo_sala(),
                entity.getDescricao(),
                entity.getImagem(),
                entity.getLatitude(),
                entity.getLongitude());
    }

    public static Sala toEntity(SalaDTO dto) {

        if (dto == null) {
            return null;
        }

        Sala s = new Sala();

        s.setId_sala(dto.getId_sala());
        s.setCep(dto.getCep());
        s.setEstado(dto.getEstado());
        s.setCidade(dto.getCidade());
        s.setBairro(dto.getBairro());
        s.setRua(dto.getRua());
        s.setNumero(dto.getNumero());

        s.setPreco(dto.getPreco());
        s.setCapacidade(dto.getCapacidade());
        s.setTipo_sala(dto.getTipo_sala());

        s.setDescricao(dto.getDescricao());
        s.setImagem(dto.getImagem());
        s.setLatitude(dto.getLatitude());
        s.setLongitude(dto.getLongitude());

        return s;
    }

    public static List<SalaDTO> toDtoList(List<Sala> list) {

        return list == null
                ? List.of()
                : list.stream()
                        .map(SalaMapper::toDTO)
                        .collect(Collectors.toList());
    }

    public static List<Sala> toEntityList(List<SalaDTO> list) {

        return list == null
                ? List.of()
                : list.stream()
                        .map(SalaMapper::toEntity)
                        .collect(Collectors.toList());
    }
}