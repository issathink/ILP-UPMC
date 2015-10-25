package com.paracamplus.ilp2.tme5.partie2.interpreter;

import com.paracamplus.ilp1.interfaces.Inamed;

public class DernierException extends
		com.paracamplus.ilp2.tme5.DernierException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Inamed etiquette;

	public DernierException(Inamed etiquette) {
		this.etiquette = etiquette;
	}

	public Inamed getEtiquette() {
		return etiquette;
	}

}
