package br.edu.ifsul.servico;

import br.edu.ifsul.servicos.ServicoCorreio;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.core.Response;

/**
 *
 * @author V_M_FT
 */
@Named(value = "controle")
@SessionScoped
public class Controle implements Serializable {

    private correio objeto = new correio();

    public Controle() {
    }

    public void buscar() {
   //     System.out.println(" CHEGOU AQUI /n/n\n\n\n");

        ServicoCorreio dados = new ServicoCorreio();
        Response resposta = dados.CalCorreio(objeto);
        Integer status = resposta.getStatus();
        System.out.println("STATUS" + status);
        switch (status) {
            case 200:
                correio volta = dados.CalCorreio(objeto).readEntity(correio.class);
                objeto.setPrazoEntrega(volta.getPrazoEntrega());
                objeto.setValorFrete(volta.getValorFrete());
                Util.mensagemInformacao("Frete Calculado com Sucesso !!!");
                break;
            case 302:
                System.out.println("ENTROU NO CASE 302");
                Util.mensagemErro("CEP de destino invalido");
                objeto.setPrazoEntrega(null);
                objeto.setValorFrete(null);
                break;
            case 403:
                System.out.println("ENTROU NO CASE 403");
                Util.mensagemErro("Peso excedido.");
                objeto.setPrazoEntrega(null);
                objeto.setValorFrete(null);

                break;
            case 500:
                System.out.println("ENTROU NO CASE 500");
                Util.mensagemErro("Serviço indisponível para esse CEP ");
                objeto.setPrazoEntrega(null);
                objeto.setValorFrete(null);
                break;

            case 505:
                System.out.println("ENTROU NO CASE 505");
                Util.mensagemErro("O Peso deve ser Informado!");
                objeto.setPrazoEntrega(null);
                objeto.setValorFrete(null);
                break;

            case 410:
                System.out.println("ENTROU NO CASE 410");
                Util.mensagemErro("O Valor Declarado nao deve ser menor que R$ 18,50.");
                objeto.setPrazoEntrega(null);
                objeto.setValorFrete(null);
                break;
              default:
                   Util.mensagemErro("Não foi possivel processar sua solicitação, favor rever os dados enviados Valor Declarado nao deve ser menor que R$ 18,50.");
                  
        }

    }

    public correio getObjeto() {
        return objeto;
    }

    public void setObjeto(correio objeto) {
        this.objeto = objeto;
    }

}
