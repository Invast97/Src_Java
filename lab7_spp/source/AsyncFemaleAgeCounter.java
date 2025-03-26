import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class AsyncFemaleAgeCounter implements Counter {
    private final String path;

    public AsyncFemaleAgeCounter(String path) {
        this.path = path;
    }

    @Override
    public int count() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int count = 0;
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String line = br.readLine(); // skip header
                while ((line = br.readLine()) != null) {
                    String[] tokens = line.split(",");
                    if (tokens.length < 6) continue;
                    String sex = tokens[4];
                    String age = tokens[5];
                    if (sex.equals("Female") && isAgeBetween15And25(age)) {
                        count++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return count;
        }, executor);

        int result = future.get();
        executor.shutdown();
        return result;
    }

    private boolean isAgeBetween15And25(String age) {
        return age.equals("15-19 years") || age.equals("20-24 years");
    }
}
