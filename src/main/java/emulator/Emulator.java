package emulator;

import lombok.Getter;

@Getter
public enum Emulator {
    ANDROID_12(4723),
    ANDROID_14(4724);

    private final int port;

    Emulator(int port) {
        this.port = port;
    }
}
