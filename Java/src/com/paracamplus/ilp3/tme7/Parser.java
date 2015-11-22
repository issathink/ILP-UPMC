package com.paracamplus.ilp3.tme7;

import org.w3c.dom.Element;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.parser.ParseException;

public class Parser extends com.paracamplus.ilp3.ast.Parser {

	public Parser(IParserFactory factory) {
		super(factory);
		addMethod("costart", Parser.class);
	}

	public IASTexpression costart(Element e) throws ParseException {

		System.out.println("Parser costart");
		IASTexpression fun = narrowToIASTexpression(findThenParseChildContent(
				e, "function"));
		IASTexpression[] iasts = findThenParseChildAsExpressions(e, "arguments");
		return ((IParserFactory) getFactory()).newCostart(fun, iasts);

	}

}
