package vectorfieldsimulator.graph;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.function.BiFunction;
import java.util.function.Function;

public class functionsMath {

    private functionsMath() {}

    // function evaluator
    public static Function<Double, Double> parseToFunction(String f) {
        try {
            Expression func = new ExpressionBuilder(f).variable("x").build();

            return x -> {
                func.setVariable("x", x);
                return func.evaluate();
            };
        } catch (Exception e) {
            System.err.println("Invalid function: " + e.getMessage());
            return x -> 0.0; // fallback
        }
    }
}
