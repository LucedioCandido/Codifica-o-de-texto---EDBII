import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
import java.util.PriorityQueue;
import java.lang.Object;

public class Compressor {
    private Map<Character,Integer> tabelaFrequencia = new HashMap<Character, Integer>();//dicionario para a tabela de frequencia das letras no txt
    private Map<Character,String> tabelaCodificacao = new HashMap<Character, String>();
    private String caminho =  null;
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
        criaArvoreCodificacao();//Cria a árvore de codificação
        while (minHeap.size() != 0){
            minHeap.poll();
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
            n.setRight(Right);
            minHeap.add(n);
        }
        if(minHeap.size() == 1){
            Node raiz = new Node(minHeap.poll());
            raiz.setRoot(true);
            minHeap.add(raiz);
        }
    }

    public void criaTabelaCodificacao(){
        Set<Character> chaves = tabelaFrequencia.keySet();
        for (Character chave : chaves) {
            caminho = getCaminho(minHeap.peek(),caminho,chave);
            tabelaCodificacao.put(chave,caminho);
        }
    }

    // a leitura é sempre do raiz da arvore até uma folha, a cada nova chamada raiz -> um no folha.
    // achou a folha, remove ela e retorna o percurso. Se chegou em
    // um no folha que não é um caracter, seta como null removendo da arvore.
    //
    public String getCaminho(Node raiz, String caminho, Character letter){

        //se tem caminhos possíveis
        if(raiz.getLeft()!=null){
            caminho+="0";
            getCaminho(raiz.getLeft(),caminho,letter);
        }else if(raiz.getRight()!=null){
            caminho+= "1";
            getCaminho(raiz.getRight(),caminho,letter);
        }
        //a partir daqui nao tem nos a esquerda nem direita, se o no folha tem o caracter prucurado,
        // retorna a string com o caminho e seta o nod como null; Se não for o procurado, for um nó sem caracter, remove
        if(letter==(char)raiz.getLetter()){
            raiz = null;
            return caminho;
        }else if (raiz.getLetter()==0){//0 é o nosso identificardor
            raiz = null;
        }
        return null;
    }

    public void print(){
        //aqui percorre o dicionario  com a tabela de codicação gerada
        Set<Character> chaves = tabelaCodificacao.keySet();
        for (Character chave : chaves) {
            System.out.println(chave+": "+tabelaCodificacao.get(chave));
        }
    }

    public void codificaTexto(String txt) {

    }

}