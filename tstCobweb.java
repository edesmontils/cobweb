/**
 * 
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





/**
 * tstCobweb.java
 *
 *
 * Created: Thu Jan 18 17:11:33 2001
 *
 * @author Emmanuel Desmontils
 * @version 1
 */

import cobweb.Cluster;
import cobweb.Instance;
import cobweb.NumericAttribut;

class MyInstance extends Instance {
    public MyInstance (float v) {
	super();
	NumericAttribut na = new NumericAttribut(0.0f,20.0f,0.5f);
	na.assign(new Float(v));
	addAttribut(na);
    }
}

public class tstCobweb  {
    
    public tstCobweb() {
    }
    
    

    public static void main(String argv []) {
	System.out.println("Begin");
	Cluster c = new Cluster();

	MyInstance mi = new MyInstance(8);
	System.out.println("Adding : "+mi);
	c.classify(mi);
	System.out.println(c);

	mi = new MyInstance(18);
	System.out.println("Adding : "+mi);
	c.classify(mi);
	System.out.println(c);

 	mi = new MyInstance(9);
	System.out.println("Adding : "+mi);
	c.classify(mi);
	System.out.println(c);

 	mi = new MyInstance((float)9.5);
	System.out.println("Adding : "+mi);
	c.classify(mi);
	System.out.println(c);

 	mi = new MyInstance((float)19);
	System.out.println("Adding : "+mi);
	c.classify(mi);
	System.out.println(c);

 	mi = new MyInstance((float)7);
	System.out.println("Adding : "+mi);
	c.classify(mi);
	System.out.println(c);

 	mi = new MyInstance((float)7.5);
	System.out.println("Adding : "+mi);
	c.classify(mi);
	System.out.println(c);

 	mi = new MyInstance((float)6.5);
	System.out.println("Adding : "+mi);
	c.classify(mi);
	System.out.println(c);

  	mi = new MyInstance((float)12);
 	System.out.println("Adding : "+mi);
 	c.classify(mi);
 	System.out.println(c);

  	mi = new MyInstance((float)13);
 	System.out.println("Adding : "+mi);
 	c.classify(mi);
 	System.out.println(c);

  	mi = new MyInstance((float)12.5);
 	System.out.println("Adding : "+mi);
 	c.classify(mi);
 	System.out.println(c);

	System.out.println("End");
   }
} // tstCobweb
