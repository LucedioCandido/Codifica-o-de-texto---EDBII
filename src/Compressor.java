import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
import java.util.PriorityQueue;

public class Compressor {
    private Map<Character,Integer> tabelaFrequencia = new HashMap<Character, Integer>();//dicionario para a tabela de frequencia das letras no txt
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
        while(!minHeap.isEmpty()) {
            System.out.println(minHeap.poll()); //Enquanto a lista não estiver vazia, chama o método toString da classe Node e imprime na tela
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

    }

    public void criaTabelaCodificacao(){

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
