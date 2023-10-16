import java.util.Iterator;
import java.util.LinkedList;
@SuppressWarnings("unchecked")

class Aluno {
    private int id;
    private String nome;
    public Aluno(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    public int getId() {
        return id;
    }
    public String getNome(){
        return nome;
    }

    @Override
    public String toString() { //transforma a representacao padrao do java para string
        return "Aluno [id=" + id + ", nome=" + nome + "]";
    }

}
public class Hash<T> {
    private LinkedList<MeuNo>[] tabela;  // Array de listas encadeadas para armazenar os elementos
    private int quantItens;  // Número de itens na tabela

    // Classe interna para representar um nó (valor + chave)
    private class MeuNo {
        private T aluno;  // Valor do nó (aluno)
        private String id;  // Chave do nó (ID)

        public MeuNo(T aluno, String id) {
            this.aluno = aluno;
            this.id = id;
        }

        @Override
        public String toString() {
            return "["+ aluno + "]";
        }


        public T getAluno() {
            return aluno;
        }

        public String getId() {
            return id;
        }
    }

    public Hash(int tamanho) {
        this.quantItens = 0;
        this.tabela = (LinkedList<MeuNo>[]) new LinkedList[tamanho];  // Inicializa o array de listas encadeadas
    }

    // Verifica se a tabela está cheia
    private boolean estaQuaseCheia() {
        if (this.quantItens >= this.tabela.length * 0.75) {
            return true;
        } else {
            return false;
        }
    }

    // Retorna o tamanho da tabela
    private int getTamanho() {
        return this.tabela.length;
    }

    // Calcula a posição na tabela com base no ID usando uma função de espalhamento
    private int getPosicaoId(Object id) {

        // Verifica se o id é uma instância de String
        if (id instanceof String) {
            String auxStr = id.toString();  // Converte o id para String
            int auxInt = 0;

            // Calcula um valor baseado nos caracteres da String e suas posições
            for (int i = 5; i != -1; --i) {
                int ascii = (int) auxStr.charAt(i);  // Obtém o valor ASCII de um caractere
                auxInt += ascii * (2 * i);  // Multiplica o valor ASCII pela posição e atualiza auxInt
            }

            // Retorna a posição calculada baseada no tamanho da tabela
            return auxInt % this.getTamanho();

        } else if (id instanceof Integer) {
            // Retorna a posição calculada baseada no tamanho da tabela para um id do tipo Integer
            return ((int) id) % this.getTamanho();

        } else {
            System.out.println("Tipo de chave informada inválida!");  // Mensagem de erro para tipo de chave inválida
            return -1;  // Retorna -1 para indicar uma posição inválida
        }
    }


    // Dobra o tamanho da tabela
    private void dobrarTamanho() {
        int tamanho = this.tabela.length * 2;
        LinkedList<MeuNo>[] novaTabela = (LinkedList<MeuNo>[]) new LinkedList[tamanho];

        for (int i = 0; i < tamanho / 2; i++) {
            if (this.tabela[i] != null) {
                novaTabela[i] = this.tabela[i];
            }
        }
        this.tabela = novaTabela;
    }


    // Insere um par ID-aluno na tabela hash
    void inserir(Object id, T aluno) {
        if (this.estaQuaseCheia()) {
            this.dobrarTamanho();  // Dobra o tamanho se a tabela está quase cheia
        }

        int posicaoId = getPosicaoId(id);

        if(this.tabela[posicaoId] == null) {
            this.tabela[posicaoId] = new LinkedList<MeuNo>();
        }

        LinkedList<MeuNo> lista = this.tabela[posicaoId];

        lista.add(new MeuNo(aluno, id.toString()));
        this.quantItens++;
    }

    // Remove um elemento da tabela hash com base no ID
    T remover(Object id) {
        // Obtém a posição com base no id
        int posicaoId = getPosicaoId(id);

        // Verifica se a posição é inválida ou se a lista naquela posição é null
        if (posicaoId == -1 || this.tabela[posicaoId] == null) {
            return null;
        }

        // Obtém a lista na posição correspondente
        LinkedList<MeuNo> lista = this.tabela[posicaoId];

        // Verifica se a lista é null
        if (lista == null) {
            return null;
        }

        // Inicializa um iterator para percorrer a lista
        Iterator<MeuNo> iterator = lista.iterator();

        // Itera sobre a lista
        while (iterator.hasNext()) {
            MeuNo node = iterator.next();

            // Verifica se o id do nó é igual ao id fornecido
            if (node.getId().equals(id.toString())) {
                // Remove o nó da lista
                iterator.remove();
                // Atualiza a quantidade de itens na tabela
                this.quantItens--;
                // Retorna o aluno associado ao nó removido
                return node.getAluno();
            }
        }
        return null;
    }

    // Procura um elemento na tabela hash com base no ID
    T buscar(Object id) {
        int posicaoId = getPosicaoId(id);

        // Verifica se a posição é inválida ou se a lista naquela posição é null

        if (posicaoId == -1 || this.tabela[posicaoId] == null) {
            return null;
        }

        // Obtém a lista na posição correspondente
        LinkedList<MeuNo> lista = this.tabela[posicaoId];

        for (MeuNo node : lista) {
            if (node.getId().equals(id.toString())) {
                return node.getAluno();
            }
        }

        return null;
    }


    // Imprime
    void imprimir() {
        for (int i = 0; i < this.getTamanho(); i++) {
            System.out.println("[ " + this.tabela[i] + " ]");
        }
    }

    // Retorna a quantidade de itens na tabela
    public int getQuantItens() {
        return quantItens;
    }
}
