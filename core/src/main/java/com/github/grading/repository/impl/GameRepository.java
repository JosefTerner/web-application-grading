package com.github.grading.repository.impl;

import com.github.grading.entity.Game;
import com.github.grading.repository.IGameRepository;

import javax.persistence.TypedQuery;
import java.util.List;

import static com.github.grading.utils.HibernateSessionFactoryUtil.getSession;

public class GameRepository extends AbstractRepository<Game, Long> implements IGameRepository {

    @Override
    public List<Game> findAll(long tournamentId) {
        TypedQuery<Game> query = getSession().createQuery("From Game where id=:tournamentId", Game.class);
        query.setParameter("id", tournamentId);
        return query.getResultList();
    }
}
