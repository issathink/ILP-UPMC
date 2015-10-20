package com.paracamplus.ilp1.ilp1tme4.bete.compiler;

import com.paracamplus.ilp1.ilp1tme4.bete.IASTaMoinsQue;
import com.paracamplus.ilp1.ilp1tme4.bete.IASTvisitorAMoinsQue;

public interface IASTCvisitor<Result, Data, Anomaly extends Throwable>
		extends IASTvisitorAMoinsQue<Result, Data, Anomaly> {
	
	Result visit(IASTaMoinsQue iast, Data data) throws Anomaly;

}
