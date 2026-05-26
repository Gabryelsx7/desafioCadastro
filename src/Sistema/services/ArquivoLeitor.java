package Sistema.services;

import java.io.*;

public class ArquivoLeitor {
    void leitorperguntas() {
        File file = new File("C:\\Users\\gabryel\\Desktop\\Sistema de Cadastro\\src\\formulario.txt");
        try(FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr)) {
            System.out.print(br.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
