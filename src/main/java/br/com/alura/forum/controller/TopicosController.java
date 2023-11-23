package br.com.alura.forum.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controller.dto.DetalhamentoDoTopicoDto;
import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.form.AtualizacaoTopicoForm;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;

@RestController
@RequestMapping(value = "/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDto> lista(String nomeCurso) {
        System.out.println(nomeCurso);
        if (nomeCurso == null) {
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoDto.converter(topicos);
        } else {
            List<Topico> topicos = (List<Topico>) topicoRepository.findByCursoNome(nomeCurso);
            return TopicoDto.converter(topicos);
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm topicoForm,
            UriComponentsBuilder uriBuilder) {
        Topico topico = topicoForm.converter(cursoRepository);
        topicoRepository.save(topico);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhamentoDoTopicoDto> detalhar(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);   
        return topico.isPresent()
            ?ResponseEntity.ok(new DetalhamentoDoTopicoDto(topico.get()))
            : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDto> atualizar(@PathVariable long id, @RequestBody @Valid AtualizacaoTopicoForm form) {
        Optional<Topico> tOptional = topicoRepository.findById(id);
        return tOptional.isPresent()
            ? ResponseEntity.ok(new TopicoDto(form.atualizar(id, topicoRepository)))
            : ResponseEntity.notFound().build();
    }

    /**
     * Remove um tópico com o ID fornecido.
     *
     * @param id O ID do tópico a ser removido.
     * @return Um ResponseEntity indicando o sucesso da remoção (OK) ou indicando que o tópico não foi encontrado (Not Found).
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        return topicoRepository.existsById(id) ?
                ResponseEntity.ok().build() : // Retorna OK se o tópico existir e for removido com sucesso.
                ResponseEntity.notFound().build(); // Retorna Not Found se o tópico não for encontrado.
    }


}
