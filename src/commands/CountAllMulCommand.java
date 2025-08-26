package commands;

import commandbuilder.CommandBuilder;

import java.io.File;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class CountAllMulCommand implements Command {

    private final CommandBuilder builder;

    public CountAllMulCommand(CommandBuilder builder) {
        this.builder = builder;
    }

    @Override
    public void execute() {
        File dir = builder.getWorkingDirectory();

        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("Directory does not exist or is not accessible.");
            return;
        }

        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        CountResult result = forkJoinPool.invoke(new CountTask(dir));

        System.out.println("Total directories (including subdirectories): " + result.dirCount);
        System.out.println("Total files: " + result.fileCount);

        forkJoinPool.shutdown();
    }

    // Recursive task for counting
    private static class CountTask extends RecursiveTask<CountResult> {
        private final File dir;

        public CountTask(File dir) {
            this.dir = dir;
        }

        @Override
        protected CountResult compute() {
            int dirCount = 0;
            int fileCount = 0;

            File[] files = dir.listFiles();
            if (files == null) return new CountResult(0, 0);

            // List of subtasks for directories
            java.util.List<CountTask> subTasks = new java.util.ArrayList<>();

            for (File f : files) {
                if (f.isDirectory()) {
                    dirCount++; // count this directory
                    CountTask subTask = new CountTask(f);
                    subTask.fork(); // run asynchronously
                    subTasks.add(subTask);
                } else if (f.isFile()) {
                    fileCount++;
                }
            }

            // Combine results from subtasks
            for (CountTask task : subTasks) {
                CountResult subResult = task.join(); // wait and get result
                dirCount += subResult.dirCount;
                fileCount += subResult.fileCount;
            }

            return new CountResult(dirCount, fileCount);
        }
    }

    // Simple holder for counts
    private static class CountResult {
        int dirCount;
        int fileCount;

        CountResult(int dirCount, int fileCount) {
            this.dirCount = dirCount;
            this.fileCount = fileCount;
        }
    }
}
