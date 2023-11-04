import java.util.Random;
import java.util.Scanner;

public class quicksort {

    private int trocas;
    private int iteracoes;

    int retornaTrocas(){
        return trocas;
    }

    int retornaIteracoes(){
        return iteracoes;
    }

    void troca(int[] vetor, int i, int direito) { // Realiza a troca do valor atual do vetor pelo seu valor do lado direito
        int temp = vetor[i];
        vetor[i] = vetor[direito];
        vetor[direito] = temp;
        trocas++;
    }

    int particiona(int[] vetor, int inferior, int superior) { // função para encontrar posição do pivô
        int pivo = vetor[inferior];
        int esq = inferior + 1;
        int dir = superior;

        while (true) {
            while (esq <= dir && vetor[esq] <= pivo) { // incremente o ponteiro inferior (esquerdo) até que vetor[inferior] seja maior do que pivo
                esq++;
                iteracoes++;
            }

            while (esq <= dir && vetor[dir] >= pivo) { // decremente o ponteiro superior (direito) até que vetor[superior] <= direito
                dir--;
                iteracoes++;
            }

            if (esq <= dir) {
                troca(vetor, esq, dir); // Se (inferior < superior) então troque inferior com superior
            } else {
                break;
            }
        }

        troca(vetor, inferior, dir);
        return dir;
    }

    void quick(int[] vetor, int inferior, int superior) {
        int pivo;

        if (inferior >= superior) // o array está ordenado
            return;

        pivo = particiona(vetor, inferior, superior); // encontra a posição correta para o pivo, o pivo pode ser considerado, por exemplo, o primeiro elemento do array
        quick(vetor, inferior, pivo - 1); // executa o mesmo proced. sobre as subarrays à esquerda e à direita do pivo
        quick(vetor, pivo + 1, superior);
    }

    void insercao_direta(int x[], int n) { // n é o tamanho do array
        int i, k, y;
        for (k = 1; k < n; k++) {
            y = x[k];
            for (i = k - 1; i >= 0 && y < x[i]; i--) {
                x[i + 1] = x[i];
            }
            x[i + 1] = y;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Defina o tamanho do vetor em 50, 500, 1000, 5000 ou 10000");
        int tamanhoVetor = scanner.nextInt();

        int[] vetor = new int[tamanhoVetor];
        Random random = new Random();

        long execucaoInicio = System.nanoTime();
        for (int i = 0; i < tamanhoVetor; i++) {
            vetor[i] = random.nextInt(100);
        }

        quicksort ordenar = new quicksort();
        ordenar.quick(vetor, 0, tamanhoVetor - 1);
        long execucaoFinal = System.nanoTime();

        System.out.println("Número de iterações: " + ordenar.retornaIteracoes());
        System.out.println("Número de trocas: " + ordenar.retornaTrocas());
        System.out.println("Tempo de execução: " + (execucaoFinal - execucaoInicio) + " nanossegundos");

        scanner.close();
    }
}
