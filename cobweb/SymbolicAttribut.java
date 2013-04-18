/**
 * 
 * This is part of "Thot-riio".
 * 
 * "Thot-riio"  allows to index a web site.
 * 
 * Copyright (C) 2001 Emmanuel DESMONTILS
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
 * Emmanuel.Desmontils@irin.univ-nantes.fr
 * 
 * 
 **/





package cobweb;

import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Iterator;

public class SymbolicAttribut extends Attribut{

    Map values;
    
    /**
       * Get the value of values.
       * @return Value of values.
       */
    public Map getValues() {return values;}
    
    /**
       * Set the value of values.
       * @param v  Value to assign to values.
       */
    public void setValues(Map  v) {this.values = v;}
    

    public SymbolicAttribut() {
	super();
	setValues(new HashMap());
    }

    float PI(Cluster c) {
	Map.Entry me;
	Set setI;
	Iterator i  = values.entrySet().iterator(), iI;
	int s=0;
	int na;
	while(i.hasNext()){
	    me = (Map.Entry)i.next();
	    na = ((Integer)me.getValue()).intValue();
	    s = s + (na/c.getNbVal())*(na/c.getNbVal());
	}
	return s;
    }

    void add(Attribut v) {
	SymbolicAttribut sa = (SymbolicAttribut)v;
	Iterator i = values.keySet().iterator();
	int n; Integer nI;
	Object key;
	while(i.hasNext()){
	    key = i.next();
	    nI = (Integer)sa.getValues().get(key);
	    if (nI != null) {
		n = ((Integer)values.get(key)).intValue();
		values.put(key,new Integer(n+nI.intValue()));
	    } else System.err.println("??? add de Symbolic Attribut ????");
	}	
    }

    public void add(Object v) {
	System.out.println("Méthode 'add' de 'SymbolicAttribut' pas implémentée");
    }

    void remove(Attribut v) {
	System.out.println("Méthode 'remove' de 'SymbolicAttribut' pas implémentée");
    }

    public void remove(Object v) {
	System.out.println("Méthode 'remove' de 'SymbolicAttribut' pas implémentée");
    }

    public void assign(Object v) {
	System.out.println("Méthode 'assign' de 'SymbolicAttribut' pas implémentée");	
    }

    public String toString() {
	return super.toString() +" val:" + values;
    }
}
