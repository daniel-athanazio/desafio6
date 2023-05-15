package brq.com.br.desafio6.Consulta;

import brq.com.br.desafio6.Medico.MedicoRepository;
import brq.com.br.desafio6.Medico.ModelsMedico;
import brq.com.br.desafio6.Paciente.ModelsPaciente;
import brq.com.br.desafio6.Paciente.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    public List<ModelsConsulta> findAll() {
        return consultaRepository.findAll();
    }

    public ModelsConsulta findById(Long id) {
        Optional<ModelsConsulta> optionalConsulta = consultaRepository.findById(id);
        if (optionalConsulta.isPresent()) {
            return optionalConsulta.get();
        }
        throw new EntityNotFoundException("Consulta não encontrada com o ID: " + id);
    }

    public ModelsConsulta save(ModelsConsulta consulta) {
        Optional<ModelsPaciente> optionalPaciente = pacienteRepository.findById(consulta.getPaciente().getId());
        if (!optionalPaciente.isPresent()) {
            throw new EntityNotFoundException("Paciente não encontrado com o ID: " + consulta.getPaciente().getId());
        }
        consulta.setPaciente(optionalPaciente.get());

        Optional<ModelsMedico> optionalMedico = medicoRepository.findById(consulta.getMedico().getId());
        if (!optionalMedico.isPresent()) {
            throw new EntityNotFoundException("Médico não encontrado com o ID: " + consulta.getMedico().getId());
        }
        consulta.setMedico(optionalMedico.get());

        consulta.setDataHora(LocalDateTime.now());
        return consultaRepository.save(consulta);
    }

    public void deleteById(Long id) {
        if (!consultaRepository.existsById(id)) {
            throw new EntityNotFoundException("Consulta não encontrada com o ID: " + id);
        }
        consultaRepository.deleteById(id);


    }
}