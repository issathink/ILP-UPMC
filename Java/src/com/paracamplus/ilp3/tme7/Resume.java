package com.paracamplus.ilp3.tme7;

import com.paracamplus.ilp1.interpreter.Interpreter;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.primitive.UnaryPrimitive;

public class Resume extends UnaryPrimitive {

	public Resume(String name) {
		super(name);
	}

	@Override
	public int getArity() {
		return 1;
	}

	@Override
	public Object apply(Interpreter interpreter, Object[] argument)
			throws EvaluationException {
		if (argument.length > 1)
			throw new EvaluationException(
					"Ce message ne doit pas etre affiche.");
		return apply(argument[0]);
	}

	@Override
	public Object apply(Object arg) throws EvaluationException {

		if (arg instanceof CoroutineInstance) {
			CoroutineInstance instance = (CoroutineInstance) arg;

			instance.getSemaphoreCor().release();
			if (! instance.getFinished()) {
				System.out.println("Je lance la coroutine");
				instance.start();
			}
			try {
				instance.getSemaphoreMain().acquire();
			} catch (InterruptedException e) {
				System.out
						.println("Ce message (dans Resume) ne doit pas etre affiche.[Interrupted]");
				e.printStackTrace();
			}
		} else {
			System.out
					.println("Ce message (dans Resume) ne doit pas etre affiche.[]");
		}

		return Boolean.FALSE;
	}

}
