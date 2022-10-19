package com.mintic.usa.NuevoCinema.Service;

import com.mintic.usa.NuevoCinema.Model.Score;
import com.mintic.usa.NuevoCinema.Repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    public List<Score> getAll(){
        return scoreRepository.getAll();
    }

    public Optional<Score> getScore(int id){
        return scoreRepository.getScore(id);
    }

    public Score save(Score score){
        if(score.getIdScore() == null){
            return scoreRepository.save(score);
        } else {
            Optional<Score> scoreEncontrado = getScore(score.getIdScore());
            if (scoreEncontrado.isEmpty()){
                return scoreRepository.save(score);
            } else {
                return score;
            }
        }
    }

    public Score update(Score score){
        if(score.getIdScore() != null){
            Optional<Score> scoreEncontrado = scoreRepository.getScore(score.getIdScore());
            if(!scoreEncontrado.isEmpty()){
                if(score.getMessageText() != null){
                    scoreEncontrado.get().setMessageText(score.getMessageText());
                }
                if(score.getStars() != null){
                    scoreEncontrado.get().setStars(score.getStars());
                }
                return scoreRepository.save(scoreEncontrado.get());
            }
        }
        return score;
    }

    public boolean deleteScore(int scoreId) {
        Boolean resultado = getScore(scoreId).map(scorePorEliminar -> {
            scoreRepository.delete(scorePorEliminar);
            return true;
        }).orElse(false);
        return resultado;
    }
}
