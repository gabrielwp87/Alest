package src.main.infra.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import src.main.interfaces.IFileReader;

public class FileReaderImplementation implements IFileReader {
    private Path path;
    private BufferedReader reader;

    public FileReaderImplementation(String filePath) throws Exception {
        path = Paths.get(filePath);
        try {
            reader = Files.newBufferedReader(path, Charset.defaultCharset());
        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: ", e);
            throw new Exception(e.toString());
        }
    }

    @Override
    public String[] readAllLines() throws Exception {
        String line = null;
        String[] lines = new String[9];
        int i = 0;
        try {

            while ((line = reader.readLine()) != null) {
                lines[i] = line;
            }

            reader.close();

            return lines;

        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: ", e);
            throw new Exception(e.toString());
        }
    }

}
