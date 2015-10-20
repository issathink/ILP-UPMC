/* *****************************************************************
 * ILP9 - Implantation d'un langage de programmation.
 * by Christian.Queinnec@paracamplus.com
 * See http://mooc.paracamplus.com/ilp9
 * GPL version 3
 ***************************************************************** */
package com.paracamplus.ilp1.ilp1tme3;

import com.paracamplus.ilp1.compiler.Primitive;
import com.paracamplus.ilp1.compiler.interfaces.IGlobalVariableEnvironment;

public class GlobalVariableStuffCompiler {
    public static void fillGlobalVariables (IGlobalVariableEnvironment env) {
        env.addGlobalVariableValue("pi", "ILP_PI");
        env.addGlobalFunctionValue(
                new Primitive("print", "ILP_print", 1));
        env.addGlobalFunctionValue(
                new Primitive("newline", "ILP_newline", 0));
        env.addGlobalFunctionValue(
                new Primitive("throw", "ILP_throw", 1));
        env.addGlobalFunctionValue(
                new Primitive("make_vector", "ILP_make_vector", 2));
        env.addGlobalFunctionValue(
                new Primitive("vector_length", "ILP_vector_length", 1));
        env.addGlobalFunctionValue(
                new Primitive("vector_get", "ILP_vector_get", 2));
        env.addGlobalFunctionValue(
                new Primitive("sinIL", "ILP_sin", 1));
    }
}
