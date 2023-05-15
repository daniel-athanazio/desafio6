package brq.com.br.desafio6.Consulta;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    @Autowired
    private ConsultaService consultaService;

    @GetMapping
    public List<ModelsConsulta> listarConsultas() {
        return consultaService.findAll();
    }

    @GetMapping("/{id}")
    public ModelsConsulta buscarConsulta(@PathVariable Long id) {
        return consultaService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ModelsConsulta criarConsulta(@RequestBody @Valid ModelsConsulta consulta) {
        return consultaService.save(consulta);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerConsulta(@PathVariable Long id) {
        consultaService.deleteById(id);
    }


}