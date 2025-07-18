import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {
    @Test
    void constructorThrowsExcWhenNameIsNull() {
        String expected = "Name cannot be null.";
        String actual = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 0.1, 0.1)).getMessage();
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({"'', ''", "' '", "'\t'", "'\n'", "'\r'", "'\f'",})
    void constructorThrowsExcWhenNameIsBlank(String name) {
        String expected = "Name cannot be blank.";
        String actual = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 0.1)).getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void constructorThrowsExcWhenSpeedIsNegative() {
        String expected = "Speed cannot be negative.";
        String actual = assertThrows(IllegalArgumentException.class, () -> new Horse("HorseName", -0.1, 0.1)).getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void constructorThrowsExcWhenDistanceIsNegative() {
        String expected = "Distance cannot be negative.";
        String actual = assertThrows(IllegalArgumentException.class, () -> new Horse("HorseName", 0.1, -0.1)).getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void getName() {
        Horse horse = new Horse("BlackJack", 0.5, 1.9);
        assertEquals("BlackJack", horse.getName());
    }

    @Test
    void getSpeed() {
        Horse horse = new Horse("BlackJack", 0.5, 1.9);
        assertEquals(0.5, horse.getSpeed());
    }

    @Test
    void getDistance() {
        Horse horse = new Horse("BlackJack", 0.5, 1.9);
        assertEquals(1.9, horse.getDistance());
    }

    @Test
    void getDefaultDistance() {
        Horse horse = new Horse("BlackJack", 0.5);
        assertEquals(0.0, horse.getDistance());
    }

    @ParameterizedTest
    @CsvSource({"0.2 , 0.9",})
    void move(Double min, Double max) {
        try (MockedStatic<Horse> horseMock = Mockito.mockStatic(Horse.class)) {
            horseMock.when(() -> Horse.getRandomDouble(min, max)).thenReturn(0.3);
            Horse horse = new Horse("BlackJack", 0.5, 1.9);
            horse.move();
            horseMock.verify(() -> Horse.getRandomDouble(0.2, 0.9), Mockito.times(1));
            Double expected = 1.9 + 0.5 * 0.3;
            double actual = horse.getDistance();
            assertEquals(expected, actual);
        }
    }
}
