import java.util.Arrays;

public class MatrixMultiplication {
    // Matriz de adjacência representando quem segue quem
    static int[][] A = {
        {0, 1, 0, 1, 0}, // Alice
        {0, 0, 1, 0, 1}, // Bob
        {1, 0, 0, 1, 0}, // Carol
        {0, 1, 0, 0, 1}, // David
        {1, 0, 1, 0, 0}  // Paul
    };

    // Matriz resultante A^2
    static int[][] A2 = new int[A.length][A.length];

    // Nomes das pessoas
    static String[] names = {"Alice", "Bob", "Carol", "David", "Paul"};

    // Classe para paralelizar a multiplicação de uma linha pela matriz
    static class MultiplyRowThread extends Thread {
        private int row;

        // Construtor que recebe o índice da linha
        public MultiplyRowThread(int row) {
            this.row = row;
        }

        // Método que executa a multiplicação da linha pela matriz
        public void run() {
            long startTime = System.nanoTime(); // Tempo de início da thread
            for (int col = 0; col < A.length; col++) {
                int sum = 0;
                for (int k = 0; k < A.length; k++) {
                    sum += A[row][k] * A[k][col]; // Multiplicação e soma
                }
                A2[row][col] = sum; // Armazena o resultado na matriz resultante
            }
            long endTime = System.nanoTime(); // Tempo de término da thread
            System.out.println("Thread " + names[row] + " demorou " + (endTime - startTime) / 1e6 + " ms.");
        }
    }

    // Função que executa a multiplicação de matriz de forma paralela
    public static void multiplyMatrixInParallel() {
        MultiplyRowThread[] threads = new MultiplyRowThread[A.length];
        for (int i = 0; i < A.length; i++) {
            threads[i] = new MultiplyRowThread(i);
            threads[i].start(); // Inicia cada thread
        }

        // Aguarda todas as threads terminarem
        for (int i = 0; i < A.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Função para imprimir uma matriz
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    // I) Função para encontrar as duas pessoas com mais amigos em comum
    public static void findMostCommonFriends() {
        long startTime = System.nanoTime(); // Tempo de início
        int[] rowSums = new int[A.length];
        for (int i = 0; i < A2.length; i++) {
            for (int j = 0; j < A2.length; j++) {
                rowSums[i] += A2[i][j]; // Soma os valores da linha
            }
        }

        // Variáveis para armazenar os índices das duas pessoas com mais amigos em comum
        int first = -1, second = -1;
        for (int i = 0; i < rowSums.length; i++) {
            if (first == -1 || rowSums[i] > rowSums[first]) {
                second = first;
                first = i;
            } else if (second == -1 || rowSums[i] > rowSums[second]) {
                second = i;
            }
        }

        long endTime = System.nanoTime(); // Tempo de término
        System.out.println("As duas pessoas com mais amigos em comum são: " + names[first] + " e " + names[second]);
        System.out.println("Tempo para calcular amigos em comum: " + (endTime - startTime) / 1e6 + " ms\n");
    }

    // II) Função para encontrar a pessoa mais influente
    public static void findMostInfluentialPerson() {
        long startTime = System.nanoTime(); // Tempo de início
        int[] colSums = new int[A.length];
        for (int i = 0; i < A2.length; i++) {
            for (int j = 0; j < A2.length; j++) {
                colSums[j] += A2[i][j]; // Soma os valores da coluna
            }
        }

        // Identifica a pessoa mais influente
        int mostInfluential = 0;
        for (int i = 1; i < colSums.length; i++) {
            if (colSums[i] > colSums[mostInfluential]) {
                mostInfluential = i;
            }
        }

        long endTime = System.nanoTime(); // Tempo de término
        System.out.println("A pessoa mais influente é " + names[mostInfluential]);
        System.out.println("Tempo para calcular a pessoa mais influente: " + (endTime - startTime) / 1e6 + " ms\n");
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime(); // Tempo de início total

        // Multiplicação de matrizes em paralelo
        multiplyMatrixInParallel();

        long endTime = System.nanoTime(); // Tempo de término total
        System.out.println("\nMatriz resultante A^2:");
        printMatrix(A2);
        System.out.println("Tempo total para calcular A^2: " + (endTime - startTime) / 1e6 + " ms\n");

        // I) Encontrar as duas pessoas com mais amigos em comum
        findMostCommonFriends();

        // II) Encontrar a pessoa mais influente
        findMostInfluentialPerson();
    }
}
