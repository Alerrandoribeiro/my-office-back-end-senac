package com.myOficce.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.myOficce.dto.ReservaDTO;
import com.myOficce.entity.Reserva;
import com.myOficce.entity.Sala;
import com.myOficce.entity.Usuario;

public class ReservaMapper {

    private ReservaMapper() {
    }

    public static ReservaDTO toDTO(Reserva entity) {

        if (entity == null) {
            return null;
        }

        return new ReservaDTO(
                entity.getId_reserva(),
                entity.getUsuario() != null ? entity.getUsuario().getId() : null,
                entity.getSala() != null ? entity.getSala().getId_sala() : null,
                entity.getData()
        );
    }

    public static Reserva toEntity(ReservaDTO dto, Usuario usuario, Sala sala) {

        if (dto == null) {
            return null;
        }

        Reserva r = new Reserva();

        r.setId_reserva(dto.getId_reserva());
        r.setUsuario(usuario);
        r.setSala(sala);
        r.setData(dto.getData());

        return r;
    }

    public static List<ReservaDTO> toDtoList(List<Reserva> list) {

        return list == null
                ? List.of()
                : list.stream()
                        .map(ReservaMapper::toDTO)
                        .collect(Collectors.toList());
    }

    public static List<Reserva> toEntityList(List<ReservaDTO> list, Usuario usuario, Sala sala) {

        return list == null
                ? List.of()
                : list.stream()
                        .map(dto -> ReservaMapper.toEntity(dto, usuario, sala))
                        .collect(Collectors.toList());
    }
}
