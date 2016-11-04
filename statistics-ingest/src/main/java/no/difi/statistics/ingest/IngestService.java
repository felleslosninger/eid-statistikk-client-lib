package no.difi.statistics.ingest;

import no.difi.statistics.model.TimeSeriesPoint;
import no.difi.statistics.model.ingest.IngestResponse;

import java.util.List;

import static java.lang.String.format;

public interface IngestService {

    void minute(String timeSeriesName, String owner, TimeSeriesPoint dataPoint) throws TimeSeriesPointAlreadyExists;

    IngestResponse minutes(String timeSeriesName, String owner, List<TimeSeriesPoint> dataPoints);

    class TimeSeriesPointAlreadyExists extends RuntimeException {

        public TimeSeriesPointAlreadyExists(String owner, String timeSeries, String id, Throwable cause) {
            super(format("The time series %s owned by %s already has a data point with timestamp %s", timeSeries, owner, id), cause);
        }

    }

}
