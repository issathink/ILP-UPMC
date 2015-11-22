package com.paracamplus.ilp3.tme7;

import java.io.Writer;

import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;

public class GlobalVariableStuff extends com.paracamplus.ilp3.interpreter.GlobalVariableStuff {

	public static void fillGlobalVariables (IGlobalVariableEnvironment env,
            Writer out ) {
		
		env.addGlobalVariableValue(new Yield("yield"));
		env.addGlobalVariableValue(new Resume("resume"));

	}
	
}
