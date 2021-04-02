package com.gugawag.pdist.ejbs;

import com.gugawag.pdist.model.Mensagem;
import com.gugawag.pdist.model.Usuario;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class MensagemDAO {

    @PersistenceContext(unitName="default")
    private EntityManager em;

    public void inserir(Mensagem novaMensagem){

        em.persist(novaMensagem);
    }

    public List<Mensagem> listar() {

        return em.createQuery("FROM Mensagem").getResultList();
    }

    public Mensagem pesquisarPorId(long id){
        return (Mensagem) em.createQuery("FROM Mensagem msg WHERE m.id = :msgId")
                .setParameter("msgId", id).getSingleResult();
    }
}
