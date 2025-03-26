public class FemaleAgeCounterApp {
    public static void main(String[] args) throws Exception {
        String path = FemaleAgeCounterApp.class.getClassLoader().getResource("filtered.csv").getPath();

        measure("Sync", new SyncFemaleAgeCounter(path));
        measure("Async", new AsyncFemaleAgeCounter(path));
        measure("Reactive", new ReactiveFemaleAgeCounter(path));
    }

    public static void measure(String label, Counter counter) throws Exception {
        long start = System.currentTimeMillis();
        int count = counter.count();
        long end = System.currentTimeMillis();
        System.out.println(label + " count: " + count + ", time: " + (end - start) + " ms");
    }
}


