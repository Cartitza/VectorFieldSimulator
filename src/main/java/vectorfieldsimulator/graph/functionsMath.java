package vectorfieldsimulator.graph;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.function.BiFunction;
import java.util.function.Function;

public class functionsMath {

    private functionsMath() {}

    // function evaluator
    public static BiFunction<Double, Double, Double> parseToFunction(String f) {
        try {
            Expression func = new ExpressionBuilder(f).variables("x", "y").build();

            return (x, y) -> {
                func.setVariable("x", x);
                func.setVariable("y", y);
                return func.evaluate();
            };
        } catch (Exception e) {
            System.err.println("Invalid function: " + e.getMessage());
            return (x, y) -> x; // fallback
        }
    }
}
