/* *****************************************************************
 * ILP9 - Implantation d'un langage de programmation.
 * by Christian.Queinnec@paracamplus.com
 * See http://mooc.paracamplus.com/ilp9
 * GPL version 3
 ***************************************************************** */
package com.paracamplus.ilp2.compiler.ast;

import java.util.Arrays;
import java.util.List;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp2.compiler.interfaces.IASTCfunctionDefinition;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;



public class ASTCprogram extends com.paracamplus.ilp1.compiler.ast.ASTCprogram 
implements com.paracamplus.ilp2.compiler.interfaces.IASTCprogram {

    public ASTCprogram (IASTCfunctionDefinition[] functions,
                        IASTexpression expression) {
        super(expression);
        this.functions = Arrays.asList(functions);
    }
    
    protected List<IASTfunctionDefinition> functions;
   
    
    @Override
	public IASTCfunctionDefinition[] getFunctionDefinitions() {
        IASTfunctionDefinition[] fds = functions.toArray(new IASTfunctionDefinition[0]);
        IASTCfunctionDefinition[] newfds = 
                new IASTCfunctionDefinition[fds.length];
        for ( int i=0 ; i<fds.length ; i++ ) {
            newfds[i] = (IASTCfunctionDefinition) fds[i];
        }
        return newfds;
    }
}
