package br.edu.infinet.sergiotransacoesapi.model.repository;

import br.edu.infinet.sergiotransacoesapi.model.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByCpf(String cpf);

    List<Usuario> findByPerfilIgnoreCase(String perfil);
    List<Usuario> findByRendaMensalBetween(double min, double max);

}
