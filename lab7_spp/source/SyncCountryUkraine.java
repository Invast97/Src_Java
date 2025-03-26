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
    }

    private boolean isAgeBetween15And25(String age) {
        return age.equals("15-19 years") || age.equals("20-24 years");
    }
}