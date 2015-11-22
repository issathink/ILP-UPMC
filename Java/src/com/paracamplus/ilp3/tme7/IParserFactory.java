package com.paracamplus.ilp3.tme7;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public interface IParserFactory extends com.paracamplus.ilp3.parser.IParserFactory {

	ASTcostart newCostart(IASTexpression function, IASTexpression[] arguments);
	
}
