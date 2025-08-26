package commandbuilder;

import java.io.File;
import java.util.List;

public class CommandBuilder {
    private File workingDir;

    public CommandBuilder() {
        this.workingDir = new File(System.getProperty("user.dir")); // default
    }

    public void setWorkingDirectory(File dir) {
        if (dir.exists() && dir.isDirectory()) {
            this.workingDir = dir;
        } else {
            System.out.println("Invalid directory: " + dir);
        }
    }

    public ProcessBuilder build(List<String> command) {
        ProcessBuilder builder = new ProcessBuilder(command);
        builder.directory(workingDir);
        return builder;
    }

    public File getWorkingDirectory() {
        return workingDir;
    }
}
