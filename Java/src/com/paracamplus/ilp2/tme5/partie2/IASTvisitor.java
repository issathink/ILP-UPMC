package com.paracamplus.ilp2.tme5.partie2;

public interface IASTvisitor<Result, Data, Anomaly extends Throwable> extends
		com.paracamplus.ilp2.tme5.IASTvisitor<Result, Data, Anomaly> {

	Result visit(IASTsuivant iast, Data data) throws Anomaly;
	Result visit(IASTloop iast, Data data) throws Anomaly;
	Result visit(IASTdernier iast, Data data) throws Anomaly;

}
