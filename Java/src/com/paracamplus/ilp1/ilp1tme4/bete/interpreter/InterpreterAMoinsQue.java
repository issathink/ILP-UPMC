package com.paracamplus.ilp1.ilp1tme4.bete.interpreter;

import com.paracamplus.ilp1.ilp1tme4.bete.IASTaMoinsQue;
import com.paracamplus.ilp1.ilp1tme4.bete.IASTvisitorAMoinsQue;
import com.paracamplus.ilp1.interpreter.Interpreter;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;

public class InterpreterAMoinsQue extends Interpreter implements
		IASTvisitorAMoinsQue<Object, ILexicalEnvironment, EvaluationException> {

	public InterpreterAMoinsQue(
			IGlobalVariableEnvironment globalVariableEnvironment,
			IOperatorEnvironment operatorEnvironment) {
		super(globalVariableEnvironment, operatorEnvironment);
	}

	private static Object whatever = "whatever";

	@Override
	public Object visit(IASTaMoinsQue iast, ILexicalEnvironment data)
			throws EvaluationException {
		Object c = iast.getCondition().accept(this, data);
		if (c != null && c instanceof Boolean) {
			Boolean b = (Boolean) c;
			if (! b.booleanValue()) {
				return iast.getConsequence().accept(this, data);
			} else {
				return whatever;
			}
		} else {
			return iast.getConsequence().accept(this, data);
		}
	}

}
