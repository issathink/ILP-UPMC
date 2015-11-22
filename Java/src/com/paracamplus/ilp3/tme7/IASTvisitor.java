package com.paracamplus.ilp3.tme7;

public interface IASTvisitor<Result, Data, Anomaly extends Throwable> extends
		com.paracamplus.ilp3.interfaces.IASTvisitor<Result, Data, Anomaly> {

	Result visit(ASTcostart iast, Data data) throws Anomaly;

}
