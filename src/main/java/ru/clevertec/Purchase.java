package ru.clevertec;

public enum Purchase {
    /** База продуктов*/
    Apple("Apple",1,2.00,true),
    Banana("Banana",2,3.20,false),
    Grapes("Grapes",3,7.00,false),
    Kiwi("Kiwi",4,4.50,false),
    Lemon("Lemon",5,2.50,true),
    Mango("Mango",6,15.00,false),
    Bread("Bread",7,1.25,true),
    Orange("Orange",8,4.00,false),
    Milk("Milk",9,2.75,false),
    Coffee("Coffee",10,18.90,true),
    Tea("Tea",11,8.50,true),
    Sugar("Sugar",12,4.10,false),
    Butter("Butter",13,3.40,true),
    Cheese("Cheese",14,25.30,true);

    private String name; // наименование товара
    private int id; // id товара
    private double price; //цена товара
    protected boolean discount; // акция на товар true/false

    // constructor
    Purchase(String name, int id, double price, boolean discount) {
        this.name = spaceCreate(name);
        this.id = id;
        this.price = price;
        this.discount = discount;
    }

    // getters & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    // метод заполняющий пробелами поле чека
    private String spaceCreate(String name){
        StringBuilder result = new StringBuilder(name);
        while (result.length() < 15){
            result.append(" ");
        }
        return result.toString();
    }

    //статический метод для получения элемента enum по id
    public static Purchase fromNumberId(Integer numberId){
        if (numberId != null){
            for (Purchase purchase: Purchase.values()) {
                if (numberId == (purchase.id)){
                    return purchase;
                }
            }
        }
        System.out.println("Элемент enum не существует.");
        return null;
    }
}