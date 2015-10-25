package com.paracamplus.ilp2.tme5.partie2.interpreter;

import java.util.ArrayList;
import java.util.List;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp2.interfaces.IASTprogram;
import com.paracamplus.ilp2.tme5.partie2.IASTdernier;
import com.paracamplus.ilp2.tme5.partie2.IASTloop;
import com.paracamplus.ilp2.tme5.partie2.IASTsuivant;
import com.paracamplus.ilp2.tme5.partie2.IASTvisitor;

public class Interpreter extends
		com.paracamplus.ilp2.tme5.interpreter.Interpreter implements
		IASTvisitor<Object, ILexicalEnvironment, EvaluationException> {

	private List<String> loops;

	public Interpreter(IGlobalVariableEnvironment globalVariableEnvironment,
			IOperatorEnvironment operatorEnvironment) {
		super(globalVariableEnvironment, operatorEnvironment);
		loops = new ArrayList<String>();
	}

	@Override
	public Object visit(IASTloop iast, ILexicalEnvironment lexenv)
			throws EvaluationException {
		loops.add(iast.getEtiquette().getName());

		while (true) {
			// System.out.println(loops);
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
				System.out.println("Inloop : " + e.getMessage() + " "
						+ e.getEtiquette().getName());

				if (e.getEtiquette().getName()
						.equals(loops.get(loops.size() - 1))) {
					System.out.println("Je sors");
					break;
				} else
					throw new DernierException(e.getEtiquette());
			} catch (SuivantException e) {
				System.out.println("Inloop: " + e.getMessage() + " "
						+ e.getEtiquette().getName());
				if (e.getEtiquette().getName()
						.equals(loops.get(loops.size() - 1))) {
					loops.remove(loops.size() - 1);
					continue;
				} else
					throw new SuivantException(e.getEtiquette());

			} finally {
				System.out.println("liste : " + loops);
				if (loops.size() > 1)
					loops.remove(loops.size() - 1);
			}
		}

		return Boolean.FALSE;
	}

	public Object visit(IASTdernier iast, ILexicalEnvironment lexenv)
			throws EvaluationException, DernierException {
		String loopName = iast.getEtiquette().getName();
		if (!loops.contains(loopName))
			throw new EvaluationException("Il n'y a pas de boucle: " + loopName);
		throw new DernierException(iast.getEtiquette());
	}

	public Object visit(IASTsuivant iast, ILexicalEnvironment lexenv)
			throws EvaluationException, SuivantException {
		String loopName = iast.getEtiquette().getName();
		if (!loops.contains(loopName))
			throw new EvaluationException("Il n'y a pas de boucle: " + loopName);
		throw new SuivantException(iast.getEtiquette());
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
