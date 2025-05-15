package network;

import java.io.Serializable;

public class Response implements Serializable {
    final private static long serialVersionUID = 15L;

    private String answer;

    public Response(){

    }

    public Response(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public static Response wrongCount() {
        return new Response("[ERROR] Неверное количество аргументов");
    }

    public static Response wrongPassword() {
        return new Response("[ERROR] Неавторизованный пользователь");
    }
}
