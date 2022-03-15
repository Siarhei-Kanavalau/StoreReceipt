package ru.clevertec;

public enum DiscountCard {
    /**База дисконтных карт*/
    DISCOUNT_CARD_1(1,"Ivanov Ivan"),
    DISCOUNT_CARD_2(2,"Petrov Petr"),
    DISCOUNT_CARD_3(3,"Sidorov Sidor");

    public int cardID; // id карты
    public String nameHolder; // имя владельца карты

    DiscountCard(int cardID, String nameHolder) {
        this.cardID = cardID;
        this.nameHolder = nameHolder;
    }

    public int getCardID() {
        return cardID;
    }

    public String getNameHolder() {
        return nameHolder;
    }
}