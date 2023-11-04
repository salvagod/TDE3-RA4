import java.util.Random;
import java.util.Scanner;

public class bubble {
    private int trocas;
    private int iteracoes;

    void bubbleSort(int[] vetor, int tamanho){
        int n = tamanho;
        int temp = 0;

        for(int i = 0; i < n; i++){ // Ordena os valores
            for(int j = 1; j < (n - i); j ++){
                iteracoes++;

                if(vetor[j-1] > vetor[j]){ // Caso o valor da esquerda seja maior que o valor da direita
                    temp = vetor[j-1]; // O valor da esquerda fica armazenado numa variável temporária
                    vetor[j-1] = vetor[j]; // O valor da esquerda recebe o da direita
                    vetor[j] = temp; // O da direita recebe o valor da esquerda (temporário)
                    trocas++;
                }
            }
        }
    }

    int retornaIteracoes(){
        return iteracoes;
    }

    int retornaTrocas(){
        return trocas;
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

        bubble ordenar = new bubble();
        ordenar.bubbleSort(vetor, tamanhoVetor);

        long execucaoFinal = System.nanoTime();

        System.out.println("Número de iterações: " + ordenar.retornaIteracoes());
        System.out.println("Número de trocas: " + ordenar.retornaTrocas());
        System.out.println("Tempo de execução: " + (execucaoFinal - execucaoInicio) + " nanossegundos");

        scanner.close();
    }

}