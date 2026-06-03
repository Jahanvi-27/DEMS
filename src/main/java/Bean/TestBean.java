/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Bean;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author Dhwani
 */
@Named(value = "testBean")
@RequestScoped
public class TestBean {
    
    @PersistenceContext(unitName = "mypu")
    private EntityManager em;

    public String testConnection() {
        try {
            em.createNativeQuery("SELECT 1").getSingleResult();
            return "Database Connected";
        } catch(Exception e) {
            return e.getMessage();
        }
    }
    
    /**
     * Creates a new instance of TestBean
     */
    public TestBean() {
    }
    
}
