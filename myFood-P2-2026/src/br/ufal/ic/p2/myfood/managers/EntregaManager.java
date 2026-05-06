package br.ufal.ic.p2.myfood.managers;

import br.ufal.ic.p2.myfood.modelos.Entrega.Entrega;
import br.ufal.ic.p2.myfood.modelos.Pedidos.Pedido;
import br.ufal.ic.p2.myfood.modelos.Usuario.Usuario;
import br.ufal.ic.p2.myfood.modelos.Usuario.Entregador;
import br.ufal.ic.p2.myfood.modelos.Empresa.Empresa;
import br.ufal.ic.p2.myfood.modelos.Empresa.Farmacia;
import br.ufal.ic.p2.myfood.exceptions.*;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class EntregaManager {
    private List<Entrega> entregasList;
    private int proximoId;

    private PedidoManager pedidoManager;
    private EmpresaManager empresaManager;
    private UsuarioManager usuarioManager;

    public EntregaManager(PedidoManager pedidoManager, EmpresaManager empresaManager, UsuarioManager usuarioManager) {
        this.pedidoManager = pedidoManager;
        this.empresaManager = empresaManager;
        this.usuarioManager = usuarioManager;
        carregarDados();
    }

    @SuppressWarnings("unchecked")
    private void carregarDados(){
        try (XMLDecoder decoder = new XMLDecoder(new FileInputStream("entregas.xml"))){
            this.entregasList = (List<Entrega>) decoder.readObject();
            this.proximoId = (int) decoder.readObject();
        } catch (Exception e){
            this.entregasList = new ArrayList<>();
            this.proximoId = 1;
        }
    }

    public void zerarDados(){
        this.entregasList.clear();
        this.proximoId = 1;
        File arquivo = new File("entregas.xml");
        if(arquivo.exists()) arquivo.delete();
    }

    public void salvarDados(){
        try(XMLEncoder encoder = new XMLEncoder(new FileOutputStream("entregas.xml"))){
            encoder.writeObject(this.entregasList);
            encoder.writeObject(this.proximoId);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private int gerarNovoId(){
        return proximoId++;
    }

    public int obterPedido(int entregadorId) throws Exception{
        Usuario usuario = usuarioManager.getUsuario(entregadorId);
        if(!(usuario instanceof Entregador)) throw new UsuarioNaoEntregadorException();

        List<Empresa> empresasEntregador = empresaManager.getListaEmpresasEntregador(entregadorId);
        if(empresasEntregador.isEmpty()) throw new EntregadorNaoEmpresaException();

        Pedido pedidoFarmaciaAntigo = null;
        Pedido pedidoAntigo = null;

        List<Pedido> pedidosProntos = pedidoManager.getPedidosEstado("pronto");

        for (Pedido p : pedidosProntos){
            boolean entregadorTrabalhaEmpresa = false;
            Empresa empresaPedido = null;

            for(Empresa e : empresasEntregador){
                if(e.getId() == p.getEmpresaId()){
                    entregadorTrabalhaEmpresa = true;
                    empresaPedido = e;
                    break;
                }
            }

            if(entregadorTrabalhaEmpresa){
                if(empresaPedido instanceof Farmacia){
                    if(pedidoFarmaciaAntigo == null || p.getNumero() < pedidoFarmaciaAntigo.getNumero()){
                        pedidoFarmaciaAntigo = p;
                    }
                }
                else{
                    if(pedidoAntigo == null || p.getNumero() < pedidoAntigo.getNumero()){
                        pedidoAntigo = p;
                    }
                }
            }
        }
        if(pedidoFarmaciaAntigo != null) return pedidoFarmaciaAntigo.getNumero();
        if(pedidoAntigo != null) return pedidoAntigo.getNumero();
        throw new NaoExistePedidoEntregaException();
    }
    public int crairEntrega(int pedidoId, int entregadorId, String destino) throws Exception{
        Pedido pedido = pedidoManager.getPedidoNum(pedidoId);
        if(!pedido.getEstado().equals("pronto")){
            throw new PedidoNaoProntoEntregaException();
        }

        Usuario usuario = usuarioManager.getUsuario(entregadorId);
        if(!(usuario instanceof  Entregador)){
            throw new NaoEntregadorvalidoException();
        }
        Entregador entregador = (Entregador) usuario;

        for(Entrega e : entregasList){
            if(e.getEntregador().equals(entregador.getNome())){
                Pedido p = pedidoManager.getPedidoNum(e.getPedido());
                if(p.getEstado().equals("entregando")){
                    throw new EntregadorEmEntregaException();
                }
            }
        }

        Usuario cliente = usuarioManager.getUsuario(pedido.getClienteId());
        Empresa empresa = empresaManager.getEmpresa(pedido.getEmpresaId());

        String destinoFinal = (destino == null || destino.isBlank() ? cliente.getEndereco() : destino);

        String formataProduto = pedidoManager.getPedidos(pedidoId,"produtos");

        Entrega newEntrega = new Entrega(
                gerarNovoId(), cliente.getNome(), empresa.getNome(),pedidoId,entregador.getNome(),
                destinoFinal,formataProduto
        );
        pedido.setEstado("entregando");
        entregasList.add(newEntrega);
        return newEntrega.getId();
    }

    public String getEntrega(int id, String atributo) throws Exception{
        Entrega fEntrega = null;
        for(Entrega e : entregasList){
            if(e.getId() == id){
                fEntrega = e;
                break;
            }
        }
        if(fEntrega == null) throw new EntregaNaoExisteException();
        if(atributo == null || atributo.isBlank()) throw new AtributoInvalidoException();

        switch (atributo){
            case "cliente": return fEntrega.getCliente();
            case "empresa": return fEntrega.getEmpresa();
            case "pedido": return String.valueOf(fEntrega.getPedido());
            case "entregador": return fEntrega.getEntregador();
            case "destino": return fEntrega.getDestino();
            case "produtos": return fEntrega.getProdutos();
            default: throw new AtributoNaoExisteException();
        }
    }

    public int getIdEntrega(int pedidoId) throws Exception{
        for (Entrega e: entregasList){
            if(e.getPedido() == pedidoId){
                return e.getId();
            }
        }
        throw new EntregaNaoExisteException();
    }

    public void entregar(int entregaId) throws Exception{
        Entrega fEntrega = null;
        for(Entrega e : entregasList){
            if(e.getId() == entregaId) {
                fEntrega = e;
                break;
            }
        }
        if(fEntrega == null){
            throw new NadaParaEntregueException();
        }
        Pedido pedido = pedidoManager.getPedidoNum(fEntrega.getPedido());
        pedido.setEstado("entregue");
    }
}
