package com.paracamplus.ilp1.ilp1tme4.normalizer;

import com.paracamplus.ilp1.ilp1tme3.VectorInterpreterTest;
import com.paracamplus.ilp1.parser.IParserFactory;

public class NormalizerTest extends VectorInterpreterTest {

	public NormalizerTest() {
		rngFileName = "Grammars/tme4.rng";
		IParserFactory factory = new ParserUnlessFactory();
		parser = new MyParser(factory);
	}

}
