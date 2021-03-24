package utils;

import entities.Categorie;
import entities.Critere;
import entities.Criterestructure;
import entities.Periode;
import entities.Responsabilite;
import entities.Service;
import entities.Structure;
import entities.TypeSousPeriode;
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

    public static List<Criterestructure> getCritereStructures() {
        HttpSession session = getSession();
        if (session != null) {
            return (List<Criterestructure>) session.getAttribute("criterestructures");
        }
        return null;
    }

    public static List<Critere> getCriteres() {
        HttpSession session = getSession();
        if (session != null) {
            return (List<Critere>) session.getAttribute("criteres");
        }
        return null;
    }

    public static List<Service> getServices() {
        HttpSession session = getSession();
        if (session != null) {
            return (List<Service>) session.getAttribute("services");
        }
        return null;
    }

    public static List<Categorie> getCategories() {
        HttpSession session = getSession();
        if (session != null) {
            return (List<Categorie>) session.getAttribute("categories");
        }
        return null;
    }

    public static List<TypeSousPeriode> getTypeSousPeriodes() {
        HttpSession session = getSession();
        if (session != null) {
            return (List<TypeSousPeriode>) session.getAttribute("type_sousperiodes");
        }
        return null;
    }

    public static List<Responsabilite> getResponsabilites() {
        HttpSession session = getSession();
        if (session != null) {
            return (List<Responsabilite>) session.getAttribute("responsabilites");
        }
        return null;
    }

}
