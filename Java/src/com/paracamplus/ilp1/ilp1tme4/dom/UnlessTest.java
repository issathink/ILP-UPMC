package com.paracamplus.ilp1.ilp1tme4.dom;

import com.paracamplus.ilp1.ast.ASTfactory;
import com.paracamplus.ilp1.ilp1tme3.VectorInterpreterTest;
import com.paracamplus.ilp1.parser.IParserFactory;

public class UnlessTest extends VectorInterpreterTest {

	public UnlessTest() {
		rngFileName = "Grammars/tme4.rng";
		IParserFactory factory = new ASTfactory();
		parser = new ParserUnless(factory);
	}

}
