package ua.artcode.dao;


import ua.artcode.model.User;

import javax.persistence.EntityManager;
import java.util.List;


/**
 * Created by root on 15.06.2015.
 */
public class HibernateUserDAO implements UserDao {
    private EntityManager em = null;

    public HibernateUserDAO() {
        em = HibernateUtils.getEntityManager();
    }

    public User create(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        return user;
    }

    public User findByEmail(String email) {
        User user =  em.createQuery("select user from User as user where user.email = :email", User.class).
        setParameter("email", email).getSingleResult();
        return user;
    }

    public User findById(long id) {
        User user = null;
        em.getTransaction().begin();
        user = em.find(User.class,id);
        em.getTransaction().commit();
        return user;
    }

    public void delete(long id) {
        User user;
        em.getTransaction().begin();
        user = em.find(User.class,id);
        em.remove(user);
        em.getTransaction().commit();
    }

    public void update(User user) {

    }

    public List<User> findAll() {
        List usersList;
        em.getTransaction().begin();
        usersList = em.createQuery("select user from User user").getResultList();
        return usersList;
    }
}
