package com.paracamplus.ilp1.ilp1tme4.bete;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public class ASTfactory extends com.paracamplus.ilp1.ast.ASTfactory implements
		IParserAMoinsQueFactory {

	@Override
	public IASTexpression newAMoinsQue(IASTexpression condition,
			IASTexpression consequence) {
		return new ASTaMoinsQue(condition, consequence);
	}

}
