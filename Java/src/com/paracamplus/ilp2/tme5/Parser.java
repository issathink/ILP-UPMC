package com.paracamplus.ilp2.tme5;

import org.w3c.dom.Element;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.parser.ParseException;

public class Parser extends com.paracamplus.ilp2.ast.Parser {

	public Parser(IParserFactory factory) {
		super(factory);
		addMethod("suivant", Parser.class);
		addMethod("dernier", Parser.class);
	}

	public IASTexpression suivant(Element e) throws ParseException {

		return ((ParserFactory) getFactory()).newSuivant();
	}

	public IASTexpression dernier(Element e) throws ParseException {

		return ((ParserFactory) getFactory()).newDernier();
	}

}
