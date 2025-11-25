package application.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorDTO { // TODO Refactor to Record class
    private final String code;
    private final String message;
}
