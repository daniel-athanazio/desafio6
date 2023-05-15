package brq.com.br.desafio6.Consulta;


import brq.com.br.desafio6.Medico.ModelsMedico;
import brq.com.br.desafio6.Paciente.ModelsPaciente;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "consultas")
@Data
public class ModelsConsulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private ModelsPaciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private ModelsMedico medico;

    @Column(nullable = false)
    private LocalDateTime dataHora;
}
