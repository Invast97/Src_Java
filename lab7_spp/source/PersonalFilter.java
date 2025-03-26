// Інтерфейс для фільтрації персон
interface PersonFilter {
    boolean isMatched(String[] columns);
}

// Фільтр по female + вік 15-25
class FemaleAgeFilter implements PersonFilter {
    @Override
    public boolean isMatched(String[] columns) {
        if (columns.length < 6) return false;
        String sex = columns[4].replace("\"", "").trim();
        String ageRange = columns[5].replace("\"", "").trim();

        return "Female".equalsIgnoreCase(sex) && isAgeInRange(ageRange, 15, 25);
    }

    private boolean isAgeInRange(String ageRange, int minAge, int maxAge) {
        if (ageRange.contains("-")) {
            String[] parts = ageRange.split("-");
            try {
                int start = Integer.parseInt(parts[0].trim());
                int end = Integer.parseInt(parts[1].split(" ")[0].trim());
                return start >= minAge && end <= maxAge;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }
}

