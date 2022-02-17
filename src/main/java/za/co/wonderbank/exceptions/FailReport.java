package za.co.wonderbank.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FailReport {
    private String message;
    private String status;
    private String description;
}
