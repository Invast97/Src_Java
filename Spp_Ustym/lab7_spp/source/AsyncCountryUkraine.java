import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class AsyncCountryUkraine implements Counter {
    private final String path;

    public AsyncCountryUkraine(String path) {
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
                    String country = tokens[1];
                    if (tokens.length < 3) continue;
                    if (country.equals("Ukraine"))  {
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

    private boolean isUkraine(String country) {
        return country.equals("Ukraine");
    }
}
