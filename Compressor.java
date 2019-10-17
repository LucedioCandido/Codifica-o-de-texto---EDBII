import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.*;


public class Compressor {
    private Map<Character,Integer> tabelaFrequencia = new HashMap<Character, Integer>();//dicionario para a tabela de frequencia das letras no txt
    private PriorityQueue<Node> minHeap = new PriorityQueue<Node>(); //arvore de codificação

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
            Node no = new Node();
            minHeap.add(no);
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
