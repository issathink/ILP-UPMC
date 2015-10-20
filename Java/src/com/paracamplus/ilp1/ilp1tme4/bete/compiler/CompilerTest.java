package com.paracamplus.ilp1.ilp1tme4.bete.compiler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.paracamplus.ilp1.compiler.GlobalVariableEnvironment;
import com.paracamplus.ilp1.compiler.GlobalVariableStuff;
import com.paracamplus.ilp1.compiler.OperatorEnvironment;
import com.paracamplus.ilp1.compiler.OperatorStuff;
import com.paracamplus.ilp1.compiler.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.compiler.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp1.compiler.optimizer.IdentityOptimizer;
import com.paracamplus.ilp1.ilp1tme4.bete.ASTfactory;
import com.paracamplus.ilp1.ilp1tme4.bete.IParserAMoinsQueFactory;
import com.paracamplus.ilp1.ilp1tme4.bete.ParserAMoinsQue;
import com.paracamplus.ilp1.interfaces.IASTprogram;
import com.paracamplus.ilp1.parser.IParser;
import com.paracamplus.ilp1.tools.FileTool;
import com.paracamplus.ilp1.tools.Input;
import com.paracamplus.ilp1.tools.InputFromFile;
import com.paracamplus.ilp1.tools.ProgramCaller;



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
		// String executionPrinting = pc.getStdout().trim();
		// checkPrintingAndResult(executionPrinting);
	}
	
	public static File changeSuffix(File file, String suffix) {
        String parent = file.getParent();
        String name = file.getName();
        String basename;
        int dotIndex = name.lastIndexOf('.');
        if (dotIndex >= 0) {
            basename = name.substring(0, dotIndex);
        } else {
            basename = name;
        }
        String newName = parent + File.separator + basename + '.' + suffix;
        return new File(newName);
    }

}
