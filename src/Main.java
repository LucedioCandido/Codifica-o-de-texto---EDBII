import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Compressor compress = new Compressor();
        compress.criaTabelaFrequencia("testes/teste1.txt");
        compress.criaArvoreCodificacao();
        compress.criaTabelaCodificacao();
        compress.print();

    }
}