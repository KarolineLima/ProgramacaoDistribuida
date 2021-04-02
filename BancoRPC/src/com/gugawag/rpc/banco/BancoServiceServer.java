package com.gugawag.rpc.banco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BancoServiceServer extends UnicastRemoteObject implements BancoServiceIF {

    private Map<String, Double> saldoContas;

    private List<Conta> contas = new ArrayList<>();

    public BancoServiceServer() throws RemoteException {
        saldoContas = new HashMap<>();
        saldoContas.put("1", 100.0);
        saldoContas.put("2", 156.0);
        saldoContas.put("3", 950.0);
    }

    @Override
    public double saldo(String conta) throws RemoteException {
        if(saldoContas.get(conta) == null){
            for(Conta c: contas){
                if(c.getNumero() == conta){
                    return c.getSaldo();
                }
            }


        }
        return saldoContas.get(conta);
    }

    @Override
    public int quantidadeContas() throws RemoteException {
        return saldoContas.size() + contas.size();
    }

    @Override
    public void adicionarConta(Conta conta) throws RemoteException{
        contas.add(conta);
    }

}
