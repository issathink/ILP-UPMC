
package com.paracamplus.ilp1.ilp1tme3;

import com.paracamplus.ilp1.compiler.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.compiler.interfaces.IOperatorEnvironment;

public class CompilerTme3 extends com.paracamplus.ilp1.compiler.Compiler {
	public CompilerTme3 (IOperatorEnvironment ioe,IGlobalVariableEnvironment igve ) {
		super(ioe, igve);
		cProgramPrefix= "#include <stdio.h> \n"
	            + "#include <stdlib.h> \n"
	            + "#include \"ilp.h\" \n\n"
	            + "#include \"ilptme3.h\" \n\n";
	}
}