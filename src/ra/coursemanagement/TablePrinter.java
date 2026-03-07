package ra.coursemanagement;

public class TablePrinter {
    public static void printLine(int... widths) {
        for (int w : widths) {
            System.out.print("+");
            for (int i = 0; i < w + 2; i++) {
                System.out.print("-");
            }
        }
        System.out.println("+");
    }

    public static void printRow(String[] data, int... widths) {
        for (int i = 0; i < data.length; i++) {
            System.out.printf("| %-" + widths[i] + "s ", data[i]);
        }
        System.out.println("|");
    }
}
