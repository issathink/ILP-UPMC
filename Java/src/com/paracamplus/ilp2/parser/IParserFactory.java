/* *****************************************************************
 * ILP9 - Implantation d'un langage de programmation.
 * by Christian.Queinnec@paracamplus.com
 * See http://mooc.paracamplus.com/ilp9
 * GPL version 3
 ***************************************************************** */
package com.paracamplus.ilp2.parser;


import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp2.interfaces.IASTprogram;

public interface IParserFactory extends com.paracamplus.ilp1.parser.IParserFactory {
    IASTprogram newProgram(
    		IASTfunctionDefinition[] functions,
            IASTexpression expression);
    
    IASTexpression newLoop(IASTexpression condition,
                           IASTexpression body);

    IASTfunctionDefinition newFunctionDefinition(
            IASTvariable functionVariable,
            IASTvariable[] variables,
            IASTexpression body);
    
    IASTexpression newAssignment(IASTvariable variable,
            IASTexpression value);
    
}
