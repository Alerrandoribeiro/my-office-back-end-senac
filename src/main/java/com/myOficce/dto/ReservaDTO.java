package com.myOficce.dto;

import java.time.LocalDate;

public class ReservaDTO {

    private Long id_reserva;
    private Long usuarioId;
    private Long salaId;
    private LocalDate data;

    public ReservaDTO() {
    }

    public ReservaDTO(Long id_reserva, Long usuarioId, Long salaId, LocalDate data) {
        this.id_reserva = id_reserva;
        this.usuarioId = usuarioId;
        this.salaId = salaId;
        this.data = data;
    }

    public Long getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(Long id_reserva) {
        this.id_reserva = id_reserva;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getSalaId() {
        return salaId;
    }

    public void setSalaId(Long salaId) {
        this.salaId = salaId;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ReservaDTO[id_reserva=" + id_reserva
                + ", usuarioId=" + usuarioId
                + ", salaId=" + salaId
                + ", data=" + data + "]";
    }
}
