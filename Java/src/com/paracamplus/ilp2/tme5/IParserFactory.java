package com.paracamplus.ilp2.tme5;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public interface IParserFactory extends
		com.paracamplus.ilp2.parser.IParserFactory {

	public IASTexpression newSuivant();

	public IASTexpression newDernier();

}
