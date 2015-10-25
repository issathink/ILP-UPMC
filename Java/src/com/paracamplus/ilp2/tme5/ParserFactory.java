package com.paracamplus.ilp2.tme5;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp2.ast.ASTfactory;

public class ParserFactory  extends ASTfactory implements IParserFactory{

	@Override
	public IASTexpression newSuivant() {
		return new ASTsuivant();
	}

	@Override
	public IASTexpression newDernier() {
		return new ASTdernier();
	}

}
