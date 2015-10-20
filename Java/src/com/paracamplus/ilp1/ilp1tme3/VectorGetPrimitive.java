package com.paracamplus.ilp1.ilp1tme3;

import java.math.BigInteger;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;

public class VectorGetPrimitive extends BinaryPrimitive {

	public VectorGetPrimitive(String name) {
		super(name);
	}

	@Override
	public Object apply(Object arg1, Object arg2) throws EvaluationException {
		if (arg1 instanceof VectorObejct) {
			if (arg2 instanceof BigInteger) {
				int index = ((BigInteger) arg2).intValue();
				VectorObejct asTvector = ((VectorObejct) arg1);

				if (index < asTvector.getTaille() && index >= 0)
					return asTvector.getElement();
				else
					throw new EvaluationException("IndexArrayOutOfBoundsException");
			} else
				throw new EvaluationException("Index invalide.");
		}
		throw new EvaluationException(
				"On ne peut pas appeler get sur n'importe quoi.");
	}

}
