package esercizi.nestedclasses;

import java.util.Scanner;

class ShopItems {
    int totalCount;

    class Vegan{
        int smoothieCount;
        int slushieCount;
        int totalCount;

        void increaseCount(int type, int count){
            if (type ==3){
                this.smoothieCount += count;
            } else if (type==4){
                this.slushieCount += count;
            }
            this.totalCount += count;
            ShopItems.this.totalCount += count;
        }
    }

    class NonVegan{
        int iceCreamCount;
        int shakeCount;
        int totalCount;

        void increaseCount(int type, int count){
            if (type ==1){
                this.iceCreamCount += count;
            } else if (type==2){
                this.shakeCount += count;
            }
            this.totalCount += count;
            ShopItems.this.totalCount += count;
        }
    }
}

public class IceCreamShop {

    public static void main(String[] args) {
        int orderType = 0;
        Scanner input = new Scanner(System.in);
        int itemCount = 0;
        String itemName;
        ShopItems shopItems = new ShopItems();
        ShopItems.NonVegan nonVegan = shopItems.new NonVegan();
        ShopItems.Vegan vegan = shopItems.new Vegan();

        System.out.println("Hello! Welcome to the ice cream shop. We are ready to take your order. For ice-cream type 1, for shakes type 2, for smoothies type 3, and for slushies type 4. If you are done ordering type 0.");
        orderType = input.nextInt();

        while(orderType != 0) {
            System.out.println("How many items of this item would you like to order?");
            itemCount = input.nextInt();
            if(orderType == 1){
                itemName = "ice cream";
                nonVegan.increaseCount(1,itemCount);
            } else if(orderType == 2) {
                itemName = "shake";
                nonVegan.increaseCount(2,itemCount);
            } else if(orderType == 3) {
                itemName = "smoothie";
                vegan.increaseCount(3,itemCount);
            } else if(orderType == 4) {
                itemName = "slushie";
                vegan.increaseCount(4,itemCount);
            } else {
                break;
            }

            System.out.println("Adding " + itemCount + " of the item "+ itemName + " to your order!");

            System.out.println("Type a number for the next item in your order:\n1: Ice cream\n2: Shake\n3: Smoothie\n4: Slushie\n");
            orderType = input.nextInt();
        }
        System.out.println(String.format("Thank you for your order! Today we have sold %d orders of sweetness!", shopItems.totalCount));
        System.out.println(String.format("%d items have been ice cream and shakes", nonVegan.totalCount));
        System.out.println(String.format("%d items have been smoothies and slushies", vegan.totalCount));
    }
}