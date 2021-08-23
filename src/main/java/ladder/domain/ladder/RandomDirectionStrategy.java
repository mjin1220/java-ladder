package ladder.domain.ladder;

import java.util.Random;

public final class RandomDirectionStrategy implements DirectionStrategy {

    private final Random RANDOM = new Random();

    @Override
    public boolean move() {
        return RANDOM.nextBoolean();
    }
}
