/* *****************************************************************
 * ILP9 - Implantation d'un langage de programmation.
 * by Christian.Queinnec@paracamplus.com
 * See http://mooc.paracamplus.com/ilp9
 * GPL version 3
 ***************************************************************** */
package com.paracamplus.ilp3.interfaces;

import com.paracamplus.ilp1.annotation.OrNull;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTinstruction;

public interface IASTtry extends IASTinstruction {
    IASTexpression getBody ();
    @OrNull IASTlambda getCatcher ();
    @OrNull IASTexpression getFinallyer ();
}
