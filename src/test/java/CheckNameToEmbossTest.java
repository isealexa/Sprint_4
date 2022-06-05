import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class CheckNameToEmbossTest {

    private final String checkName;
    private final boolean expectedResult;

    public CheckNameToEmbossTest(String checkName, boolean expectedResult){
        this.checkName = checkName;
        this.expectedResult = expectedResult;
    }
    @Parameterized.Parameters(name = "The test {index} checks if user's name: {0}, then the method checkNameToEmboss will return {1}")
    public static Object[][] getCheckNameToEmbossTest() {
        return new Object[][] {
                {"Vlad Dracula", true}, // больше 3-х, меньше 19-ти - только латинские символы
                {"V Д", true}, // ровно 3 - латинские и кирилица
                {"Марина Александрова", true}, // ровно 19 - только кирилица
                {null, false},
                {"Иван", false}, // без пробелов
                {"Иванов ", false}, // пробел в конце строки
                {" Иванов", false}, // пробел в начале строки
                {"Ким Чин Ын", false}, // в строке два пробела
                {"Иван 777", false}, // цифры в имени
                {"Ва-ня, Ив@нов!", false}, // в имени присутсивуют другие символы
                {"Александра Алексеева", false}, // больше 19-ти
                // думаю, 4-е проверкиниже - уже излишни:
                {"", false},
                {"      ", false},
                {"И", false}, // меньше 3-х
                {"Иван  Иванов", false}, // два пробела посреди строки
        };
    }

    @Test
    @DisplayName("Verification the user's name for compliance with the conditions")
    @Description("The test verifies the correctness of the method checkNameToEmboss by passing the test value for the variable name")
    public void checkNameToEmbossReturnBoolean(){
        boolean actualResult = checkName();
        checkResult(actualResult);
    }

    @Step("Call the method checkNameToEmboss and pass the checking name there")
    public boolean checkName(){
        Account account = new Account(checkName);
        return account.checkNameToEmboss();
    }

    @Step("Check the value which was returned by the method checkNameToEmboss")
    public void checkResult(boolean actualResult){
        assertEquals("The method works wrong", expectedResult, actualResult);
    }
}
