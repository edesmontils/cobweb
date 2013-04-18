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


public class NumericAttribut extends Attribut{
    
    float s;
    
    /**
       * Get the value of s.
       * @return Value of s.
       */
    float getS() {return s;}
    
    /**
       * Set the value of s.
       * @param v  Value to assign to s.
       */
    void setS(float  v) {this.s = v;}
    
    float s2;
    
    /**
       * Get the value of s2.
       * @return Value of s2.
       */
    float getS2() {return s2;}
    
    /**
       * Set the value of s2.
       * @param v  Value to assign to s2.
       */
    void setS2(float  v) {this.s2 = v;}


    float acuteness;
    
    /**
       * Get the value of acuteness.
       * @return Value of acuteness.
       */
    public float getAcuteness() {return acuteness;}
    
    /**
       * Set the value of acuteness.
       * @param v  Value to assign to acuteness.
       */
    public void setAcuteness(float  v) {this.acuteness = v;}
    

    float min;
    
    /**
       * Get the value of min.
       * @return Value of min.
       */
    public float getMin() {return min;}
    
    /**
       * Set the value of min.
       * @param v  Value to assign to min.
       */
    public void setMin(float  v) {this.min = v;}
    
    float max;
    
    /**
       * Get the value of max.
       * @return Value of max.
       */
    public float getMax() {return max;}
    
    /**
       * Set the value of max.
       * @param v  Value to assign to max.
       */
    public void setMax(float  v) {this.max = v;}
    
    public NumericAttribut(int alpha) {
	this(Integer.MIN_VALUE,Integer.MAX_VALUE,alpha);
    }

    public NumericAttribut(float alpha) {
	this(Float.NEGATIVE_INFINITY,Float.POSITIVE_INFINITY,alpha);
    }

    public NumericAttribut() {
	this((float)0.01);
    }

    public NumericAttribut(int min, int max) {
	this(min, max,1);
    }

    public NumericAttribut(int min, int max, int alpha) {
	this((float)min,(float)max,(float)alpha);
    }

    public NumericAttribut(float min, float max, float alpha) {
	super();
	setS(0);
	setS2(0);
	setAcuteness(alpha);
	setMin(min);
	setMax(max);
    }

    float mu(float s, int n) {
	return s/(float)n;
    }

    float sigma(float s, float s2, int n) {
	return (float)Math.sqrt(s2/(float)n-mu(s,n)*mu(s,n));
    }

    float PI(Cluster c){
	return getAcuteness() / Math.max(getAcuteness(),sigma(getS(),getS2(),getN()));
    }

    void add(Attribut v){
	NumericAttribut na = (NumericAttribut)v;
	setS(getS()+na.getS());
	setS2(getS2()+na.getS2());
	setN(getN()+na.getN());
	setAcuteness(Math.min(getAcuteness(),na.getAcuteness()));
	setMin(Math.min(getMin(),na.getMin()));
	setMax(Math.max(getMax(),na.getMax()));
    }

    public void add(Object v) {
	float n = ((Number)v).floatValue();
	setS(getS()+n);
	setS2(getS2()+n*n);
	setN(getN()+1);
    }

    void remove(Attribut v){
	NumericAttribut na = (NumericAttribut)v;
	setS(getS()-na.getS());
	setS2(getS2()-na.getS2());
	setN(getN()-na.getN());
	//setAcuteness(Math.min(getAcuteness(),na.getAcuteness()));
	//setMin(Math.min(getMin(),na.getMin()));
	//setMax(Math.max(getMax(),na.getMax()));
    }

    public void remove(Object v) {
	float n = ((Number)v).floatValue();
	setS(getS()-n);
	setS2(getS2()-n*n);
	setN(getN()-1);
    }

    public void assign(Object v) {
	float n = ((Number)v).floatValue();
	setS(n);
	setS2(n*n);
	setN(1);	
    }

    double Arrondir(double val, double nbDec) {
	double t = Math.pow(10,nbDec);
	return Math.round(val*t)/t;
    }    

    public String toString() {
	return super.toString() + "["+getS()+";"+getS2()+"]"+
	    " sigma:"+ Arrondir(sigma(getS(),getS2(),getN()),2)+
	    " mu:"+Arrondir(mu(getS(),getN()),2)+ " PI:"+PI(null);
    }
}
