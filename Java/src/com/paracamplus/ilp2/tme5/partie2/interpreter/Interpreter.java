package com.paracamplus.ilp2.tme5.partie2.interpreter;

import java.util.ArrayList;
import java.util.List;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp2.interfaces.IASTprogram;
import com.paracamplus.ilp2.tme5.DernierException;
import com.paracamplus.ilp2.tme5.SuivantException;
import com.paracamplus.ilp2.tme5.partie2.IASTdernier;
import com.paracamplus.ilp2.tme5.partie2.IASTloop;
import com.paracamplus.ilp2.tme5.partie2.IASTsuivant;
import com.paracamplus.ilp2.tme5.partie2.IASTvisitor;

public class Interpreter extends
		com.paracamplus.ilp2.tme5.interpreter.Interpreter implements
		IASTvisitor<Object, ILexicalEnvironment, EvaluationException> {

	private static int NB_LOOP = 0;
	List<String> loops;

	public Interpreter(IGlobalVariableEnvironment globalVariableEnvironment,
			IOperatorEnvironment operatorEnvironment) {
		super(globalVariableEnvironment, operatorEnvironment);
		loops = new ArrayList<String>();
	}

	@Override
	public Object visit(IASTloop iast, ILexicalEnvironment lexenv)
			throws EvaluationException {
		NB_LOOP++;
		while (true) {

			Object condition = iast.getCondition().accept(this, lexenv);
			if (condition instanceof Boolean) {
				Boolean c = (Boolean) condition;
				if (!c) {
					break;
				}
			}
			try {
				iast.getBody().accept(this, lexenv);
			} catch (DernierException e) {
				System.out.println("Inloop : " + e.getMessage());
				break;
			} catch (SuivantException e) {
				System.out.println("Inloop : " + e.getMessage());
				continue;
			}
		}
		NB_LOOP--;
		return Boolean.FALSE;
	}

	public Object visit(IASTdernier iast, ILexicalEnvironment lexenv)
			throws EvaluationException, DernierException {
		if (NB_LOOP > 0)
			throw new DernierException();
		else
			throw new EvaluationException("break en dehors de la boucle.");
	}

	public Object visit(IASTsuivant iast, ILexicalEnvironment lexenv)
			throws EvaluationException, SuivantException {
		throw new SuivantException();
	}

	@Override
	public Object visit(IASTprogram iast, ILexicalEnvironment lexenv)
			throws EvaluationException {
		for (IASTfunctionDefinition fd : iast.getFunctionDefinitions()) {
			Object f = this.visit(fd, lexenv);
			String v = fd.getName();
			getGlobalVariableEnvironment().addGlobalVariableValue(v, f);
		}
		try {
			return iast.getBody().accept(this, lexenv);
		} catch (DernierException | SuivantException e) {
			System.out.println("Ce message ne doit pas être affiché.");
			return null;
		} catch (Exception exc) {
			return exc;
		}
	}

}
