package com.paracamplus.ilp1.ilp1tme4.bete.compiler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collection;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

import com.paracamplus.ilp1.ilp1tme4.bete.ASTfactory;
import com.paracamplus.ilp1.ilp1tme4.bete.IParserAMoinsQueFactory;
import com.paracamplus.ilp1.ilp1tme4.bete.ParserAMoinsQue;
import com.paracamplus.ilp1.parser.IParser;



public class CompilerTest {

	protected static String rngFileName = "Grammars/tme4.rng";
	protected static String samplesDirName = "Samples/Scheme";
	protected static String pattern = "aaa";

	public CompilerTest() {
		
		IParserAMoinsQueFactory factory = new ASTfactory();
		this.parser = new ParserAMoinsQue(factory);
	}


	protected IParser parser;

	@Test
	public void processFile() throws Throwable {
		File file = new File("Samples/Scheme/aaa.xml");
		System.err.println("Testing " + file.getAbsolutePath() + " ...");
		assertTrue(file.exists());
		Input input = new InputFromFile(file);
		parser.setInput(input);
		File rngFile = new File(rngFileName);
		parser.setGrammar(rngFile);
		IASTprogram program = parser.getProgram();

		IOperatorEnvironment ioe = new OperatorEnvironment();
		OperatorStuff.fillUnaryOperators(ioe);
		OperatorStuff.fillBinaryOperators(ioe);
		IGlobalVariableEnvironment gve = new GlobalVariableEnvironment();
		GlobalVariableStuff.fillGlobalVariables(gve);
		Compiler compiler = new Compiler(ioe, gve);
		compiler.setOptimizer(new IdentityOptimizer());
		String compiled = compiler.compile(program);
		File cFile = changeSuffix(file, "c");
		FileTool.stuffFile(cFile, compiled);

		try {
			String indentProgram = "indent " + cFile.getAbsolutePath();
			ProgramCaller pcindent = new ProgramCaller(indentProgram);
			pcindent.run();
			System.out.println(FileTool.slurpFile(cFile));
		} catch (Exception exc) {
			// program 'indent' is probably absent, ignore!
			System.out.println(compiled);
		}

		String compileProgram = "bash C/compileThenRun.sh +gc "
				+ cFile.getAbsolutePath();
		ProgramCaller pc = new ProgramCaller(compileProgram);
		pc.setVerbose();
		pc.run();
		assertEquals("Comparing return code", 0, pc.getExitValue());
		String executionPrinting = pc.getStdout().trim();
		// checkPrintingAndResult(executionPrinting);
	}

}
