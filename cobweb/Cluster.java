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

import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;

public class Cluster extends Instance implements Cloneable {

    int level;

    Cluster father;
    
    /**
       * Get the value of father.
       * @return Value of father.
       */
    Cluster getFather() {return father;}
    
    /**
       * Set the value of father.
       * @param v  Value to assign to father.
       */
    void setFather(Cluster  v) {
	this.father = v;
	if (v!=null) level = father.level + 1;
    }
    
    
    Set childs;
    
    /**
       * Get the value of childs.
       * @return Value of childs.
       */
    public Set getChilds() {return childs;}
    
    /**
       * Set the value of childs.
       * @param v  Value to assign to childs.
       */
    void setChilds(Set  v) {this.childs = v;}
    
    /**
       * Add a child.
       * @param v  Value to add to the child list.
       */
    void addChild(Instance i) {this.childs.add(i);}
    
    int nbVal;
    
    /**
       * Get the value of nbVal.
       * @return Value of nbVal.
       */
    int getNbVal() {return nbVal;}
    
    /**
       * Set the value of nbVal.
       * @param v  Value to assign to nbVal.
       */
    void setNbVal(int  v) {this.nbVal = v;}
    
    boolean isInitialised;

    public Cluster() {
	super();
	setFather(null);
	setChilds(new HashSet());
	setNbVal(0);
	level = 0;
	isInitialised = false;
    }
    
    float P() {
	if (father!=null) 
	    return (float)getNbVal() / (float)father.getNbVal();
	else {
	    System.out.println("=============> P() à 1");
	    return 1;
	}
    }

    float PI() {
	int I = Attributs.size();
	if (I>0) {
	    float s=0;
	    ListIterator i = Attributs.listIterator(), iIns;
	    while(i.hasNext()) {
		Attribut a = (Attribut)i.next();
		s = s + a.PI(this);
	    }
	    return s / I;
	} else {
	    System.out.println("=============> PI() à 0");
	    return 0;
	}
    }

    float CU() {
	int K = childs.size();
	if (K>0) {
	    Iterator i = childs.iterator();
	    float s = 0;
	    Cluster c;
	    while(i.hasNext()) {
		c = (Cluster)i.next();
		s = s + c.P() * c.PI();
	    }
	    s = s - PI();
	    return s / K;
	} else {
	    System.out.println("=============> CU() à 1");
	    return 1;
	}
    }

    void add(Instance I) {
	if (!isInitialised) {
	    assign(I);
	} else {
	    super.add(I);
	    setNbVal(getNbVal()+1);
	}
    }

    void remove(Instance I) {
	super.remove(I);
	setNbVal(getNbVal()-1);
	if (getNbVal()==0) {
	    isInitialised = false;
	    Attributs.clear();
	}
    }

    void assign(Instance I) {
	super.assign(I);
	setNbVal(1);
	isInitialised = true;
    }

