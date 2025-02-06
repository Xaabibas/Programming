package commands;

public class ExecuteScript implements Command {
    @Override
    public String describe() {
        return "execute_script - считать и выполнить скрипт из введенного файла";
    }
}
