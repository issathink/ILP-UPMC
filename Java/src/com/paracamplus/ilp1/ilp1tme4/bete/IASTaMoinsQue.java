package com.paracamplus.ilp1.ilp1tme4.bete;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public interface IASTaMoinsQue extends IASTexpression {

	IASTexpression getCondition();

	IASTexpression getConsequence();

}
