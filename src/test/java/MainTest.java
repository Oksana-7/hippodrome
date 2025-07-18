import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class MainTest {
    @Timeout(22)
    @Disabled
    @Test
    void main() throws Exception {
        Main.main(new String[0]);
    }
}
