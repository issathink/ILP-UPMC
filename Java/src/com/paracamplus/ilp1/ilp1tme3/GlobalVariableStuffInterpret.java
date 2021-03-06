package com.paracamplus.ilp1.ilp1tme3;

import java.io.Writer;
import java.math.BigDecimal;

import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.primitive.Newline;
import com.paracamplus.ilp1.interpreter.primitive.Print;

public class GlobalVariableStuffInterpret {
	
	public static void fillGlobalVariables (
            IGlobalVariableEnvironment env,
            Writer out ) {
        env.addGlobalVariableValue("pi", new BigDecimal("3.1415926535"));
        env.addGlobalVariableValue(new Print(out));
        env.addGlobalVariableValue(new Newline(out));
        env.addGlobalVariableValue(new Sinus("sin"));
        env.addGlobalVariableValue(new MakeVectorPrimitive("make_vector"));
        env.addGlobalVariableValue(new VectorLengthPrimitive("vector_length"));
        env.addGlobalVariableValue(new VectorGetPrimitive("vector_get"));
    }

}
