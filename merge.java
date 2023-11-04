import java.util.Random;
import java.util.Scanner;

public class merge {

    private int trocas;
    private int iteracoes;

    int retornaTrocas(){
        return trocas;
    }

    int retornaIteracoes(){
        return iteracoes;
    }

    void mergeMetades(int[] a, int[] b, int[] c, int tamanhoA, int tamanhoB) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < tamanhoA && j < tamanhoB) { // Passa todos os valores da metade para o array final
            iteracoes++;
            if (a[i] < b[j]) {
                c[k++] = a[i++];
                trocas++;
            } else {
                c[k++] = b[j++];
                trocas++;
            }
        }
        while (i < tamanhoA) { // Caso ainda haja valores na array esquerda, os manda para o array final
            iteracoes++;
            c[k++] = a[i++];
            trocas++;
        }
        while (j < tamanhoB) { // Caso ainda haja valores na array direita, os manda para o array final
            iteracoes++;
            c[k++] = b[j++];
            trocas++;
        }
    }

    void ordenaArray(int[] vetor, int tamanho) {
        if (tamanho <= 1) {
            return; // Encerra caso o array esteja ordenado
        }

        int meio = tamanho / 2; // Cria duas arrays que serão metade
        int[] arrayEsquerda = new int[meio]; // A primeira metade é a esquerda, a segunda é a direita
        int[] arrayDireita = new int[tamanho - meio];

        for (int i = 0; i < meio; i++) { // Passa os valores da primeira metade do array para a array esquerda
            arrayEsquerda[i] = vetor[i];
        }
        for (int i = meio; i < tamanho; i++) { // Passa os valores da segunda metade do array para a array direita
            arrayDireita[i - meio] = vetor[i];
        }

        ordenaArray(arrayEsquerda, meio); // Ordena a metade esquerda
        ordenaArray(arrayDireita, tamanho - meio); // Ordena a metade direita

        mergeMetades(arrayEsquerda, arrayDireita, vetor, meio, tamanho - meio); // Faz o merge das metades já ordenadas
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

        merge ordenar = new merge();
        ordenar.ordenaArray(vetor, tamanhoVetor);
        long execucaoFinal = System.nanoTime();

        System.out.println("Número de iterações: " + ordenar.retornaIteracoes());
        System.out.println("Número de trocas: " + ordenar.retornaTrocas());
        System.out.println("Tempo de execução: " + (execucaoFinal - execucaoInicio) + " nanossegundos");

        scanner.close();
    }
}
