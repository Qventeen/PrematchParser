package org.serhiituhaienko.util;

import org.serhiituhaienko.models.Match;
import org.serhiituhaienko.models.Sport;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;

public class Printer {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss 'UTC'").withZone(ZoneId.of("UTC"));

    public static void print(List<Sport> sports, Map<String, Match> matches) {

        var space = ' ';
        for (var sport : sports) {
            for (var league : sport.topLeagues()) {
                out.printf("%s, %s %s%n", sport.name(), league.regionName(), league.name());
                var match = matches.get(league.id());
                out.printf("%5s%s, %s, %s%n", space, match.name(), FORMATTER.format(match.startTime()), match.id());
                for (var market : match.markets()) {
                    out.printf("%10s%s%n", space, market.name());
                    for (var runner : market.runners()) {
                        out.printf("%15s%s, %s, %s%n", space, runner.name(), runner.price(), runner.id());
                    }
                }
            }
        }
    }
}
