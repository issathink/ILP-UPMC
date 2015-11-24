package com.paracamplus.ilp3.tme7;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTinvocation;
import com.paracamplus.ilp1.interpreter.Function;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.Invocable;
import com.paracamplus.ilp2.ast.ASTfunctionDefinition;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp3.interfaces.IASTprogram;
import com.paracamplus.ilp3.interpreter.primitive.Throw.ThrownException;

public class Interpreter extends com.paracamplus.ilp3.interpreter.Interpreter
		implements
		IASTvisitor<Object, ILexicalEnvironment, EvaluationException> {

	public Interpreter(IGlobalVariableEnvironment globalVariableEnvironment,
			IOperatorEnvironment operatorEnvironment) {
		super(globalVariableEnvironment, operatorEnvironment);
	}

	@Override
	public Object visit(IASTprogram iast, ILexicalEnvironment lexenv)
			throws EvaluationException {
		for (IASTfunctionDefinition fd : iast.getFunctionDefinitions()) {
			System.out.println("func: " + fd.getName());
			Object f = this.visit(fd, lexenv);
			String v = fd.getName();
			getGlobalVariableEnvironment().addGlobalVariableValue(v, f);
		}
		try {
			Object obj = iast.getBody().accept(this, lexenv);
			System.out.println((obj == null) + " ; " + obj);
			return obj;
		} catch (ThrownException exc) {
			System.out.println("ThrownException + " + exc.getMessage());
			return exc.getThrownValue();
		} catch (Exception exc) {
			System.out.println("Exception whatever + "
					+ exc.getLocalizedMessage());
			return exc;
		}
	}

	@Override
	public Object visit(ASTcostart iast, ILexicalEnvironment data)
			throws EvaluationException {

		System.out.println(">>> Je visite noeud ASTcostart");
		Object function = iast.getFunction().accept(this, data);
		List<Object> args = new ArrayList<Object>();
		for (IASTexpression arg : iast.getArguments()) {
			Object value = arg.accept(this, data);
			args.add(value);
		}

		IASTexpression seq = ((Function) function).getBody();
		System.out.println("Args of coroutine " + args + ", functionBody: "
				+ seq);
		CoroutineInstance instance = new CoroutineInstance(function, args, this);

		System.out.println(instance + " , " + (instance == null));

		return instance;
	}

	@Override
	public Object visit(IASTinvocation iast, ILexicalEnvironment lexenv)
			throws EvaluationException {
		Object function = iast.getFunction().accept(this, lexenv);
		System.out.println("Invocation: " + (iast == null) + " ; " + iast);


		System.out.println("Cest quoi " + function);
		if (function instanceof Resume) {
			Resume f = (Resume) function;
			System.out.println("Instance of resume: "  + " ; Nom de la finction : " + f.getName());

			List<Object> args = new Vector<Object>();
			for (IASTexpression arg : iast.getArguments()) {
				Object value = arg.accept(this, lexenv);
				args.add(value);
			}
			return f.apply(this, args.toArray());
		} else if (function instanceof Invocable){
			System.out.println("Cest toujours des invocable");
			Invocable f = (Invocable) function;
			List<Object> args = new Vector<Object>();
			for (IASTexpression arg : iast.getArguments()) {
				Object value = arg.accept(this, lexenv);
				args.add(value);
			}
			return f.apply(this, args.toArray());
			
		} else {
			String msg = "Cannot apply " + iast;
			throw new EvaluationException(msg);
		}
	}

}
