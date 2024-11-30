import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите количество выпиленных клеток:");
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        if (N < 1 || N > 64) {
            System.out.println("Шахматная доска состоит из 64 клеток. Введите число от 1 до 64.");
            return; // Завершаем программу, если количество клеток вне диапазона
        }

        boolean[][] board = new boolean[8][8]; // Создаём шахматную доску 8x8
        System.out.println("Введите координаты клеток (доска имеет размер 8x8). Формат: X Y (где 0 ≤ X, Y < 8)");

        for (int i = 0; i < N; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            // Проверка границ
            if (x < 0 || x >= 8 || y < 0 || y >= 8) {
                System.out.println("Координаты должны быть в диапазоне от 0 до 7.");
                return; // Завершаем программу, если координаты вне диапазона
            }

            board[x][y] = true; // Помечаем клетку как вырезанную
        }

        int perimeter = calculatePerimeter(board);
        System.out.println("Периметр вырезанной фигуры: " + perimeter);
    }

    // Метод для вычисления периметра вырезанных клеток
    private static int calculatePerimeter(boolean[][] board) {
        int perimeter = 0;

        // Движения: вверх, вниз, влево, вправо
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j]) { // Если клетка вырезана
                    // Проверяем стороны каждой вырезанной клетки
                    for (int[] direction : directions) {
                        int newX = i + direction[0];
                        int newY = j + direction[1];

                        // Если соседняя клетка вне пределов доски или не вырезана, увеличиваем периметр
                        if (newX < 0 || newX >= 8 || newY < 0 || newY >= 8 || !board[newX][newY]) {
                            perimeter++;
                        }
                    }
                }
            }
        }
        return perimeter;
    }
}
