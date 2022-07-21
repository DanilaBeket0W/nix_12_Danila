package ua.cars;

import ua.cars.entity.Vehicle;

import java.math.BigDecimal;

public class MyOptional <T extends Vehicle>{
        private T value;

        public MyOptional(T value){
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void discountPrice(){
            int randomProcent = 10 + (int) (Math.random() * 30);
            BigDecimal price = value.getPrice();
            BigDecimal discount = BigDecimal.valueOf((price.doubleValue()*100)/randomProcent);
            price.subtract(discount);
            value.setPrice(price);
        }
        public <K extends Number> void priceIncrease(K number){
            value.setPrice(value.getPrice().multiply(BigDecimal.valueOf(number.doubleValue())));
        }

        public void printObj(){
            System.out.println(value);
        }

}
