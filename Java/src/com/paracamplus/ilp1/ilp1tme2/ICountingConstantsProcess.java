package com.paracamplus.ilp1.ilp1tme2;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;

public interface ICountingConstantsProcess {

	// Compte les constantes a partir du DOM:
	public int getNbConstantesDOM();

	// Compte les constantes a partir de l’AST:
	public int getNbConstantesAST() throws EvaluationException;

}
