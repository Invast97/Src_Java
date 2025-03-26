import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class SyncCountryUkraine implements Counter {
    private final String path;

    public SyncCountryUkraine(String path) {
        this.path = path;
    }

    @Override
    public int count() {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length < 3) continue;
                String country = tokens[1];
                if (country.equals("Ukraine")){
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    private boolean isUkraine(String country) {
        return country.equals("Ukraine");
    }
}