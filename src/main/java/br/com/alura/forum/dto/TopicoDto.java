package br.com.alura.forum.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.forum.model.Topico;

public class TopicoDto {

    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;

    /**
     * @param id
     * @param titulo
     * @param mensagem
     * @param dataCriacao
     */
    public TopicoDto(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
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
     * Este método converte uma lista de objetos Topico em uma lista de objetos
     * TopicoDto.
     *
     * @param topicos A lista de objetos Topico que serão convertidos.
     * @return Uma lista de objetos TopicoDto que foram convertidos a partir da lista de entrada.
     */
    public static List<TopicoDto> converter(List<Topico> topicos) {
        return topicos.stream() // Inicia um stream a partir da lista de tópicos
                .map(TopicoDto::new) // Para cada tópico na lista, cria um novo objeto TopicoDto
                .collect(Collectors.toList()); // Coleta todos os objetos TopicoDto em uma nova lista
    }

}
