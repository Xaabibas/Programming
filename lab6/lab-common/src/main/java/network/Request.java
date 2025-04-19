package network;

import java.io.Serializable;

public class Request implements Serializable {
    final private static long serialVersionUID = 15L;

    final private String commandName;

    final private String tokens;

    final private Serializable obj;

    public Request(String commandName, String tokens, Serializable obj) {
        this.commandName = commandName;
        this.tokens = tokens;
        this.obj = obj;
    }

    public Request(String commandName, String tokens) {
        this(commandName, tokens, null);
    }

    public Request() {
        this("", "");
    }

    public String getCommandName() {
        return commandName;
    }

    public String[] getTokens() {
        return tokens.split(" ");
    }

    public Serializable getObj() {
        return obj;
    }
}
