package com.paracamplus.ilp3.tme7;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public class ASTfactory extends com.paracamplus.ilp3.ast.ASTfactory implements IParserFactory {

	@Override
	public ASTcostart newCostart(IASTexpression function,
			IASTexpression[] arguments) {
		return new ASTcostart(function, arguments);
	}

}
