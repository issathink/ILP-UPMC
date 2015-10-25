package com.paracamplus.ilp2.tme5.partie2;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.Inamed;

public interface IParserFactory extends
		com.paracamplus.ilp2.tme5.IParserFactory {

	public IASTexpression newSuivant(Inamed etiquette);

	public IASTexpression newDernier(Inamed etiquette);

	IASTexpression newLoop(IASTexpression condition, IASTexpression body,
			Inamed etiquette);

}
