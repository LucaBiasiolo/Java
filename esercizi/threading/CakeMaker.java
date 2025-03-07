package esercizi.threading;

public class CakeMaker {

    /* Instance Variables */
    private boolean whiskInUse = false;
    private boolean ovenInUse = false;
    private boolean mixingBowlInUse = false;

    /* Main Method */
    public static void main(String[] args) {

        CakeMaker c = new CakeMaker();
        try {

            Thread preheatOven = new Thread(c::preheatOven, "preheatOven");
            Thread mixDryIngredients = new Thread(c::mixDryIngredients, "mixDryIngredients");
            Thread mixWetIngredients = new Thread(c::mixWetIngredients, "mixWetIngredients");
            Thread combineIngredients = new Thread(c::combineIngredients, "combineIngredients");
            Thread bakeCake = new Thread(c::bakeCake, "bakeCake");
            Thread makeFrosting = new Thread(c::makeFrosting, "makeFrosting");
            Thread frostCake = new Thread(c::frostCake, "frostCake");

            preheatOven.start();
            mixDryIngredients.start();
            mixWetIngredients.start();
            makeFrosting.start();
            mixDryIngredients.join();
            mixWetIngredients.join();
            combineIngredients.start();
            combineIngredients.join();
            preheatOven.join();
            bakeCake.start();
            makeFrosting.join();
            bakeCake.join();
            frostCake.start();
            frostCake.join();

            System.out.println("Cake complete!");
        } catch (Exception e) {
            System.out.println(e);
        }
    } // End of Main

    /* Instance Methods */
    public void preheatOven() {
        try {
            synchronized(this){
                while(ovenInUse){
                    printTask("Waiting for the oven");
                    wait();
                }
                printTask("Using the oven!");
                ovenInUse = true;
                printTask("Oven pre-heating...");
                Thread.sleep(10000);
                printTask("Releasing the oven!");
                ovenInUse = false;
                notifyAll();
                printTask("Done!");
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void mixDryIngredients() {
        try {
            synchronized(this){
                while(mixingBowlInUse){
                    printTask("Waiting for the mixing bowl");
                    wait();
                }
                printTask("Using mixing bowl!");
                mixingBowlInUse = true;
                printTask("Mixing dry ingredients...");
                Thread.sleep(200);
                printTask("Adding cake flour");
                Thread.sleep(200);
                printTask("Adding salt");
                Thread.sleep(200);
                printTask("Adding baking powder");
                Thread.sleep(200);
                printTask("Adding baking soda");
                Thread.sleep(200);
                while (whiskInUse){
                    printTask("Waiting for the whisk");
                    wait();
                }
                printTask("Using the whisk!");
                whiskInUse = true;
                printTask("Mixing...");
                Thread.sleep(200);
                printTask("Releasing the whisk!");
                whiskInUse = false;
                notifyAll();
                printTask("Releasing mixing bowl!");
                mixingBowlInUse = false;
                notifyAll();
                printTask("Done!");
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void mixWetIngredients() {
        try {
            synchronized(this){
                while(mixingBowlInUse){
                    printTask("Waiting for the mixing bowl");
                    wait();
                }
                printTask("Using mixing bowl!");
                mixingBowlInUse = true;
                printTask("Mixing wet ingredients...");
                Thread.sleep(1000);
                printTask("Adding butter...");
                Thread.sleep(500);
                printTask("Adding eggs...");
                Thread.sleep(500);
                printTask("Adding vanilla extract...");
                Thread.sleep(500);
                printTask("Adding buttermilk...");
                Thread.sleep(500);
                while (whiskInUse){
                    printTask("Waiting for the whisk");
                    wait();
                }
                printTask("Using the whisk");
                whiskInUse = true;
                printTask("Mixing...");
                Thread.sleep(1500);
                printTask("Releasing the whisk");
                whiskInUse = false;
                notifyAll();
                printTask("Releasing mixing bowl!");
                mixingBowlInUse = false;
                notifyAll();
                printTask("Done!");
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    };

    public void combineIngredients() {
        try {
            printTask("Combining ingredients...");
            mixingBowlInUse = true;
            Thread.sleep(1000);
            printTask("Adding dry mix to wet mix...");
            Thread.sleep(1500);
            whiskInUse = true;
            printTask("Mixing...");
            Thread.sleep(1500);
            whiskInUse = false;
            mixingBowlInUse = false;
            printTask("Done!");
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    };

    public void bakeCake() {
        try {
            printTask("Baking cake...");
            ovenInUse = true;
            Thread.sleep(10000);
            ovenInUse = false;
            printTask("Done!");
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void makeFrosting() {
        try {
            printTask("Making frosting...");
            whiskInUse = true;
            mixingBowlInUse = true;
            printTask("Adding butter...");
            Thread.sleep(200);
            printTask("Adding milk...");
            Thread.sleep(200);
            printTask("Adding sugar...");
            Thread.sleep(200);
            printTask("Adding vanilla extract...");
            Thread.sleep(200);
            printTask("Adding salt...");
            Thread.sleep(200);
            whiskInUse = false;
            mixingBowlInUse = false;
            printTask("Done!");

        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void frostCake() {
        try {
            printTask("Frosting cake...");
            Thread.sleep(1500);
            printTask("Done!");

        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    private void printTask(String task) {
        System.out.println(Thread.currentThread().getName() + " " + " - " + task);
    }
}