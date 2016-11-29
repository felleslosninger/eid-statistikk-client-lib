package no.difi.statistics.model.query;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import no.difi.statistics.model.RelationalOperator;

import java.io.IOException;

@JsonDeserialize(using = TimeSeriesFilter.TimeSeriesFilterJsonDeserializer.class)
public class TimeSeriesFilter {

    private int percentile;
    private String measurementId;
    private RelationalOperator relationalOperator;

    public TimeSeriesFilter(int percentile, String measurementId, RelationalOperator relationalOperator) {
        this.percentile = percentile;
        this.measurementId = measurementId;
        this.relationalOperator = relationalOperator;
    }

    public String getMeasurementId() {
        return measurementId;
    }

    public int getPercentile() {
        return percentile;
    }

    public RelationalOperator getRelationalOperator() {
        return relationalOperator;
    }

    /**
     * Use custom deserializer to maintain immutability property
     */
    static class TimeSeriesFilterJsonDeserializer extends JsonDeserializer<TimeSeriesFilter> {

        @Override
        public TimeSeriesFilter deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
            JsonNode node = parser.getCodec().readTree(parser);
            return new TimeSeriesFilter(
                    node.get("percentile").asInt(),
                    node.get("measurementId").asText(),
                    RelationalOperator.valueOf(node.get("relationalOperator").asText())
            );
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeSeriesFilter that = (TimeSeriesFilter) o;

        if (percentile != that.percentile) return false;
        return measurementId != null ? measurementId.equals(that.measurementId) : that.measurementId == null;

    }

    @Override
    public int hashCode() {
        int result = percentile;
        result = 31 * result + (measurementId != null ? measurementId.hashCode() : 0);
        return result;
    }
}
