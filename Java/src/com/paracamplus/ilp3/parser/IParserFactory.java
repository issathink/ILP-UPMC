/* *****************************************************************
 * ILP9 - Implantation d'un langage de programmation.
 * by Christian.Queinnec@paracamplus.com
 * See http://mooc.paracamplus.com/ilp9
 * GPL version 3
 ***************************************************************** */
package com.paracamplus.ilp3.parser;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp3.interfaces.IASTlambda;
import com.paracamplus.ilp3.interfaces.IASTnamedLambda;
import com.paracamplus.ilp3.interfaces.IASTprogram;
import com.paracamplus.ilp1.interfaces.IASTvariable;

public interface IParserFactory extends com.paracamplus.ilp2.parser.IParserFactory {
    @Override
	IASTprogram newProgram(
    		IASTfunctionDefinition[] functions,
            IASTexpression expression);
    
    IASTexpression newTry (IASTexpression body,
                           IASTlambda catcher,
                           IASTexpression finallyer );

    IASTlambda newLambda (IASTvariable[] variables,
                              IASTexpression body );

    IASTnamedLambda newNamedLambda(
            IASTvariable functionVariable,
            IASTvariable[] variables,
            IASTexpression body );
    
    IASTexpression newCodefinitions(IASTnamedLambda[] functions,
                                    IASTexpression body);


}
