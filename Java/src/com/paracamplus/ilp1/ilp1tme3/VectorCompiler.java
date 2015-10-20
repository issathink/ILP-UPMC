package com.paracamplus.ilp1.ilp1tme3;

import com.paracamplus.ilp1.compiler.Compiler;
import com.paracamplus.ilp1.compiler.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.compiler.interfaces.IOperatorEnvironment;

public class VectorCompiler extends Compiler {

	public VectorCompiler(IOperatorEnvironment ioe,
			IGlobalVariableEnvironment igve) {
		super(ioe, igve);
		cProgramPrefix= "#include <stdio.h> \n"
	            + "#include <stdlib.h> \n"
	            + "#include \"ilp.h\" \n\n"
	            + "#include \"ilptme3.h\" \n\n";
	}

}
