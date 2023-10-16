import java.util.Scanner;


public class TesteHash {
    public static void main(String[] args) {
        Hash<Aluno> hash1 = new Hash<>(10);
        Aluno[] alunos = criarAlunos();

        for (Aluno aluno : alunos) {
            hash1.inserir(aluno.getId(), aluno);
        }

        System.out.println("--------Tabela--------");
        hash1.imprimir();
        System.out.println("Quantidade de itens: " + hash1.getQuantItens());
        long startHash1 = System.nanoTime();

        System.out.println("\n --------Busca--------");

        System.out.println(hash1.buscar(1));
        System.out.println(hash1.buscar(2));
        System.out.println(hash1.buscar(69));
        System.out.println(hash1.buscar(0));
        System.out.println(hash1.buscar(0));

        System.out.println("\n --------Remocao--------");

        System.out.println(hash1.remover(10));
        System.out.println(hash1.remover(5));
        System.out.println("\n");
        hash1.imprimir();



        long finishHash1 = System.nanoTime();
        long totalHash1 = finishHash1 - startHash1;
        System.out.println("\n Tempo: " + totalHash1);


    }

    static Aluno[] criarAlunos() {
        Aluno[] alunos = new Aluno[5];

        //Scanner scanner = new Scanner(System.in);
        //System.out.print("ID: ");
        //int ids = scanner.nextInt();
        //System.out.print("Nome: ");
        //String nomes = scanner.nextLine();
        //alunos[0] = new Aluno(ids, nomes);

        alunos[0] = new Aluno(10,  "ADS");
        alunos[1] = new Aluno(2,  "DSA");
        alunos[2] = new Aluno(78,  "SAD");
        alunos[3] = new Aluno(69,  "DAS");
        alunos[4] = new Aluno(124,  "QQQ");
        return alunos;
    }
}
