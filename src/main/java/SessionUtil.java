
import Entity.Users;
import jakarta.faces.context.FacesContext;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dhwani
 */
public class SessionUtil {
    public static Users getUser() {
        return (Users) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap()
                .get("user");
    }
}
