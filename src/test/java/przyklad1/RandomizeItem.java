package przyklad1;

import java.util.Random;

public class RandomizeItem {

    public static String getRandomElementFromTable(String[] table) {
        Random random = new Random();
        int randomInt = random.nextInt(50);
        int randomIndex = randomInt % table.length;
        return table[randomIndex];
    }
}
