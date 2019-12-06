import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
import java.util.PriorityQueue;
import java.lang.Object;

public class Compressor<Private> {
    private Map<Character,Integer> tabelaFrequencia = new HashMap<Character, Integer>();//dicionario para a tabela de frequencia das letras no txt
    private Map<Character,String> tabelaCodificacao = new HashMap<Character, String>();
    private Map<Character,Integer> tabelaFrequenciaOrdenada = new HashMap<Character, Integer>();
    private  ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();
    private ArrayList<Integer> noVisitado = new ArrayList<Integer>(); //Cria uma lista para salvar os nós já visitados
    private PriorityQueue<Node> minHeap = new PriorityQueue<Node>(new Comparator<Node>() {
        @Override
        public int compare(Node node1, Node node2) {
            return Integer.valueOf(node1.getCount()).compareTo(node2.getCount());//Define prioridade do menor para o maior na lista
        }
    }); //arvore de codificação
    private final int quebraLinha = 13; //Número referente a quebra de linha na tabela ASCII

    public void criaTabelaFrequencia(String entrada) throws IOException{
        File file = new File(entrada);
        FileReader input = new FileReader(file);
        BufferedReader leitura  = new BufferedReader(input);
        String linha = leitura.readLine();

        while(linha!=null){
            char[] linha_vez = linha.toCharArray();
            for (int i = 0; i<linha.length();i++){
                verificExistenciaLetraNoDicionario(linha_vez[i]);
            }
            linha = leitura.readLine();
            //Identifica se existe uma nova linha, se existir, adiciona a quebra de linha a Tabela de Frequencia
            if(linha!=null){
                verificExistenciaLetraNoDicionario((char)quebraLinha); //Forçando o número da quebra de linha a virar um char
            }
        }
    }

    public void verificExistenciaLetraNoDicionario(Character c){
        if (tabelaFrequencia.containsKey(c)) {
            tabelaFrequencia.put(c,tabelaFrequencia.get(c).intValue()+1);
        }else{
            tabelaFrequencia.put(c,1);
        }

    }

    public void criaArvoreCodificacao(){
        Set<Character> chaves = tabelaFrequencia.keySet();
        for (Character chave : chaves) {
            tabelaFrequencia.get(chave);
            Node no = new Node((int)chave, tabelaFrequencia.get(chave)); //Cria um no passando o valor na tabela ASCII de chave e o valor correspondente a chave
            minHeap.add(no); //Adiciona o no criado a lista de prioridade
        }
        while(minHeap.size() >= 2){
            Node Left = new Node(minHeap.poll());
            Node Right = new Node(minHeap.poll());
            Node n = new Node(0, Left.getCount()+Right.getCount());
            n.setLeft(Left);
            n.getLeft().setParent(n);
            n.setRight(Right);
            n.getRight().setParent(n);
            minHeap.add(n);
            if(Left.getLetter() != 0){
                tabelaFrequenciaOrdenada.put((char)Left.getLetter(), Left.getCount());
            }
            if(Right.getLetter() !=0){
                tabelaFrequenciaOrdenada.put((char)Right.getLetter(), Right.getCount());
            }
        }
        if(minHeap.size() == 1){
            Node raiz = new Node(minHeap.poll());
            raiz.setRoot(true);
            minHeap.add(raiz);
        }
        arvore.insert(minHeap.peek());
    }

    public void criaTabelaCodificacao(){
        noVisitado.add(500); //Adiciona um número que não pode ser letra apenas para iniciar a lista
        Set<Character> chaves = tabelaFrequenciaOrdenada.keySet();
        for (Character chave : chaves) {
            String caminho = " ";
            caminho = getCaminho(arvore.getRoot(),caminho,chave);// elemento é a raiz da AB, a ideia era sempre percorrer a partir dela.
            if(caminho == null){
                caminho = getCaminho(arvore.getRoot(), " ", chave);
            }
            tabelaCodificacao.put(chave,caminho);//add na tabela de codificação o caracter e o codigo binario
        }
    }

    // a leitura é da raiz até uma folha, achou a folha, remove ela e retorna o percurso. Se chegou em
    // um no folha que não contém um caracter, removendo da arvore.
    public String getCaminho(Node raiz, String caminho, int letter){
        //se tem caminhos possíveis
        if(raiz.getLeft()!=null){
            caminho += "0";
            if(raiz.getLeft().getLetter() == letter){
                raiz.setLeft(null);
                return caminho;
            } else {
                return getCaminho(raiz.getLeft(), caminho, letter);
            }
        } else if(raiz.getRight()!=null){
            caminho += "1";
            if(raiz.getRight().getLetter() == letter){
                raiz.setRight(null);
                return caminho;
            } else {
                return getCaminho(raiz.getRight(), caminho, letter);
            }
        } else{
            raiz.setParent(null);
            return null;
        }
    }

    public void codificaTexto(String txt) {
    //
    }

    public void print(){
        //aqui percorre o dicionario  com a tabela de codicação gerada
        System.out.println("Tabela de codificação:");
        Set<Character> chaves = tabelaCodificacao.keySet();
        for (Character chave : chaves) {
            System.out.println(chave+":"+tabelaCodificacao.get(chave));
        }
    }
}