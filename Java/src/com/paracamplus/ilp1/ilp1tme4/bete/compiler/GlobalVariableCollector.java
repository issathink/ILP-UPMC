package com.paracamplus.ilp1.ilp1tme4.bete.compiler;

import java.util.Set;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.interfaces.IASTCglobalVariable;
import com.paracamplus.ilp1.ilp1tme4.bete.IASTaMoinsQue;

public class GlobalVariableCollector extends
		com.paracamplus.ilp1.compiler.GlobalVariableCollector
		implements
		IASTCvisitor<Set<IASTCglobalVariable>, Set<IASTCglobalVariable>, CompilationException> {

	public GlobalVariableCollector() {
		super();
	}

	public Set<IASTCglobalVariable> visit(IASTaMoinsQue iast,
			Set<IASTCglobalVariable> result) throws CompilationException {
		result = iast.getCondition().accept(this, result);
		result = iast.getConsequence().accept(this, result);
		return result;
	}

}
