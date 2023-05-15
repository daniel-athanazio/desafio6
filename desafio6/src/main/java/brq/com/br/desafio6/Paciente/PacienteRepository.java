package brq.com.br.desafio6.Paciente;

import brq.com.br.desafio6.Paciente.ModelsPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<ModelsPaciente, Long> {

}

