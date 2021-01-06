package utils;

import entities.Periode;
import entities.Structure;
import entities.Utilisateur;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionMBean {

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }

    public static Utilisateur getUserAccount() {
        HttpSession session = getSession();
        if (session != null) {
            return (Utilisateur) session.getAttribute("user");
        }
        return null;
    }

    public static List<Long> getAccess() {
        HttpSession session = getSession();
        if (session != null) {
            return (List) session.getAttribute("user_access_id");
        }
        return null;
    }

    public static Structure getStructure() {
        HttpSession session = getSession();
        if (session != null) {
            return (Structure) session.getAttribute("structure");
        }
        return null;
    }

    public static Periode getPeriode() {
        HttpSession session = getSession();
        if (session != null) {
            return (Periode) session.getAttribute("periode");
        }
        return null;
    }

}
