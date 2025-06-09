
package hu.oraszalon.entity;

import java.io.Serializable;

/**
 *
 * @author Böröczky Csaba
 */
public class OraSzalon implements Serializable {
    
    public int oraszalonId;
    private String megnevezes;
    private OraSzalonTipusEnum tipus;
    private int ar;
    private boolean vizalloe;

    public int getOraszalonId() {
        return oraszalonId;
    }

    public void setOraszalonId(int oraszalonId) {
        this.oraszalonId = oraszalonId;
    }
    
    public String getMegnevezes() {
        return megnevezes;
    }

    public void setMegnevezes(String megnevezes) {
        this.megnevezes = megnevezes;
    }

    public OraSzalonTipusEnum getTipus() {
        return tipus;
    }

    public void setTipus(OraSzalonTipusEnum tipus) {
        this.tipus = tipus;
    }

    public int getAr() {
        return ar;
    }

    public void setAr(int ar) {
        this.ar = ar;
    }

    public boolean isVizalloe() {
        return vizalloe;
    }

    public void setVizalloe(boolean vizalloe) {
        this.vizalloe = vizalloe;
    }
    
    @Override
    public String toString() {
        return megnevezes + " " + tipus.name() + " " + ar + " " + (isVizalloe() ? "Vizálló" : "Nem vizálló");
    }
    
}
