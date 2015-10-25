package com.paracamplus.ilp2.tme5.partie2;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvisitor;
import com.paracamplus.ilp2.interfaces.IASTvisitable;

public class ASTsuivant extends ASTexpression implements IASTdernier, IASTvisitable{

	private String etiquette;
	
	public ASTsuivant(String etiquette) {
		this.etiquette = etiquette;
	}
	
	@Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			IASTvisitor<Result, Data, Anomaly> visitor, Data data)
			throws Anomaly {
		return null;
	}

	@Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp2.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
		return null;
	}

	@Override
	public String getEtiquette() {
		return etiquette;
	}

}
