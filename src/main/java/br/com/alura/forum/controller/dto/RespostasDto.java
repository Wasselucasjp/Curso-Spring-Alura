package br.com.alura.forum.controller.dto;

import java.time.LocalDateTime;

import br.com.alura.forum.model.Resposta;

public class RespostasDto {

    private Long id;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String nomeDoAutor;

    public RespostasDto(Resposta resposta) {
        this.id = resposta.getId();
        this.mensagem = resposta.getMensagem();
        this.dataCriacao = resposta.getDataCriacao();
        this.nomeDoAutor = resposta.getAutor().getNome();
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the mensagem
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * @return the dataCriacao
     */
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    /**
     * @return the nomeDoAutor
     */
    public String getNomeDoAutor() {
        return nomeDoAutor;
    }

}
