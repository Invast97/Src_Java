import java.io.BufferedReader;
import java.io.FileReader;
import io.reactivex.rxjava3.core.Observable;

class ReactiveCountryUkraine implements Counter {
    private final String path;

    public ReactiveCountryUkraine(String path) {
        this.path = path;
    }

    @Override
    public int count() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(path));

        Observable<String> observable = Observable.create(emitter -> {
            String line = reader.readLine(); // skip header
            while ((line = reader.readLine()) != null) {
                emitter.onNext(line);
            }
            emitter.onComplete();
        });

        return observable
                .filter(line -> {
                    String[] tokens = line.split(",");
                    if (tokens.length < 6) return false;
                    String sex = tokens[4];
                    String age = tokens[5];
                    return sex.equals("Female") && isAgeBetween15And25(age);
                })
                .count()
                .blockingGet()
                .intValue();
    }

    private boolean isAgeBetween15And25(String age) {
        return age.equals("15-19 years") || age.equals("20-24 years");
    }
}