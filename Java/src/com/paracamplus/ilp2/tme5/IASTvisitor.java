package com.paracamplus.ilp2.tme5;

public interface IASTvisitor<Result, Data, Anomaly extends Throwable> extends
		com.paracamplus.ilp2.interfaces.IASTvisitor<Result, Data, Anomaly> {
	
	Result visit(IASTsuivant iast, Data data) throws Anomaly;

	Result visit(IASTdernier iast, Data data) throws Anomaly;
}