    public void classify(Instance Ins) {
	System.out.println("Classification dans {"+super.toString()+"}");
	if (!isInitialised) {
	    System.out.println("Premiere instance !");
	    assign(Ins);
	} else if (childs.size()==0) {
	    System.out.println("On est sur une feuille !");
	    Cluster m, o;
	    m = new Cluster();
	    m.setFather(this);
	    m.assign(this);
	    addChild(m);
	    
	    //if (father==null) add(Ins);
	    add(Ins);

	    o = new Cluster();
	    o.setFather(this);
	    o.assign(Ins);
	    addChild(o);
	} else {
	    float v=Float.NEGATIVE_INFINITY, 
		w=Float.NEGATIVE_INFINITY, 
		x=Float.NEGATIVE_INFINITY, 
		y=Float.NEGATIVE_INFINITY, 
		z=Float.NEGATIVE_INFINITY, 
		e;
	    Cluster c, q, f, firsts[], bestDiv=null;
	    Set div=null;

	    firsts=new Cluster[2];

	    //---------------------------------------------
	    //De toute façon la nouvelle instance va dans cette classe
	    //if (father==null) add(Ins);
	    add(Ins);

	    //---------------------------------------------
	    System.out.print("Classe à part ? => ");
	    q = new Cluster();
	    q.setFather(this);
	    q.assign(Ins);
	    addChild(q);
	    x = CU();
	    childs.remove(q);
	    System.out.println(x);

	    //---------------------------------------------
	    System.out.print("Détermination des 2 meilleurs W puis V : ");
	    firsts[0] = null;
	    firsts[1] = null;
	    Iterator i = childs.iterator();
	    while(i.hasNext()) {
		c = (Cluster)i.next();
		c.add(Ins);
		e = CU();
		c.remove(Ins);
		if (e>w) {
		    w = e;
		    firsts[0] = c;
		} else if (e>v) {
		    v = e;
		    firsts[1] = c;
		}
	    }
	    System.out.println(w+"/"+v);

	    //---------------------------------------------
	    System.out.print("Fusion des deux meilleurs ? => ");
	    f = new Cluster();
	    f.setFather(this);
	    if ((firsts[0]!=null)&&(firsts[1]!=null)) {//&&(childs.size()>2)
		childs.remove(firsts[0]);
		childs.remove(firsts[1]);
		f.assign(firsts[0]);
		f.add(firsts[1]);
		f.add(Ins);
		f.addChild(firsts[0]);firsts[0].setFather(f);
		f.addChild(firsts[1]);firsts[1].setFather(f);
		addChild(f);
		y = CU();
		f.remove(Ins);
		childs.remove(f);
		childs.add(firsts[0]);firsts[0].setFather(this);
		childs.add(firsts[1]);firsts[1].setFather(this);
	    }
	    System.out.println(y);

	    //---------------------------------------------
	    System.out.print("Division de la meilleure ? => ");
	    if (firsts[0]!=null) {
		float bestVal=Float.NEGATIVE_INFINITY;
		childs.remove(firsts[0]);
		div = firsts[0].getChilds();
		childs.addAll(div);
	        i = div.iterator();
		while(i.hasNext()) {
		    c = (Cluster)i.next();
		    c.setFather(this);
		}
	        i = div.iterator();
		while(i.hasNext()) {
		    c = (Cluster)i.next();
		    c.add(Ins);
		    e = CU();
		    c.remove(Ins);
		    if (e>bestVal) {
			bestVal = e;
			bestDiv = c;
		    }
		}
		if (bestDiv != null) {
		    z = bestVal;
		} else z = Float.NEGATIVE_INFINITY;
		childs.removeAll(div);
		childs.add(firsts[0]);
		i = div.iterator();
		while(i.hasNext()) {
		    c = (Cluster)i.next();
		    c.setFather(firsts[0]);
		}
	    }
	    System.out.println(z);

	    //---------------------------------------------
	    System.out.print("Selection du meilleur score:");
	    float theBest = Math.max(w, Math.max(x, Math.max(y,z)));
	    if (w==theBest) {System.out.println("w");
		//firsts[0].add(Ins);
		firsts[0].classify(Ins);
	    } else if (x==theBest) {System.out.println("x");
		addChild(q);
	    } else if (y==theBest) {System.out.println("y");
		addChild(f);
		childs.remove(firsts[0]);firsts[0].setFather(f);
		childs.remove(firsts[1]);firsts[1].setFather(f);
		f.classify(Ins);
	    } else {//z==theBest
		System.out.println("z");
		childs.remove(firsts[0]);
		childs.addAll(div);
		i = div.iterator();
		while(i.hasNext()) {
		    c = (Cluster)i.next();
		    c.setFather(this);
		}
		//bestDiv.add(Ins);
		bestDiv.classify(Ins);
	    }
	}
    }

    public String toString() {
	String s = "";
	for(int i=0;i<level;i++) s = s + "| ";
	s = s + "Cluster="+super.toString()+" % P:"+P()+" PI:"+PI()+"\n";
	Iterator i = getChilds().iterator();
	Cluster c; 
	while(i.hasNext()) {
	    c = (Cluster)i.next();
	    s = s + c.toString();
	}
	return s;
    }

    protected Object clone(){
	Cluster i = (Cluster)super.clone();
	i.setChilds(new HashSet(this.childs));
	return i;
    }
}
