package br.com.aldoc.aeroportos.repository;

import br.com.aldoc.aeroportos.domain.Aeroporto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AeroportoRepository extends JpaRepository<Aeroporto, Long> {
  Optional<Aeroporto> findByCodigoIata(String codigoIata);
  boolean existsByCodigoIata(String codigoIata);
}

