package network;

import java.io.Serializable;

public class Response implements Serializable {
    final private static long serialVersionUID = 15L;

    final private String answer;

    public Response(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public static Response wrongCount() {
        return new Response("[ERROR] Неверное количество аргументов");
    }
}
