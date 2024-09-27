package token_generate.token.utils;

public class GenericException extends Exception {

    private String messageError;
    private int codeError;

    // Constructor con mensaje de error y código
    public GenericException(String messageError, int codeError) {
        super(messageError);  // Llama al constructor de Exception con el mensaje de error
        this.messageError = messageError;
        this.codeError = codeError;
    }

    // Getters para acceder al mensaje de error y al código
    public String getMessageError() {
        return messageError;
    }

    public int getCodeError() {
        return codeError;
    }
}
