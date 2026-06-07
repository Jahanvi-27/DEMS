/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package facades;

import Entity.Cases;
import Entity.Users;
import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author Dhwani
 */
@Stateless
@LocalBean
public class UserFacade {
    
    @PersistenceContext(unitName = "mypu")
    private EntityManager em;

    public Users login(String email) {

        try {

            TypedQuery<Users> query =
            em.createQuery(
            "SELECT u FROM Users u WHERE u.email = :email",
            Users.class);

            query.setParameter("email", email);

            return query.getSingleResult();

        } catch(Exception e) {

            return null;

        }
    }
    
    public List<Users> findAllUsers() {

    return em.createQuery(
    "SELECT u FROM Users u",
    Users.class)
    .getResultList();
}
    public void save(Users user) {

    em.persist(user);

}
    
    public Users find(Integer id){

    return em.find(Users.class,id);

}

public void delete(Users user){

    em.remove(em.merge(user));

}
    
    public void update(Users user) {

    em.merge(user);

}
    
      public List<Users> findAll(){

        return em.createQuery(
    "SELECT u FROM Users u",
    Users.class)
    .getResultList();
    }
      
      public long countAll() {
    return em.createQuery("SELECT COUNT(c) FROM Users c", Long.class)
            .getSingleResult();
}
    
 


    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
