package org.serhiituhaienko.models;

import java.util.List;

public record Match(String id, String name, String startTime, List<Market> markets) {
    public record Market(String name, List<Runner> runners) {}
    public record Runner(String name, String price) {}
}
