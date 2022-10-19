package com.mintic.usa.NuevoCinema.Service;

import com.mintic.usa.NuevoCinema.Model.Cinema;
import com.mintic.usa.NuevoCinema.Repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CinemaService {
    @Autowired
    private CinemaRepository cinemaRepository;

    public List<Cinema> getAll(){
        return cinemaRepository.getAll();
    }

    public Optional<Cinema> getCinema(int id){
        return cinemaRepository.getCinema(id);
    }

    public Cinema save(Cinema cinema){
        if(cinema.getId() == null){
            return cinemaRepository.save(cinema);
        } else {
            Optional<Cinema> cinemaEncontrado = cinemaRepository.getCinema(cinema.getId());
            if (cinemaEncontrado.isEmpty()){
                return cinemaRepository.save(cinema);
            } else {
                return cinema;
            }
        }
    }

    public Cinema update(Cinema cinema){
        if(cinema.getId() != null){
            Optional<Cinema> cinemaEncontrado = cinemaRepository.getCinema(cinema.getId());
            if(!cinemaEncontrado.isEmpty()){
                if(cinema.getName() != null){
                    cinemaEncontrado.get().setName(cinema.getName());
                }
                if(cinema.getOwner() != null){
                    cinemaEncontrado.get().setOwner(cinema.getOwner());
                }
                if(cinema.getCapacity() != null){
                    cinemaEncontrado.get().setCapacity(cinema.getCapacity());
                }
                if(cinema.getDescription() != null){
                    cinemaEncontrado.get().setDescription(cinema.getDescription());
                }
                if(cinema.getCategory() != null){
                    cinemaEncontrado.get().setCategory(cinema.getCategory());
                }
                return cinemaRepository.save(cinemaEncontrado.get());
            }
        }
        return cinema;
    }

    public boolean deleteCinema(int cinemaId) {
        Boolean resultado = getCinema(cinemaId).map(cinemaPorEliminar -> {
            cinemaRepository.delete(cinemaPorEliminar);
            return true;
        }).orElse(false);
        return resultado;
    }
}
