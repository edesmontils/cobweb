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

public abstract class Attribut implements Cloneable {

    static int attNumber=0;

    String name;
    
    /**
       * Get the value of name.
       * @return Value of name.
       */
    public String getName() {return name;}
    
    /**
       * Set the value of name.
       * @param v  Value to assign to name.
       */
    public void setName(String  v) {this.name = v;}
    

    int n;
    
    /**
       * Get the value of n.
       * @return Value of n.
       */
    int getN() {return n;}
    
    /**
       * Set the value of nbVal.
       * @param v  Value to assign to nbVal.
       */
    void setN(int  v) {this.n = v;}
    
    public Attribut() {
	attNumber = attNumber + 1;
	setName("a"+attNumber);
	setN(0);
    }

    abstract float PI(Cluster c);
    abstract void add(Attribut v);
    abstract void remove(Attribut v);

    public abstract void add(Object v);
    public abstract void remove(Object v);
    public abstract void assign(Object v);

    public String toString() {
	return getName() + " (" + getN() +")";
    }

    protected Object clone() {
	try {
	    Attribut a=(Attribut)super.clone();
	    a.setName(this.getName()+"+");
	    return a;
	} catch (CloneNotSupportedException e) {return null;}
    }

}
