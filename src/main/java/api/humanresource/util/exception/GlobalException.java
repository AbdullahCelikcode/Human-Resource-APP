package api.humanresource.util.exception;

import jakarta.mail.MessagingException;

public class GlobalException extends RuntimeException {

    public GlobalException(String massage) {
        super(massage);
    }

    public GlobalException(MessagingException messagingException) {
        super(messagingException.getMessage());
    }
}
