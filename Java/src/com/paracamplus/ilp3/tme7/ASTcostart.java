package com.paracamplus.ilp3.tme7;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTinvocation;

public class ASTcostart extends ASTexpression implements IASTinvocation {

	public ASTcostart(IASTexpression function, IASTexpression[] arguments) {
		this.function = function;
		this.arguments = arguments;
	}

	private final IASTexpression function;
	private final IASTexpression[] arguments;

	@Override
	public IASTexpression getFunction() {
		return function;
	}

	@Override
	public IASTexpression[] getArguments() {
		return arguments;
	}

	@Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
		System.out.println("je visite ASTcostart ilp1");
		return this.accept((IASTvisitor<Result, Data, Anomaly>) visitor, data); // .visit(this, data);
	}

	public <Result, Data, Anomaly extends Throwable> Result accept(
			IASTvisitor<Result, Data, Anomaly> visitor, Data data)
			throws Anomaly {
		System.out.println("je visite ASTcostart 3");
		return visitor.visit(this, data);
	}

}
