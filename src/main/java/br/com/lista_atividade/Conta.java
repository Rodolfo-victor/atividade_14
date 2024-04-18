package br.com.lista_atividade;

import java.io.*;

public class Conta {
    public static void main(String[] args) {
        ContaBancaria conta = new ContaBancaria(123, 1000.0);
        System.out.println("Conta antes da serialização: " + conta);

        try (FileOutputStream fos = new FileOutputStream("conta.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(conta);
            System.out.println("Conta serializada com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao serializar a conta: " + e.getMessage());
        }

        ContaBancaria contaDeserializada = null;
        try (FileInputStream fis = new FileInputStream("conta.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            contaDeserializada = (ContaBancaria) ois.readObject();
            System.out.println("Conta deserializada com sucesso: " + contaDeserializada);
            contaDeserializada.setSaldo(contaDeserializada.getSaldo() + 500.0);
            System.out.println("Conta atualizada: " + contaDeserializada);

            try (FileOutputStream fos = new FileOutputStream("conta.dat");
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(contaDeserializada);
                System.out.println("Conta atualizada serializada com sucesso.");
            } catch (Exception e) {
                System.err.println("Erro ao serializar a conta atualizada: " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Erro ao deserializar a conta: " + e.getMessage());
        }
    }
}
