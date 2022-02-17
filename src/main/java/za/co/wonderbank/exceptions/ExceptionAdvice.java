package za.co.wonderbank.exceptions;


import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice extends RuntimeException {

    @ExceptionHandler
    public ResponseEntity expiredJwtException(ExpiredJwtException expiredJwtException) {
        return ResponseEntity.ok("Invalid/Expired Token: " + expiredJwtException);
    }

    @ExceptionHandler
    public ResponseEntity expiredJwtException(Exception e) {
        return ResponseEntity.ok(new FailReport(e.getMessage(), "", ""));
    }
}
