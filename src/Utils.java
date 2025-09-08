public class Utils {
    public static Object parseInput(String input){
        try{ // return integer
            return Integer.parseInt(input);
        }catch (NumberFormatException ignored){}

        try{ // return double
            return Double.parseDouble(input);
        }catch (NumberFormatException ignored){}

        if (input.length() == 1){ // return char
            return input.charAt(0);
        }

        return input; // return string
    }

    public static String timeSpent(long nanos){
        return String.format("%06.2f ms", nanos / 1_000_000.0);
    }
}