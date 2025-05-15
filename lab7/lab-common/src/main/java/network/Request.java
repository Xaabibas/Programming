package network;

import java.io.Serializable;

public class Request implements Serializable {
    final private static long serialVersionUID = 15L;

    private String commandName;

    private String tokens;

    private Serializable obj;

    private String user;

    private String password;

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

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setTokens(String tokens) {
        this.tokens = tokens;
    }

    public String[] getTokens() {
        return tokens.split(" ");
    }

    public void setObj(Serializable obj) {
        this.obj = obj;
    }

    public Serializable getObj() {
        return obj;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
