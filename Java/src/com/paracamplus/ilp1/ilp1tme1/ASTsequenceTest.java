package com.paracamplus.ilp1.ilp1tme1;

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
import com.paracamplus.ilp1.interpreter.Interpreter;
import com.paracamplus.ilp1.interpreter.OperatorEnvironment;
import com.paracamplus.ilp1.interpreter.OperatorStuff;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp1.parser.IParser;
import com.paracamplus.ilp1.parser.IParserFactory;
import com.paracamplus.ilp1.tools.Input;
import com.paracamplus.ilp1.tools.InputFromFile;

public class ASTsequenceTest {
	
    protected static String rngFileName = "Grammars/grammar1.rng";
    protected static String samplesDirName = "Samples"; 
    protected static String pattern = "aaa";
    
    
    public ASTsequenceTest() {
        IParserFactory factory = new ASTfactory();
        this.parser = new com.paracamplus.ilp1.ast.Parser(factory);
    }
    
    
    
    public void setParser (IParser parser) {
        this.parser = parser;
    }
    protected IParser parser;
    
    @Test
    public void processFile () throws Throwable { // Ouvre le fichier XML, choisis la grammaire, valide la syntaxe, parse cree l'ast, interptre cree le programme
    	File file=new File("Samples/Scheme/aaa.xml");
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
        Interpreter interpreter = new Interpreter(gve, oe);
        ILexicalEnvironment lexenv = new EmptyLexicalEnvironment();
        Object value = interpreter.visit(program, lexenv);
        String printing = stdout.toString();
        System.out.println("  Value: " + value);
        if ( ! "".equals(printing) ) {
            System.out.println("  Printing: " + printing);
        }
        //checkResult(value);
        //checkPrinting(printing);
    }
    protected Writer stdout;
    
    
    protected static Object normalizeResult(Object value) {
        if (value instanceof BigInteger) {
            return ((BigInteger)value).intValue();
        } else if ( value instanceof BigDecimal ) {
            return ((BigDecimal)value).doubleValue();
        } else {
            return value;
        }
    }}
