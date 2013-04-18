/**
 * 
 * Copyright (C) 2013 Emmanuel DESMONTILS
 * 
 * This library is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA
 * 
 * 
 * 
 * E-mail:
 * Emmanuel.Desmontils@univ-nantes.fr
 * 
 * 
 **/





package cobweb;

import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

public class Instance implements Cloneable {
    List Attributs;
    
    /**
       * Get the value of Attributs.
       * @return Value of Attributs.
       */
    public List getAttributs() {return Attributs;}
    
    /**
       * Set the value of Attributs.
       * @param v  Value to assign to Attributs.
       */
    public void setAttributs(List  v) {this.Attributs = v;}
    
    /**
       * Add the value to Attributs.
       * @param v  Value to add to Attributs.
       */
    public void addAttribut(Attribut  v) {this.Attributs.add(v);}

    public Instance() {
	setAttributs(new ArrayList());
    }

    void add(Instance I) {
	ListIterator i = Attributs.listIterator();
	ListIterator i2 = I.getAttributs().listIterator();
	Attribut at;
	while(i.hasNext()) {
	    at = (Attribut)i.next();
	    at.add((Attribut)i2.next());
	}
    }

    void remove(Instance I) {
	ListIterator i = Attributs.listIterator();
	ListIterator i2 = I.getAttributs().listIterator();
	Attribut at;
	while(i.hasNext()) {
	    at = (Attribut)i.next();
	    at.remove((Attribut)i2.next());
	}
    }

    void assign(Instance I) {
	ListIterator i2 = I.getAttributs().listIterator();
	Attribut at;
	while(i2.hasNext()) {
	    at = (Attribut)i2.next();
	    addAttribut((Attribut)at.clone());
	}
    }

    public String toString() {
	ListIterator i = Attributs.listIterator();
	Attribut at;
	String s = "";
	while(i.hasNext()) {
	    at = (Attribut)i.next();
	    s = s  + at+ (i.hasNext()?", ":"");
	}
	return s;
    }
 
    protected Object clone(){
	Instance i;
	try{
	    i = (Instance)super.clone();
	}catch(java.lang.CloneNotSupportedException e) {i = new Instance();}
	i.Attributs = new ArrayList(this.Attributs);
	return i;
    }
}
