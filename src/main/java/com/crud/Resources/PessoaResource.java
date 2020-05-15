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
public class PessoaResource {
    @Inject
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pessoa> findPessoas(){
        return em.createQuery("select p from Pessoa p", Pessoa.class).getResultList();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Pessoa findPessoasById(@PathParam("id") Long id){
        Pessoa pessoa = em.find(Pessoa.class, id);
        return pessoa;
    }

    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Pessoa addPessoa(Pessoa pessoa){
//        try {
//
//        } catch (XException ex) {
//
//        } catch (YException ey) {
//
//        } finally {
//
//        }
        em.persist(pessoa);
        return pessoa;
    }

    @Path("/{id}")
    @PUT
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response editPessoa(@PathParam("id") Long id, Pessoa pessoaNewData) {
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
    }


}

