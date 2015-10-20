package com.paracamplus.ilp1.ilp1tme4.bete.compiler;

import com.paracamplus.ilp1.ilp1tme4.bete.ASTaMoinsQue;
import com.paracamplus.ilp1.interfaces.IASTexpression;

public class NormalizationFactory extends
		com.paracamplus.ilp1.compiler.normalizer.NormalizationFactory implements
		INormalizationFactory {

	@Override
	public IASTexpression newAMoinsQue(IASTexpression condition,
			IASTexpression consequence) {
		return new ASTaMoinsQue(condition, consequence);
	}

}
