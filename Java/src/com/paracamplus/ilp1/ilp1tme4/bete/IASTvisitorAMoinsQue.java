package com.paracamplus.ilp1.ilp1tme4.bete;

import com.paracamplus.ilp1.interfaces.IASTvisitor;

public interface IASTvisitorAMoinsQue<Result, Data, Anomaly extends Throwable>
		extends IASTvisitor<Result, Data, Anomaly> {
	
	Result visit(IASTaMoinsQue iast, Data data) throws Anomaly;

}
