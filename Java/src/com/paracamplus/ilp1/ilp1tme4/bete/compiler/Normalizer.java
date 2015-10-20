package com.paracamplus.ilp1.ilp1tme4.bete.compiler;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.normalizer.INormalizationEnvironment;
import com.paracamplus.ilp1.ilp1tme4.bete.IASTaMoinsQue;
import com.paracamplus.ilp1.ilp1tme4.bete.IASTvisitorAMoinsQue;
import com.paracamplus.ilp1.interfaces.IASTexpression;

public class Normalizer extends
		com.paracamplus.ilp1.compiler.normalizer.Normalizer
		implements
		IASTvisitorAMoinsQue<IASTexpression, INormalizationEnvironment, CompilationException> {

	public Normalizer(INormalizationFactory factory) {
		super(factory);
	}

	@Override
	public IASTexpression visit(IASTaMoinsQue iast,
			INormalizationEnvironment data) throws CompilationException {
		IASTexpression c = iast.getCondition().accept(this, data);
		IASTexpression t = iast.getConsequence().accept(this, data);
		return ((INormalizationFactory) factory).newAMoinsQue(c, t);
	}

}
