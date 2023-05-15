package brq.com.br.desafio6.Paciente;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<ModelsPaciente> findAll() {
        return pacienteRepository.findAll();
    }

    public ModelsPaciente findById(Long id) {
        Optional<ModelsPaciente> optionalPaciente = pacienteRepository.findById(id);
        if (optionalPaciente.isPresent()) {
            return optionalPaciente.get();
        }
        throw new EntityNotFoundException("Paciente não encontrado com o ID: " + id);
    }

    public ModelsPaciente save(ModelsPaciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public void deleteById(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new EntityNotFoundException("Paciente não encontrado com o ID: " + id);
        }
        pacienteRepository.deleteById(id);
    }

}
