/* *****************************************************************
 * ILP9 - Implantation d'un langage de programmation.
 * by Christian.Queinnec@paracamplus.com
 * See http://mooc.paracamplus.com/ilp9
 * GPL version 3
 ***************************************************************** */
package com.paracamplus.ilp2.tme5;



import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FilenameFilter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

import com.paracamplus.ilp1.interpreter.EmptyLexicalEnvironment;
import com.paracamplus.ilp1.interpreter.GlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.GlobalVariableStuff;
import com.paracamplus.ilp1.interpreter.OperatorEnvironment;
import com.paracamplus.ilp1.interpreter.OperatorStuff;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp1.tools.Input;
import com.paracamplus.ilp1.tools.InputFromFile;
import com.paracamplus.ilp2.interfaces.IASTprogram;
import com.paracamplus.ilp2.tme5.interpreter.Interpreter;



public class InterpreterTest extends com.paracamplus.ilp1.interpreter.test.InterpreterTest{
    
	protected static String rngFileName = "Grammars/grammar-tme5.rng";
    protected static String samplesDirName = "Samples/Scheme"; 
    protected static String pattern = "aa";
    public InterpreterTest(final File file) {
        super (file);
        ParserFactory factory =  new ParserFactory();
        this.parser = new Parser(factory);

    }
    
    @Override
	@Test
    public void processFile () throws Throwable {
        System.err.println("Testing " + file.getAbsolutePath() + " ...");
        assertTrue(file.exists());
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
		Object value = null;
		try {
			value = interpreter.visit(program, lexenv);

			String printing = stdout.toString();
			System.out.println("  Value: " + value);
			if (!"".equals(printing)) {
				System.out.println("  Printing: " + printing);
			}
			// checkResult(value);
			// checkPrinting(printing);
		} catch (DernierException | SuivantException e) {
			System.out
					.println("Stop doing shit man : "
							+ e.getMessage());
		}
    }
    
    @Parameters(name = "{0}")
    public static Collection<File[]> data() throws Exception {
        //Path currentRelativePath = Paths.get("");
        //String s = currentRelativePath.toAbsolutePath().toString();
        //System.err.println("Current relative path is: " + s);
        
        final Pattern p = Pattern.compile("^" + pattern + ".xml$");
        final FilenameFilter ff = new FilenameFilter() {
            @Override
			public boolean accept (File dir, String name) {
                final Matcher m = p.matcher(name);
                return m.matches();
            }
        };
        File samplesDir = new File(samplesDirName);
        final File[] testFiles = samplesDir.listFiles(ff);
        assertNotNull(testFiles);
        
        if ( testFiles.length == 0 ) {
            final String msg = "Cannot find a single test like " + pattern;
            throw new RuntimeException(msg);
        }

        // Old way before Java8:
//        java.util.Arrays.sort(testFiles,
//                new java.util.Comparator<java.io.File>() {
//            public int compare (java.io.File f1, java.io.File f2) {
//                return f1.getName().compareTo(f2.getName());
//            }
//        });
        java.util.Arrays.sort(testFiles,
                (f1, f2) -> f1.getName().compareTo(f2.getName()));

       Collection<File[]> result = new Vector<>();
        for ( final File f : testFiles ) {
            result.add(new File[]{ f });
        }
        return result;
    }
}
