package com.company;

import java.util.ArrayList;

public class Noeud {
    private Noeud parent;
    private int arite = 0;
    private boolean used = false;
    private int id;
    private int position=0;
    private ArrayList<Noeud> enfants;

    public Noeud(Noeud parent, int id) {
        this.parent = parent;
        this.id = id;
        this.enfants= new ArrayList<>();
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addChild(Noeud n){
        this.enfants.add(n);
        this.arite++;
        n.setPosition(this.position+1);
    }

    public ArrayList<Noeud> getChildren() {
        return enfants;
    }

    public int getId() {
        return id;
    }

    public Noeud getParent() {
        return parent;
    }

    public void setParent(Noeud parent) {
        this.parent = parent;
    }

    public int getArite() {
        return arite;
    }

    public void setArite(int arite) {
        this.arite = arite;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    @Override
    public String toString() {
        return "Noeud : " + id +
                " - Parent=" + (parent!=null? parent.getId() : "null") +
                " - arite=" + arite +
                " - used=" + used ;
    }

}
