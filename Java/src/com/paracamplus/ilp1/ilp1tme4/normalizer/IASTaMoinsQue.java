package com.paracamplus.ilp1.ilp1tme4.normalizer;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public interface IASTaMoinsQue extends IASTexpression {

	public IASTexpression getCondition();

	public IASTexpression getConsequence();

}
