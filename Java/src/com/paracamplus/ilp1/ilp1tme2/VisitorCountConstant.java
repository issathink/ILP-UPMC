package com.paracamplus.ilp1.ilp1tme2;

import com.paracamplus.ilp1.interfaces.IASTalternative;
import com.paracamplus.ilp1.interfaces.IASTbinaryOperation;
import com.paracamplus.ilp1.interfaces.IASTblock;
import com.paracamplus.ilp1.interfaces.IASTblock.IASTbinding;
import com.paracamplus.ilp1.interfaces.IASTboolean;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTfloat;
import com.paracamplus.ilp1.interfaces.IASTinteger;
import com.paracamplus.ilp1.interfaces.IASTinvocation;
import com.paracamplus.ilp1.interfaces.IASToperator;
import com.paracamplus.ilp1.interfaces.IASTprogram;
import com.paracamplus.ilp1.interfaces.IASTsequence;
import com.paracamplus.ilp1.interfaces.IASTstring;
import com.paracamplus.ilp1.interfaces.IASTunaryOperation;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp1.interfaces.IASTvisitor;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;

public class VisitorCountConstant implements
		IASTvisitor<Object, ILexicalEnvironment, EvaluationException> {

	public VisitorCountConstant(
			IGlobalVariableEnvironment globalVariableEnvironment,
			IOperatorEnvironment operatorEnvironment) {
		this.globalVariableEnvironment = globalVariableEnvironment;
		this.operatorEnvironment = operatorEnvironment;
	}

	public Object visit(IASTprogram iast, ILexicalEnvironment lexenv)
			throws EvaluationException {
		try {
			return iast.getBody().accept(this, lexenv);
		} catch (Exception exc) {
			return exc;
		}
	}

	protected IGlobalVariableEnvironment globalVariableEnvironment;
	protected IOperatorEnvironment operatorEnvironment;

	public IOperatorEnvironment getOperatorEnvironment() {
		return operatorEnvironment;
	}

	public IGlobalVariableEnvironment getGlobalVariableEnvironment() {
		return globalVariableEnvironment;
	}

	@Override
	public Object visit(IASTboolean iast, ILexicalEnvironment data)
			throws EvaluationException {
		return 1;
	}

	@Override
	public Object visit(IASTfloat iast, ILexicalEnvironment data)
			throws EvaluationException {
		return 1;
	}

	@Override
	public Object visit(IASTinteger iast, ILexicalEnvironment data)
			throws EvaluationException {
		return 1;
	}

	@Override
	public Object visit(IASTstring iast, ILexicalEnvironment data)
			throws EvaluationException {
		return 1;
	}

	@Override
	public Object visit(IASTalternative iast, ILexicalEnvironment lexenv)
			throws EvaluationException {
		Object condition = iast.getCondition().accept(this, lexenv);
		Object consequence = iast.getConsequence().accept(this, lexenv);
		Object alternant = 0;
		if (iast.isTernary()) {
			alternant = iast.getAlternant().accept(this, lexenv);
		}

		return (int) condition + (int) consequence + (int) alternant;
	}

	@Override
	public Object visit(IASTbinaryOperation iast, ILexicalEnvironment lexenv)
			throws EvaluationException {
		Object leftOperand = iast.getLeftOperand().accept(this, lexenv);
		Object rightOperand = iast.getRightOperand().accept(this, lexenv);
		return (int) leftOperand + (int) rightOperand;
	}

	@Override
	public Object visit(IASTblock iast, ILexicalEnvironment lexenv)
			throws EvaluationException {
		int sum = 0;
		for (IASTbinding binding : iast.getBindings()) {
			sum += (int) binding.getInitialisation().accept(this, lexenv);
		}
		return sum;
	}

	@Override
	public Object visit(IASTinvocation iast, ILexicalEnvironment lexenv)
			throws EvaluationException {
		Object function = iast.getFunction().accept(this, lexenv);
		int sum = 0;
		for (IASTexpression arg : iast.getArguments())
			sum += (int) arg.accept(this, lexenv);

		return sum + (int) function;

	}

	@Override
	public Object visit(IASToperator iast, ILexicalEnvironment lexenv)
			throws EvaluationException {
		return 0;
	}

	@Override
	public Object visit(IASTsequence iast, ILexicalEnvironment lexenv)
			throws EvaluationException {
		IASTexpression[] expressions = iast.getExpressions();
		int lastValue = 0;
		for (IASTexpression e : expressions)
			lastValue += (int) e.accept(this, lexenv);
		return lastValue;
	}

	@Override
	public Object visit(IASTunaryOperation iast, ILexicalEnvironment lexenv)
			throws EvaluationException {
		return (int) iast.getOperand().accept(this, lexenv);
	}

	@Override
	public Object visit(IASTvariable iast, ILexicalEnvironment lexenv)
			throws EvaluationException {
		return 0;
	}

}
