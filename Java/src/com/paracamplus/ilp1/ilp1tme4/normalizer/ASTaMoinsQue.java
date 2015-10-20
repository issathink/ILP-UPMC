package com.paracamplus.ilp1.ilp1tme4.normalizer;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp1.interfaces.IASTexpression;

public class ASTaMoinsQue extends ASTexpression implements IASTaMoinsQue {

	public ASTaMoinsQue(IASTexpression condition, IASTexpression consequence) {
		this.condition = condition;
		this.consequence = consequence;
	}

	private final IASTexpression condition;
	private final IASTexpression consequence;

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
		IVisitorAMoinsQue<Result, Data, Anomaly> amoin = null;
		try {
			System.out.println(visitor.getClass());
		amoin = (IVisitorAMoinsQue<Result,Data,Anomaly>)visitor;
		} catch(ClassCastException e) {
			System.out.println("what!!!!!!");
		}
		
		return this.accept(amoin, data);
	}
	
	//@Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			IVisitorAMoinsQue<Result,Data,Anomaly> visitor, Data data)
			throws Anomaly {
		return visitor.visit(this, data);
	}

}
