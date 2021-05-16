package com.github.grading.repository.impl;

import com.github.grading.entity.User;
import com.github.grading.repository.IUserRepository;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import java.util.Optional;

import static com.github.grading.utils.HibernateSessionFactoryUtil.getSession;

public class UserRepository extends AbstractRepository<User, Long> implements IUserRepository {

    @Override
    public Optional<User> findByLogin(String login) {
        TypedQuery<User> query = getSession().createQuery("From User where login=:login", User.class);
        query.setParameter("login", login);
        return query.getResultStream().findFirst();
    }

    @Override
    public void updatePassword(long id, String password) {
        getSession().createQuery("UPDATE User u SET u.password = :password where u.id= :id")
                .setParameter("password", password)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void updateLogin(long id, String login) {
        getSession().createQuery("UPDATE User u SET u.login = :login where u.id = :id")
                .setParameter("login", login)
                .setParameter("id", id)
                .executeUpdate();
    }
}
