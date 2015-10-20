package com.paracamplus.ilp1.ilp1tme4.normalizer;

import com.paracamplus.ilp1.compiler.normalizer.NormalizationFactory;
import com.paracamplus.ilp1.interfaces.IASTexpression;

public class NormalizerFactory extends NormalizationFactory{

	public IASTexpression newAMoinsQue(IASTexpression condition,
			IASTexpression consequence) {
		return new ASTaMoinsQue(condition, consequence);
	}

}

