package brq.com.br.desafio6.Paciente;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
@Autowired
private PacienteService pacienteService;

@GetMapping
public List<ModelsPaciente> listarPacientes() {
        return pacienteService.findAll();
        }

@GetMapping("/{id}")
public ModelsPaciente buscarPaciente(@PathVariable Long id) {
        return pacienteService.findById(id);
        }

@PostMapping
@ResponseStatus(HttpStatus.CREATED)
public ModelsPaciente criarPaciente(@RequestBody @Valid ModelsPaciente paciente) {
        return pacienteService.save(paciente);
        }

@PutMapping("/{id}")
public ModelsPaciente atualizarPaciente(@PathVariable Long id, @RequestBody @Valid ModelsPaciente pacienteAtualizado) {
        ModelsPaciente pacienteExistente = pacienteService.findById(id);
        pacienteExistente.setNome(pacienteAtualizado.getNome());
        pacienteExistente.setCpf(pacienteAtualizado.getCpf());
        pacienteExistente.setDataNascimento(pacienteAtualizado.getDataNascimento());
        return pacienteService.save(pacienteExistente);
        }

@DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void removerPaciente(@PathVariable Long id) {
        pacienteService.deleteById(id);
        }}