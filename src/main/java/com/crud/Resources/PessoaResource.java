package com.crud.Resources;

import com.crud.models.Pessoa;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/pessoas")
@Transactional
@Consumes(value = MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class PessoaResource {
    @Inject
    EntityManager em;

    @GET
    public List<Pessoa> findPessoas(){
        return em.createQuery("select p from Pessoa p", Pessoa.class).getResultList();
    }

    @Path("/{id}")
    @GET
    public Pessoa findPessoasById(@PathParam("id") Long id){
        Pessoa pessoa = em.find(Pessoa.class, id);
        return pessoa;
    }

    @POST
    public Pessoa addPessoa(Pessoa pessoa) throws Exception {
        try {
            em.persist(pessoa);
            return pessoa;
        } catch (Exception e ) {
            throw e;
        }
    }

    @Path("/{id}")
    @PUT
    public Response editPessoa(@PathParam("id") Long id, Pessoa pessoaNewData) {
        try {
            Pessoa pessoa = em.find(Pessoa.class, id);
            JsonObject err = Json.createObjectBuilder().add("Error", "Id not found.").build();

            if(pessoa == null) {
                return Response.ok(err).status(404).build();
            }

            pessoa.setNome(pessoaNewData.getNome());
            pessoa.setSobrenome(pessoaNewData.getSobrenome());
            pessoa.setEmail(pessoaNewData.getEmail());
            pessoa.setTelefone(pessoaNewData.getTelefone());
            pessoa.setEndereco(pessoaNewData.getEndereco());
            pessoa.setCpf(pessoaNewData.getCpf());
            return Response.ok(pessoa).build();
        } catch (Exception e ) {
            throw e;
        }

    }

    @DELETE
    @Path("/{id}")
    public Response deletePessoa(@PathParam("id") Long id) {
        Pessoa pessoa = em.find(Pessoa.class, id);
        if(pessoa == null) {
            System.out.println("-----------------Pessoa: " + pessoa);
            JsonObject err = Json.createObjectBuilder().add("Error", "Id not found.").build();
            return Response.ok(err).status(404).build();
        } else {
            em.remove(pessoa);
            Response.status(200).build();
        }
        return Response.ok().build();
    }

}

