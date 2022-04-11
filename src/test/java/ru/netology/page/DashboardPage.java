package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement firstCardDepositButton = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] button");
    private SelenideElement secondCardDepositButton = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] button");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public TransactionPage firstDepositButtonClick() {
        firstCardDepositButton.click();
        return new TransactionPage();
    }

    public TransactionPage secondDepositButtonClick() {
        secondCardDepositButton.click();
        return new TransactionPage();
    }

    public void matchBalanceOfCard(String cardPositionNumber, String balance) {
        if (cardPositionNumber == "1") {
            $(withText(balance)).shouldHave(attribute("data-test-id", "92df3f1c-a033-48e6-8390-206f6b1f56c0"));
        } else if (cardPositionNumber == "2") {
            $(withText(balance)).shouldHave(attribute("data-test-id", "0f3f5c2a-249e-4c3d-8287-09f7a039391d"));
        }
    }
}
