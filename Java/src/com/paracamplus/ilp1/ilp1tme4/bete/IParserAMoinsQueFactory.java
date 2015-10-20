package com.paracamplus.ilp1.ilp1tme4.bete;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.parser.IParserFactory;

public interface IParserAMoinsQueFactory extends IParserFactory {

	IASTexpression newAMoinsQue(IASTexpression condition,
			IASTexpression consequence);
	
}
