package com.paracamplus.ilp1.ilp1tme4.bete.compiler;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public interface INormalizationFactory extends com.paracamplus.ilp1.compiler.normalizer.INormalizationFactory {

	IASTexpression newAMoinsQue(IASTexpression condition,
            IASTexpression consequence);
	
}
