package com.paracamplus.ilp2.tme5.partie2;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.Inamed;

public class ASTloop extends com.paracamplus.ilp2.ast.ASTloop implements
		IASTloop {

	private Inamed etiquette;

	public ASTloop(IASTexpression condition, IASTexpression body,
			Inamed etiquette) {
		super(condition, body);
		this.etiquette = etiquette;
	}

	@Override
	public Inamed getEtiquette() {
		return etiquette;
	}

	public <Result, Data, Anomaly extends Throwable> Result accept(
			IASTvisitor<Result, Data, Anomaly> visitor, Data data)
			throws Anomaly {
		return visitor.visit(this, data);
	}

	@Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp2.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
		return visitor.visit(this, data);
	}

	@Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
		return ((IASTvisitor<Result, Data, Anomaly>) visitor).visit(this, data);
	}

}
