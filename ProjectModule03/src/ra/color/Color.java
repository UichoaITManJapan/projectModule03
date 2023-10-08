package ra.color;

public class Color {
    public static final String RESET = "\u001B[0m";    // reset
    public static final String BLACK = "\033[0;30m";   // BLACK đen
    public static final String RED = "\033[0;31m";     // RED đỏ
    public static final String GREEN = "\033[0;32m";   // GREEN xanh lá
    public static final String YELLOW = "\033[0;33m";  // YELLOW vàng
    public static final String BLUE = "\033[0;34m";    // BLUE xanh dương
    public static final String PURPLE = "\033[0;35m";  // PURPLE màutims
    public static final String CYAN = "\033[0;36m";    // CYAN xanh dương nhạt
    public static final String WHITE = "\033[0;37m";   // WHITE trắng
    public static final String PINK = "\033[38;5;225m"; // màu hồng nhạt
    public static final String TIM = "\033[38;5;153m";  // màu tím
    public static final String XANHLAMA = "\033[38;5;30m"; // màu xanh lá
    public static final String DONHAT = "\033[38;5;203m"; // màu đỏ
    public static final String VANGDAM = "\033[38;5;226m"; // màu vàng đậm
    public static final String TRANGSUA = "\033[38;5;231m"; // màu trắng
    public static final String TRANGNHAT = "\033[38;5;254m"; // màu trắng nhạt

    //BackGround
    public static final String RED_BACKGROUND = "\033[41m";    // RED
    public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN

    // Bold
    public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
    public static final String WHITE_BOLD = "\033[1;37m";  // WHITE
}
