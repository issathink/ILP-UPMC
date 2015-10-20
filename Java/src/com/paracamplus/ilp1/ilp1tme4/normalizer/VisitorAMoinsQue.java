package com.paracamplus.ilp1.ilp1tme4.normalizer;

import com.paracamplus.ilp1.ast.ASToperator;
import com.paracamplus.ilp1.ast.ASTunaryOperation;
import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.normalizer.INormalizationEnvironment;
import com.paracamplus.ilp1.compiler.normalizer.INormalizationFactory;
import com.paracamplus.ilp1.compiler.normalizer.Normalizer;
import com.paracamplus.ilp1.interfaces.IASTexpression;

public class VisitorAMoinsQue extends Normalizer implements  IVisitorAMoinsQue<IASTexpression, INormalizationEnvironment, CompilationException> {
	public VisitorAMoinsQue(INormalizationFactory factory) {
        super(factory);
      
    }
	public IASTexpression visit(IASTaMoinsQue iast,INormalizationEnvironment env) throws CompilationException {
		IASTexpression c = iast.getCondition().accept(this, env);
		c = new ASTunaryOperation(new ASToperator("!"), c);
		IASTexpression t = iast.getConsequence().accept(this, env);
		return factory.newAlternative(c, t, null);
	}
	
}
