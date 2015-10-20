package com.paracamplus.ilp1.ilp1tme4.bete;

import org.w3c.dom.Element;

import com.paracamplus.ilp1.ast.Parser;
import com.paracamplus.ilp1.interfaces.IAST;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.parser.ParseException;

public class ParserAMoinsQue extends Parser {

	public ParserAMoinsQue(IParserAMoinsQueFactory factory) {
		super(factory);
		addMethod("aMoinsQue", ParserAMoinsQue.class);
	}

	public IASTexpression aMoinsQue(Element e) throws ParseException {
		IAST iastc = findThenParseChildContent(e, "condition");
		IASTexpression condition = narrowToIASTexpression(iastc);
		IASTexpression[] iaste = findThenParseChildAsExpressions(e,
				"consequence");
		IASTexpression consequence = getFactory().newSequence(iaste);

		return ((IParserAMoinsQueFactory) getFactory()).newAMoinsQue(condition,
				consequence);
	}

}
