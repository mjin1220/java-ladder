package nextstep.ladder.domain;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static nextstep.ladder.domain.LadderPlayersTest.ps;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class Step4LadderRailsTest {
    @Test
    public void create() {
        assertThat(Step4LadderRails.of(List.of("n1", "n2"), List.of("r1", "r2")))
                .isEqualTo(Step4LadderRails.of(List.of("n1", "n2"), List.of("r1", "r2")));
    }

    static Stream<Arguments> parseCreateFailed() {
        return Stream.of(
                Arguments.of(null, List.of("r1", "r2"), "cannot be null"),
                Arguments.of(List.of("p1", "p2"), null, "cannot be null"),
                Arguments.of(List.of("p1", "p2"), List.of("r1", "r2", "r3"), "count is not matched")
        );
    }

    @ParameterizedTest
    @MethodSource("parseCreateFailed")
    public void createFailed(List<String> players, List<String> results, String expectedMessage) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Step4LadderRails.of(players, results))
                .withMessageContaining(expectedMessage);
    }

    @Test
    public void playersAndResults() {
        List<String> players = List.of("n1", "n2");
        List<String> results = List.of("r1", "r2");
        Step4LadderRails rails = lrs(players, results);
        assertThat(rails.players()).isEqualTo(ps(players));
        assertThat(rails.prizes()).isEqualTo(LadderPrizesTest.pzs(results));
    }

    @Test void railCount() {
        assertThat(lrs(List.of("n1", "n2"), List.of("r1", "r2")).railCount()).isEqualTo(LadderRailCount.of(2));
    }

    public static Step4LadderRails lrs(List<String> players, List<String> results) {
        return Step4LadderRails.of(players, results);
    }

    public static Step4LadderRails simpleRails = lrs(List.of("p1", "p2"), List.of("r1", "r2"));
}
