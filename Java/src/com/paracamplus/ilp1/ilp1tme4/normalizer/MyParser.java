package com.paracamplus.ilp1.ilp1tme4.normalizer;

import org.w3c.dom.Element;

import com.paracamplus.ilp1.ast.Parser;
import com.paracamplus.ilp1.interfaces.IAST;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.parser.IParserFactory;
import com.paracamplus.ilp1.parser.ParseException;

public class MyParser extends Parser {

	public MyParser(IParserFactory	 factory) {
		super(factory);
		addMethod("aMoinsQue", MyParser.class);

	}

	public IASTexpression aMoinsQue(Element e) throws ParseException {
		IAST iastc = findThenParseChildContent(e, "condition");
		IASTexpression condition = narrowToIASTexpression(iastc);
		IASTexpression[] iaste = findThenParseChildAsExpressions(e,
				"consequence");
		IASTexpression consequence = getFactory().newSequence(iaste);
		ParserUnlessFactory fac = (ParserUnlessFactory) getFactory();
		return fac.newAMoinsQue(condition, consequence);

	}
}
