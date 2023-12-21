package cz.uhk.sigmamail.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Override
    public User getUserById(int id){
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void saveUser(User user){
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("FROM User", User.class);
        return query.getResultList();
    }

    @Override
    public User getUserByUserame(String username) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE username=:username", User.class);
        query.setParameter("username",username);
        return query.getSingleResult();
    }

}

