package brq.com.br.desafio6.Medico;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<ModelsMedico>> listar() {
        List<ModelsMedico> medicos = medicoService.findAll();
        return ResponseEntity.ok().body(medicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelsMedico> buscarPorId(@PathVariable Long id) {
        ModelsMedico medico = medicoService.findById(id);
        return ResponseEntity.ok().body(medico);
    }

    @PostMapping
    public ResponseEntity<ModelsMedico> cadastrar(@Valid @RequestBody ModelsMedico medico) {
        ModelsMedico medicoSalvo = medicoService.save(medico);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(medicoSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(medicoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModelsMedico> atualizar(@PathVariable Long id, @Valid @RequestBody ModelsMedico medicoAtualizado) {
        ModelsMedico medicoAtual = medicoService.findById(id);
        BeanUtils.copyProperties(medicoAtualizado, medicoAtual, "id");
        ModelsMedico medicoSalvo = medicoService.save(medicoAtual);
        return ResponseEntity.ok().body(medicoSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        medicoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
