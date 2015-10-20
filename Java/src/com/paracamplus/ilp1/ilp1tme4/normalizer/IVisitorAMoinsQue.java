package com.paracamplus.ilp1.ilp1tme4.normalizer;

import com.paracamplus.ilp1.interfaces.IASTvisitor;

public interface IVisitorAMoinsQue<Result, Data, Anomaly extends Throwable>
		extends IASTvisitor<Result, Data, Anomaly> {

	Result visit(IASTaMoinsQue iast, Data data) throws Anomaly;

}
