package iuh.fit.services;

import iuh.fit.Repositories.BusrouteRepository;
import iuh.fit.entities.Busroute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusrouteService {
    @Autowired
    private BusrouteRepository busrouteRepository;

    //a
    public List<Busroute> getAll() {
        return busrouteRepository.findAll();
    }

    //b
    public Busroute getById(Integer id){
        return  busrouteRepository.findById(id).orElse(null);
    }

    //c
    public List<Busroute> getByName(String name){
        return busrouteRepository.getBusrouteByNameContaining(name);
    }

    //d - f
    public Busroute save(Busroute busroute) {
        return busrouteRepository.save(busroute);
    }

    public void delete(Integer id) {
        busrouteRepository.deleteById(id);
    }


}
