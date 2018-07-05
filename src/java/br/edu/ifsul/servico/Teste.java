/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.servico;

import br.edu.ifsul.servicos.ServicoCorreio;
import com.google.gson.Gson;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.ws.rs.core.Response;

/**
 *
 * @author V_M_FT
 */
@SessionScoped
public class Teste implements Serializable {

    public static void main(String[] args) {
        Gson gson = new Gson();

        ServicoCorreio teste = new ServicoCorreio();
        correio co = new correio();

        co.setCepDestino("95985000");
        co.setCodigoServico("04510");
        co.setPeso("2.0");
        co.setValorDeclarado(100.00);

        Response res = teste.CalCorreio(co);

        if (res.getStatus() == 200) {
            System.out.println("retorno:" + res.readEntity(String.class));
        } else {
            System.out.println("Erro ao alterar");
        }
    }
}
