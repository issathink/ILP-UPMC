package com.paracamplus.ilp1.ilp1tme2;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

import com.paracamplus.ilp1.interfaces.IASTprogram;
import com.paracamplus.ilp1.interpreter.EmptyLexicalEnvironment;
import com.paracamplus.ilp1.interpreter.GlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.GlobalVariableStuff;
import com.paracamplus.ilp1.interpreter.OperatorEnvironment;
import com.paracamplus.ilp1.interpreter.OperatorStuff;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp1.parser.IParser;
import com.paracamplus.ilp1.parser.IParserFactory;
import com.paracamplus.ilp1.parser.ParseException;
import com.paracamplus.ilp1.tools.Input;
import com.paracamplus.ilp1.tools.InputFromFile;

public class CountingTest implements ICountingConstantsProcess {

	protected static String rngFileName = "Grammars/grammar1.rng";
	protected static String samplesDirName = "Samples";
	protected static String pattern = "u01-01";

	public CountingTest() {
		IParserFactory factory = new com.paracamplus.ilp1.ast.ASTfactory();
		this.parser = new NewDOMParser(factory);
	}

	public void setParser(IParser parser) {
		this.parser = parser;
	}

	protected IParser parser;

	@Test
	public void processFile() throws Throwable { // Ouvre le fichier XML,
													// choisis la grammaire,
													// valide la syntaxe, parse
													// cree l'ast, interptre
													// cree le programme
		File file = new File("Samples/Scheme/aaa.xml");
		System.err.println("Testing " + file.getAbsolutePath() + " ...");
		assertTrue(file.exists());
		Input input = new InputFromFile(file);
		parser.setInput(input);
		File rngFile = new File(rngFileName);
		parser.setGrammar(rngFile);
		IASTprogram program = parser.getProgram();

		IGlobalVariableEnvironment gve = new GlobalVariableEnvironment();
		stdout = new StringWriter();
		GlobalVariableStuff.fillGlobalVariables(gve, stdout);
		IOperatorEnvironment oe = new OperatorEnvironment();
		OperatorStuff.fillUnaryOperators(oe);
		OperatorStuff.fillBinaryOperators(oe);
		VisitorCountConstant interpreter = new VisitorCountConstant(gve, oe);
		ILexicalEnvironment lexenv = new EmptyLexicalEnvironment();
		Object value = interpreter.visit(program, lexenv);
		String printing = stdout.toString();
		System.out.println("  Value: " + value);
		if (!"".equals(printing)) {
			System.out.println("  Printing: " + printing);
		}
	}

	protected Writer stdout;

	protected static Object normalizeResult(Object value) {
		if (value instanceof BigInteger) {
			return ((BigInteger) value).intValue();
		} else if (value instanceof BigDecimal) {
			return ((BigDecimal) value).doubleValue();
		} else {
			return value;
		}
	}

	@Override
	public int getNbConstantesDOM() {

		File file = new File("Samples/Scheme/aaa.xml");
		System.err.println("Testing " + file.getAbsolutePath() + " ...");
		assertTrue(file.exists());
		Input input = new InputFromFile(file);
		parser.setInput(input);
		File rngFile = new File(rngFileName);
		parser.setGrammar(rngFile);
		try {
			parser.getProgram();
			
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return 0;
	}

	//Creer des fichiers .cnt de test et une methode CheckCount qui verifie si le retour du parsing = .cnt 
	//Dans processfile on utilise getNbAST/DOM pour calculer les valeurs
	//Dans getNBconstanteDOM on utilise le new Parser
	@Override
	public int getNbConstantesAST() throws EvaluationException {
		File file = new File("Samples/Scheme/aaa.xml");
		System.err.println("Testing " + file.getAbsolutePath() + " ...");
		assertTrue(file.exists());
		Input input = new InputFromFile(file);
		parser.setInput(input);
		File rngFile = new File(rngFileName);
		parser.setGrammar(rngFile);
		IASTprogram program;
		try {
			program = parser.getProgram();

			IGlobalVariableEnvironment gve = new GlobalVariableEnvironment();
			stdout = new StringWriter();
			GlobalVariableStuff.fillGlobalVariables(gve, stdout);
			IOperatorEnvironment oe = new OperatorEnvironment();
			OperatorStuff.fillUnaryOperators(oe);
			OperatorStuff.fillBinaryOperators(oe);
			VisitorCountConstant interpreter = new VisitorCountConstant(gve, oe);
			ILexicalEnvironment lexenv = new EmptyLexicalEnvironment();
			Object value = interpreter.visit(program, lexenv);
			String printing = stdout.toString();
			System.out.println("  Value: " + value);
			if (!"".equals(printing)) {
				System.out.println("  Printing: " + printing);
			}

			return (int) value;
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (EvaluationException e) {
			e.printStackTrace();
		}

		return 0;
	}

}
