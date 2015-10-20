package com.paracamplus.ilp1.ilp1tme4.dom;

import org.w3c.dom.Element;

import com.paracamplus.ilp1.ast.ASToperator;
import com.paracamplus.ilp1.ast.ASTunaryOperation;
import com.paracamplus.ilp1.ast.Parser;
import com.paracamplus.ilp1.interfaces.IAST;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.parser.IParserFactory;
import com.paracamplus.ilp1.parser.ParseException;

public class ParserUnless extends Parser {

	public ParserUnless(IParserFactory factory) {
		super(factory);
		addMethod("aMoinsQue", ParserUnless.class);
	}

	public IASTexpression aMoinsQue(Element e) throws ParseException {
		IAST iastc = findThenParseChildContent(e, "condition");
		IASTexpression condition = narrowToIASTexpression(iastc);
		condition = new ASTunaryOperation(new ASToperator("!"), condition);
		IASTexpression[] iaste = findThenParseChildAsExpressions(e,
				"consequence");
		IASTexpression consequence = getFactory().newSequence(iaste);

		return getFactory().newAlternative(condition, consequence, null);

	}

}
