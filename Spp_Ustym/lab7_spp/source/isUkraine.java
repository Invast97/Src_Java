public class isUkraine {
    public static void main(String[] args) throws Exception {
        String path = isUkraine.class.getClassLoader().getResource("filtered.csv").getPath();

        measure("Sync", new SyncCountryUkraine(path));
        measure("Async", new AsyncCountryUkraine(path));
        measure("Reactive", new ReactiveCountryUkraine(path));
    }

    public static void measure(String label, Counter counter) throws Exception {
        long start = System.currentTimeMillis();
        int count = counter.count();
        long end = System.currentTimeMillis();
        System.out.println(label + " count: " + count + ", time: " + (end - start) + " ms");
    }
}


