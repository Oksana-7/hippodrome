import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

public class HippodromeTest {
    @Test
    void constructorThrowsExcWhenHorsesIsNull() {
        String expected = "Horses cannot be null.";
        String actual = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null)).getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void constructorThrowsExcWhenHorsesIsEmpty() {
        String expected = "Horses cannot be empty.";
        String actual = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>())).getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void getHorses() {
        List<Horse> expected = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            expected.add(new Horse("Horse " + i, Horse.getRandomDouble(0.1, 1.0), Horse.getRandomDouble(0.1, 1.0)));
        }
        List<Horse> actual = new Hippodrome(expected).getHorses();
        assertEquals(expected, actual);
    }

    @Test
    void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse horse : hippodrome.getHorses()) {
            Mockito.verify(horse, times(1)).move();
        }
    }

    @Test
    void getWinner() {
        Horse horseFirst = new Horse("Horse 1", 1, 15);
        Horse horseSecond = new Horse("Horse 2", 2, 10);
        Horse horseThird = new Horse("Horse 3", 3, 18);
        Hippodrome hippodrome = new Hippodrome(List.of(horseFirst, horseSecond, horseThird));
        assertEquals(horseThird, hippodrome.getWinner());
    }
}