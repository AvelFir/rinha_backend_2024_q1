package br.com.guilherme.rinhabackend2024q1.enuns;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;
import java.util.Map;
import java.util.TreeMap;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TransactionType {
    CREDITO("c"),
    DEBITO("d");

    private final String value;

    private static final Map<String, TransactionType> mapString = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    static {
        for(final TransactionType type: EnumSet.allOf(TransactionType.class)){
            mapString.put(type.getValue().toLowerCase(), type);
        }
    }

    @JsonCreator
    public static TransactionType entryOf(final String type){
        return (type == null || type.isBlank()) ? null: mapString.get(type.toLowerCase());
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
