package br.com.alura.forum.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import br.com.alura.forum.model.StatusTopico;
import br.com.alura.forum.model.Topico;

public class DetalhamentoDoTopicoDto {

    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String nomeDoAutor;
    private StatusTopico status;
    private List<RespostasDto> respostas;

        public DetalhamentoDoTopicoDto(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
        this.nomeDoAutor =  topico.getAutor().getNome();
        this.status = topico.getStatus();
        this.respostas =  new ArrayList<>();
        this.respostas.addAll(topico.getRespostas().stream().map(RespostasDto::new).collect(Collectors.toList()));     
    }

        /**
         * @return the id
         */
        public Long getId() {
            return id;
        }

        /**
         * @return the titulo
         */
        public String getTitulo() {
            return titulo;
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

        /**
         * @return the status
         */
        public StatusTopico getStatus() {
            return status;
        }

        /**
         * @return the respostas
         */
        public List<RespostasDto> getRespostas() {
            return respostas;
        }

}
