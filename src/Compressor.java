import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
import java.util.PriorityQueue;
import java.lang.Object;

public class Compressor {
    private Map<Character,Integer> tabelaFrequencia = new HashMap<Character, Integer>();//dicionario para a tabela de frequencia das letras no txt
    private PriorityQueue<Node> minHeap = new PriorityQueue<Node>(new Comparator<Node>() {
        @Override
        public int compare(Node node1, Node node2) {
            return Integer.valueOf(node1.getCount()).compareTo(node2.getCount());//Define prioridade do menor para o maior na lista
        }
    }); //arvore de codificação
    private Map<Character,Integer> tabelaCodificacao = new HashMap<Character, Integer>();//Dicionário para a tabela de codificação
    private final int quebraLinha = 13; //Número referente a quebra de linha na tabela ASCII
    public int qteLeaf = 0; //Conta a quantidade de nós folhas da árvore

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
        criaTabelaCodificacao(minHeap.peek());//Cria a tabela de codificação
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
            no.setLeaf(true);
            minHeap.add(no); //Adiciona o no criado a lista de prioridade
            qteLeaf++;
        }
        while(minHeap.size() >= 2){
            Node Left = new Node(minHeap.poll());
            Node Right = new Node(minHeap.poll());
            Node n = new Node(0, Left.getCount()+Right.getCount());
            Left.setPai(n);
            Right.setPai(n);
            n.setLeft(Left);
            n.setRight(Right);
            n.setRoot(true);
            minHeap.add(n);
        }
    }

    public void criaTabelaCodificacao(Node raiz) {
        PriorityQueue<Node> noVisitado = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                return Integer.valueOf(node1.getCount()).compareTo(node2.getCount());//Define prioridade do menor para o maior na lista
            }
        });
        int valorBinario = 0;
        int iterador = 0;
        int[] binario = new int[];
        while (qteLeaf > 0) {
            if (raiz.isLeaf()) {
                qteLeaf -= 1;
                noVisitado.add(raiz);
                valorBinario = calculoBinario(binario);
                tabelaCodificacao.put((char) raiz.getLetter(), valorBinario);
            } else {
                if (noVisitado.contains(raiz)) {
                    criaTabelaCodificacao(raiz.getPai());
                } else {
                    if (noVisitado.contains(raiz.getLeft())) {
                        binario[iterador] = 1;
                        iterador++;
                        criaTabelaCodificacao(raiz.getRight());
                    } else {
                        binario[iterador] = 0;
                        iterador++;
                        criaTabelaCodificacao(raiz.getLeft());
                    }
                }
            }
        }
    }

    public int calculoBinario(int[] valor){
        int chaveBinaria = 0;
        for(int i= valor.length; i>-1; i--){
            chaveBinaria += (valor[i]*i);
        }
        return chaveBinaria;
    }

    public void codificaTexto(String txt) {

    }

    public void print(){
        Set<Character> chaves = tabelaFrequencia.keySet();
        for (Character chave : chaves) {
            if (chave != null){
                System.out.println(chave + tabelaFrequencia.get(chave).toString());
            }
        }
    }

}
