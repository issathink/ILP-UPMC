package com.paracamplus.ilp3.tme7;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

import com.paracamplus.ilp1.interpreter.EmptyLexicalEnvironment;
import com.paracamplus.ilp1.interpreter.GlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.OperatorEnvironment;
import com.paracamplus.ilp1.interpreter.OperatorStuff;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp1.tools.Input;
import com.paracamplus.ilp1.tools.InputFromFile;
import com.paracamplus.ilp3.interfaces.IASTprogram;

public class InterpreterTest {

	protected static String rngFileName = "Grammars/grammar-tme7.rng";
	protected static String samplesDirName = "Samples";
	protected static String pattern = "ur?[0-8]\\d*-[123456]";

	public InterpreterTest() {
		IParserFactory factory = new ASTfactory();
		this.parser = new Parser(factory);
	}

	protected File file;
	protected Parser parser;

	@Test
	public void processFile() throws Throwable {
		// System.err.println("Testing " + file.getAbsolutePath() + " ...");
		// assertTrue(file.exists());
		file = new File("Samples/Scheme/a.xml");
		Input input = new InputFromFile(file);
		parser.setInput(input);
		File rngFile = new File(rngFileName);
		parser.setGrammar(rngFile);
		IASTprogram program = (IASTprogram) parser.getProgram();

		IGlobalVariableEnvironment gve = new GlobalVariableEnvironment();
		stdout = new StringWriter();
		GlobalVariableStuff.fillGlobalVariables(gve, stdout);
		IOperatorEnvironment oe = new OperatorEnvironment();
		OperatorStuff.fillUnaryOperators(oe);
		OperatorStuff.fillBinaryOperators(oe);
		Interpreter interpreter = new Interpreter(gve, oe);
		ILexicalEnvironment lexenv = new EmptyLexicalEnvironment();
		Object value = interpreter.visit(program, lexenv);
		
		String printing = stdout.toString();
		System.out.println("  Value: " + value);
		if (!"".equals(printing)) {
			System.out.println("  Printing: " + printing);
		}
	}

	private Writer stdout;

	protected static Object normalizeResult(Object value) {
		if (value instanceof BigInteger) {
			return ((BigInteger) value).intValue();
		} else if (value instanceof BigDecimal) {
			return ((BigDecimal) value).doubleValue();
		} else {
			return value;
		}
	}

}
