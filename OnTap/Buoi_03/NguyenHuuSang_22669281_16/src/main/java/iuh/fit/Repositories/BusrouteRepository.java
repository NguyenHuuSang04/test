package iuh.fit.Repositories;

import iuh.fit.entities.Busroute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusrouteRepository extends JpaRepository<Busroute, Integer> {
    List<Busroute> getBusrouteByNameContaining(String name);
}
