package com.paracamplus.ilp1.ilp1tme1;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvisitor;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;

public class ASTsequence implements IASTsequence {

	public ASTsequence(IASTexpression[] expressions) {
		this.expressions = expressions;
	}

	protected IASTexpression[] expressions;

	@Override
	public IASTexpression[] getExpressions() {
		try {
			return getAllButLastInstructions();
		} catch (EvaluationException e) {
			return null;
		}

	}

	@Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			IASTvisitor<Result, Data, Anomaly> visitor, Data data)
			throws Anomaly {
		return visitor.visit(this, data);
	}

	@Override
	public IASTexpression[] getAllButLastInstructions()
			throws EvaluationException {
		IASTexpression[] newarray = new IASTexpression[expressions.length - 1];
		for (int i = 0; i < newarray.length; i++)
			newarray[i] = expressions[i];
		return newarray;
	}

}
