package com.paracamplus.ilp1.ilp1tme4.bete;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp1.interfaces.IASTexpression;

public class ASTaMoinsQue extends ASTexpression implements IASTaMoinsQue {

	private final IASTexpression condition;
	private final IASTexpression consequence;

	public ASTaMoinsQue(IASTexpression condition, IASTexpression consequence) {
		this.condition = condition;
		this.consequence = consequence;
	}

	@Override
	public IASTexpression getCondition() {
		return condition;
	}

	@Override
	public IASTexpression getConsequence() {
		return consequence;
	}

	@Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor, Data data)
			throws Anomaly {
		return this.accept((IASTvisitorAMoinsQue<Result,Data,Anomaly>)visitor, data);
	}
	
	public <Result, Data, Anomaly extends Throwable> Result accept(
			IASTvisitorAMoinsQue<Result,Data,Anomaly> visitor, Data data)
			throws Anomaly {
		return visitor.visit(this, data);
	}

}
