package br.edu.infinet.sergiotransacoesapi.model.repository;

import br.edu.infinet.sergiotransacoesapi.model.domain.Conta;
import br.edu.infinet.sergiotransacoesapi.model.domain.enums.TipoConta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

    List<Conta> findByUsuarioCpfAndTipo(String cpf, TipoConta tipo);

    List<Conta> findByUsuarioCpfAndSaldoGreaterThan(String cpf, Double valor);
}
