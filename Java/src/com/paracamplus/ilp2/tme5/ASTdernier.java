package com.paracamplus.ilp2.tme5;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp2.interfaces.IASTvisitable;

public class ASTdernier extends ASTexpression implements IASTdernier,
		IASTvisitable {

	public <Result, Data, Anomaly extends Throwable> Result accept(
			IASTvisitor<Result, Data, Anomaly> visitor, Data data)
			throws Anomaly {
		return visitor.visit(this, data);
	}

	@Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp2.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
		System.out.println("nullilp2");
		return this.accept((IASTvisitor<Result, Data, Anomaly>) visitor, data);
	}

	@Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
		return this.accept((IASTvisitor<Result, Data, Anomaly>) visitor, data);
	}

}
