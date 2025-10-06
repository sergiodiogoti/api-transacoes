package br.edu.infinet.app.repository;

import br.edu.infinet.app.domain.enums.TipoConta;
import br.edu.infinet.app.domain.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

    List<Conta> findByUsuarioCpfAndTipo(String cpf, TipoConta tipo);

    List<Conta> findByUsuarioCpfAndSaldoGreaterThan(String cpf, Double valor);
}
