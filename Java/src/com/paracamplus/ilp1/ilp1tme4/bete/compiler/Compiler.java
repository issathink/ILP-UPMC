package com.paracamplus.ilp1.ilp1tme4.bete.compiler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Set;

import com.paracamplus.ilp1.compiler.AssignDestination;
import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.NoDestination;
import com.paracamplus.ilp1.compiler.interfaces.IASTCglobalVariable;
import com.paracamplus.ilp1.compiler.interfaces.IASTCprogram;
import com.paracamplus.ilp1.compiler.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.compiler.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp1.ilp1tme4.bete.IASTaMoinsQue;
import com.paracamplus.ilp1.ilp1tme4.bete.IASTvisitorAMoinsQue;
import com.paracamplus.ilp1.interfaces.IASTprogram;
import com.paracamplus.ilp1.interfaces.IASTvariable;

public class Compiler extends com.paracamplus.ilp1.compiler.Compiler implements
		IASTvisitorAMoinsQue<Void, Compiler.Context, CompilationException> {

	public Compiler(IOperatorEnvironment ioe, IGlobalVariableEnvironment igve) {
		super(ioe, igve);
	}

	@Override
	public IASTCprogram normalize(IASTprogram program)
			throws CompilationException {
		INormalizationFactory nf = new NormalizationFactory();
		Normalizer normalizer = new Normalizer(nf);
		IASTCprogram newprogram = normalizer.transform(program);
		return newprogram;
	}
	
	@Override
    public String compile(IASTprogram program) 
            throws CompilationException {
        
        IASTCprogram newprogram = normalize(program);
        newprogram = optimizer.transform(newprogram);

        GlobalVariableCollector gvc = new GlobalVariableCollector();
        Set<IASTCglobalVariable> gvs = gvc.analyze(newprogram);
        newprogram.setGlobalVariables(gvs);
        
        //FreeVariableCollector fvc = new FreeVariableCollector(newprogram);
        //newprogram = fvc.analyze();
      
        Context context = new Context(NoDestination.NO_DESTINATION);
        StringWriter sw = new StringWriter();
        try {
            out = new BufferedWriter(sw);
            visit(newprogram, context);
            out.flush();
        } catch (IOException exc) {
            throw new CompilationException(exc);
        }
        return sw.toString();
    }

	@Override
	public Void visit(IASTaMoinsQue iast, Context context)
			throws CompilationException {
		IASTvariable tmp1 = context.newTemporaryVariable();
		emit("{ \n");
		emit("  ILP_Object " + tmp1.getMangledName() + "; \n");
		Context c = context.redirect(new AssignDestination(tmp1));
		iast.getCondition().accept(this, c);
		emit("  if ( !ILP_isEquivalentToTrue(");
		emit(tmp1.getMangledName());
		emit(" ) ) {\n");
		iast.getConsequence().accept(this, context);
		emit("\n  }\n}\n");
		return null;
	}

}
