package brq.com.br.desafio6.Medico;

import brq.com.br.desafio6.Consulta.ModelsConsulta;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "medicos")
@Data
public class ModelsMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String crm;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ModelsConsulta> consultas;
}
