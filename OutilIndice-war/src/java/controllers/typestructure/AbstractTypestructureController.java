package controllers.typestructure;

import entities.Typestructure;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.MouchardFacadeLocal;
import sessions.TypestructureFacadeLocal;
import utils.Routine;

public class AbstractTypestructureController {

    @EJB
    protected TypestructureFacadeLocal typestructureFacadeLocal;
    protected Typestructure typestructure = new Typestructure();
    protected List<Typestructure> typestructures = new ArrayList<>();

    protected Routine routine = new Routine();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    protected String mode = "";

    public Routine getRoutine() {
        return this.routine;
    }

    public String getMode() {
        return this.mode;
    }

    public Typestructure getTypestructure() {
        return typestructure;
    }

    public void setTypestructure(Typestructure typestructure) {
        this.typestructure = typestructure;
    }

    public List<Typestructure> getTypestructures() {
        typestructures = typestructureFacadeLocal.findAllRangeByNom();
        return typestructures;
    }

}
