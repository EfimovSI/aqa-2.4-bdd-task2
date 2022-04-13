package ru.netology.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransactionPage;
import ru.netology.page.VerificationPage;


public class TemplateSteps {
    private static LoginPage loginPage;
    private static DashboardPage dashboardPage;
    private static VerificationPage verificationPage;
    private static TransactionPage transactionPage;

    @Пусть("пользователь залогинен с именем {string} и паролем {string}")
    public void validLoginAndVerification(String login, String password) {
        loginPage = Selenide.open("http://localhost:9999", LoginPage.class);
        verificationPage = loginPage.validLogin(login, password);
        var authInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        dashboardPage = verificationPage.validVerify(verificationCode);
    }

    @Когда("пользователь переводит {string} рублей с карты с номером {string} на свою {string} карту с главной страницы;")
    public void depositCardFromAnother(String amount, String fromCardNumber, String cardPositionNumber) {
            transactionPage = dashboardPage.depositButtonClick(cardPositionNumber);
            dashboardPage = transactionPage.validTransfer(amount, fromCardNumber);
    }

    @Тогда("баланс его {string} карты из списка на главной странице должен стать {string} рублей")
    public void matchCardBalance(String cardPositionNumber, String balance) {
        dashboardPage.matchBalanceOfCard(cardPositionNumber, balance);
    }
}