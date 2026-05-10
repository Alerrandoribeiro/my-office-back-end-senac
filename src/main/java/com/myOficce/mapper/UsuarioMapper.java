package com.myOficce.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.myOficce.dto.UsuarioDTO;
import com.myOficce.entity.Usuario;

public class UsuarioMapper {

    private UsuarioMapper() {
    }

    public static UsuarioDTO toDTO(Usuario entity) {
        if (entity == null) {
            return null;
        }
        return new UsuarioDTO(
                entity.getId_usuario(),
                entity.getNome(),
                entity.getEmail(),
                entity.getTelefone(),
                entity.getSenha());
    }

    public static Usuario toEntity(UsuarioDTO dto) {

        if (dto == null)
            return null;
        Usuario u = new Usuario();
        u.setId_usuario(dto.getId());
        u.setNome(dto.getNome());
        u.setEmail(dto.getEmail());
        u.setTelefone(dto.getTelefone());
        u.setSenha(dto.getSenha());
        return u;
    }

    public static List<UsuarioDTO> toDtoList(List<Usuario> list) {
        return list == null
                ? List.of()
                : list.stream()
                        .map(UsuarioMapper::toDTO)
                        .collect(Collectors.toList());
    }

    public static List<Usuario> toEntityList(List<UsuarioDTO> list) {
        return list == null
                ? List.of()
                : list.stream()
                        .map(UsuarioMapper::toEntity)
                        .collect(Collectors.toList());
    }

}
