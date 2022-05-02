import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@DisplayName("PRUEBA DISPLAY NAME")
class CalculatorTest {
    private int a, b;
    Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();

        a = ThreadLocalRandom.current().nextInt();
        b = ThreadLocalRandom.current().nextInt();
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testAdd1() {
        int result = calculator.add(a, b);

        assertEquals( a + b, result, "Resultado incorrecto de la suma");
    }

    @Test
    @EnabledOnOs({OS.LINUX})
    void testAdd2() {
        int result = calculator.add(a, b);

        assertEquals( a + b, result, "Resultado incorrecto de la suma");
    }

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void testAdd3(){
        int result = calculator.add(a, b);

        assertEquals( a + b, result, "Resultado incorrecto de la suma");
    }


    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4})
    void testAdd4(int num){
        int result = calculator.add(a,num);

        assertEquals(a + num, result, "Resultado incorrecto de la suma.");
    }

    @ParameterizedTest
    @ValueSource(ints = {2,4,6,8})
    void testMultiply2(int num){
        int additionResult = calculator.add(a, num);

        assumeTrue(additionResult == a+num);

        int multiplicationResult = calculator.multiply(a, num);

        Assertions.assertEquals(a * num, multiplicationResult, "Resultado incorrecto de la multiplicación");
    }


   @Test
   @DisplayName("╯°□°）╯")
    void testAddThrowsExceptionWhenIsCalledWithInvalidParams() {
        String c = "hello";

      assertThrows(Exception.class, () -> {
            int result = calculator.add(a, b);

            assertEquals( a + b, result, "Resultado incorrecto de la suma");
       });
    }


    @Test
    void testMultiply() {
        int additionResult = calculator.add(a, b);

        assumeTrue(additionResult == a+b);

        int multiplicationResult = calculator.multiply(a, b);

        Assertions.assertEquals(a * b, multiplicationResult, "Resultado incorrecto de la multiplicación");
    }
}