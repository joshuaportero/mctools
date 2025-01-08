package dev.portero.tools.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ButcherData {
    private final long timeElapsed;
    private final int count;
}
