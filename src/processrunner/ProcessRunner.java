package processrunner;
import java.io.*;
import java.util.*;
public class ProcessRunner {
    public List<String> runProcess(ProcessBuilder builder) {
        List<String> output = new ArrayList<>();
        try {
            Process process = builder.start();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            String line;
            while ((line = reader.readLine()) != null) {
                output.add(line);
            }

            int exitCode = process.waitFor();
            System.out.println("Exited with code: " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

}
