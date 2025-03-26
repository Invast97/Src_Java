// Лічильник з інжекцією фільтра
class PersonCounter {
    private final PersonFilter filter;
    private int count = 0;

    public PersonCounter(PersonFilter filter) {
        this.filter = filter;
    }

    public void process(String[] columns) {
        if (filter.isMatched(columns)) {
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}

