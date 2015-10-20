package com.paracamplus.ilp1.ilp1tme3;

public class VectorObejct {

	private int taille;
	private Object element;

	public VectorObejct(int taille, Object object) {
		this.taille = taille;
		this.element = object;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public Object getElement() {
		return element;
	}

	public void setElement(Object element) {
		this.element = element;
	}

	@Override
	public String toString() {
		String s = "[ ";
		
		for (int i = 0; i < taille; i++)
			s += element.toString() + " ";
		s += "]";
		
		return s;
	}

}
