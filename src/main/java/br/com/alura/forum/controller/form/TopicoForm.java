package br.com.alura.forum.controller.form;

import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.CursoRepository;

public class TopicoForm {
    private String titulo;
    private String mensagem;
    private String nomeCurso;

    
    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }
    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    /**
     * @return the mensagem
     */
    public String getMensagem() {
        return mensagem;
    }
    /**
     * @param mensagem the mensagem to set
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    /**
     * @return the nomeCurso
     */
    public String getNomeCurso() {
        return nomeCurso;
    }
    /**
     * @param nomeCurso the nomeCurso to set
     */
    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Topico converter(CursoRepository cursoRepository) {
        Curso curso = cursoRepository.findByNome(nomeCurso);
        return new Topico(titulo, mensagem, curso);
    }

    
}
