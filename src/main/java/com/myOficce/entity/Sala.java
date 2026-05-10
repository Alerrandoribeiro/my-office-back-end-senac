package com.myOficce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sala")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_sala;

    @Column(nullable = false, length = 20)
    private String cep;

    @Column(nullable = false, length = 50)
    private String estado;

    @Column(nullable = false, length = 100)
    private String cidade;

    @Column(nullable = false, length = 100)
    private String bairro;

    @Column(nullable = false, length = 100)
    private String rua;

    @Column(nullable = false, length = 20)
    private String numero;

    @Column(nullable = false)
    private float preco;

    @Column(nullable = false)
    private int capacidade;

    @Column(nullable = false, length = 100)
    private String tipo_sala;

    @Column(nullable = false, length = 500)
    private String descricao;

    @Column(nullable = false, length = 500)
    private String imagem;

    @Column(nullable = false, length = 100)
    private String latitude;

    @Column(nullable = false, length = 100)
    private String longitude;

    public Sala() {
    }

    public Sala(
            String cep,
            String estado,
            String cidade,
            String bairro,
            String rua,
            String numero,
            float preco,
            int capacidade,
            String tipo_sala,
            String descricao,
            String imagem,
            String latitude,
            String longitude) {

        this.cep = cep;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.preco = preco;
        this.capacidade = capacidade;
        this.tipo_sala = tipo_sala;
        this.descricao = descricao;
        this.imagem = imagem;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId_sala() {
        return id_sala;
    }

    public void setId_sala(Long id_sala) {
        this.id_sala = id_sala;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getTipo_sala() {
        return tipo_sala;
    }

    public void setTipo_sala(String tipo_sala) {
        this.tipo_sala = tipo_sala;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Sala[id_sala=" + id_sala
                + ", cep=" + cep
                + ", estado=" + estado
                + ", cidade=" + cidade
                + ", bairro=" + bairro
                + ", rua=" + rua
                + ", numero=" + numero
                + ", preco=" + preco
                + ", capacidade=" + capacidade
                + ", tipo_sala=" + tipo_sala
                + ", descricao=" + descricao
                + ", imagem=" + imagem
                + ", latitude=" + latitude
                + ", longitude=" + longitude + "]";
    }
}