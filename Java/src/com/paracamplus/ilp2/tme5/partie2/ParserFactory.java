package com.paracamplus.ilp2.tme5.partie2;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.Inamed;

public class ParserFactory extends com.paracamplus.ilp2.tme5.ParserFactory
		implements IParserFactory {

	@Override
	public IASTexpression newSuivant(Inamed etiquette) {
		return new ASTsuivant(etiquette);
	}

	@Override
	public IASTexpression newDernier(Inamed etiquette) {
		return new ASTdernier(etiquette);
	}

	@Override
	public IASTexpression newLoop(IASTexpression condition,
			IASTexpression body, Inamed etiquette) {
		return new ASTloop(condition, body, etiquette);
	}

}
