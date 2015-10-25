package com.paracamplus.ilp2.tme5.partie2;

import org.w3c.dom.Element;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.Inamed;
import com.paracamplus.ilp1.parser.ParseException;

public class Parser extends com.paracamplus.ilp2.tme5.Parser {

	public Parser(IParserFactory factory) {
		super(factory);
	}

	@Override
	public IASTexpression suivant(Element e) throws ParseException {
		Inamed etiquette = new ASTnamed(e.getAttribute("name"));
		return ((ParserFactory) getFactory()).newSuivant(etiquette);
	}

	@Override
	public IASTexpression dernier(Element e) throws ParseException {
		Inamed etiquette = new ASTnamed(e.getAttribute("name"));
		return ((ParserFactory) getFactory()).newDernier(etiquette);
	}

	public IASTexpression loop(Element e) throws ParseException {
		IASTexpression condition = narrowToIASTexpression(findThenParseChildContent(
				e, "condition"));
		IASTexpression[] expressions = findThenParseChildAsExpressions(e,
				"body");
		IASTexpression body = getFactory().newSequence(expressions);
		Inamed etiquette = new ASTnamed(e.getAttribute("name"));
		return ((IParserFactory) getFactory()).newLoop(condition, body,
				etiquette);
	}

}
