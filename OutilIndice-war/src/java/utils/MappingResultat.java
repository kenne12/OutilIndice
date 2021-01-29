/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class MappingResultat {

    private int idCritere;
    private double point;

    public MappingResultat() {
    }

    public int getIdCritere() {
        return idCritere;
    }

    public void setIdCritere(int idCritere) {
        this.idCritere = idCritere;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public static List<MappingResultat> getMapping() {
        List<MappingResultat> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MappingResultat mr = new MappingResultat();
            mr.idCritere = i + 1;
            mr.point = 0;
            list.add(mr);
        }
        return list;
    }
}
