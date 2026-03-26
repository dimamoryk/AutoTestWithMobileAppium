package emulator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Emulator {
    ANDROID_12(4723),
    ANDROID_14(4724);

    private final int port;
}
