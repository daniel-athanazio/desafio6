package brq.com.br.desafio6.Medico;

import brq.com.br.desafio6.Paciente.ModelsPaciente;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public List<ModelsMedico> findAll() {
        return medicoRepository.findAll();
    }

    public ModelsMedico findById(Long id) {
        Optional<ModelsMedico> optionalMedico = medicoRepository.findById(id);
        if (optionalMedico.isPresent()) {
            return optionalMedico.get();
        }
        throw new EntityNotFoundException("Médico não encontrado com o ID: " + id);
    }

    public ModelsMedico save(ModelsMedico medico) {
        return medicoRepository.save(medico);
    }

    public void deleteById(Long id) {
        if (!medicoRepository.existsById(id)) {
            throw new EntityNotFoundException("Médico não encontrado com o ID: " + id);
        }
        medicoRepository.deleteById(id);
    }

}
