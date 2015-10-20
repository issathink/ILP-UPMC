package com.paracamplus.ilp1.ilp1tme4.normalizer;

import com.paracamplus.ilp1.ast.ASTfactory;
import com.paracamplus.ilp1.interfaces.IASTexpression;

public class ParserUnlessFactory extends ASTfactory   {


	public IASTexpression newAMoinsQue(IASTexpression condition,
			IASTexpression consequence) {
		return new ASTaMoinsQue(condition, consequence);
	}

}
