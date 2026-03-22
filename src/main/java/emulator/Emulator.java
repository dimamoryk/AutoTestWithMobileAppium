package emulator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Emulator {

    ANDROID_12(4723),
    ANDROID_14(4724);

    @Getter
    private final int port;

    Emulator(int port) {
        this.port = port;
    }
}