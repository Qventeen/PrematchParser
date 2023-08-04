package org.serhiituhaienko.models;

import java.util.List;

public record Sport(String name, List<Region> regions) {
    public record League(String id, String name, boolean top) {}
    public record Region(String name, List<League> leagues) {}
}
