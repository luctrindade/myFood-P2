package br.ufal.ic.p2.myfood.Modelos.Empresa;

import br.ufal.ic.p2.myfood.Modelos.Usuario.*;
import br.ufal.ic.p2.myfood.Modelos.Exceptions.*;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class EmpresaManager {
    private List<Empresa> empresaList;
    private int proximoId;
    private UsuarioManager usuarioManager;

    public EmpresaManager(UsuarioManager usuarioManager){
        this.usuarioManager = usuarioManager;
        carregarDados();
    }

    @SuppressWarnings("unchecked")
    private void carregarDados(){
        try (XMLDecoder decoder = new XMLDecoder(new FileInputStream("empresas.xml"))){
            this.empresaList = (List<Empresa>) decoder.readObject();
            this.proximoId = (int) decoder.readObject();
        } catch (Exception e){
            this.empresaList = new ArrayList<>();
            this.proximoId = 1;
        }
    }

    public void zerarDados(){
        this.empresaList.clear();
        this.proximoId = 1;
        File arquivo = new File("empresas.xml");
        if(arquivo.exists()) arquivo.delete();
    }

    public void salvarDados(){
        try(XMLEncoder encoder = new XMLEncoder(new FileOutputStream("empresas.xml"))){
            encoder.writeObject(this.empresaList);
            encoder.writeObject(this.proximoId);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public int criarEmpresas(String tipoEmpresa, int donoId, String nome,String endereco, String tipoCozinha) throws Exception{
        if(nome == null || nome.isBlank()) throw new NomeInvalidoException();
        if(endereco == null || endereco.isBlank()) throw new EnderecoInvalidoException();
        Usuario usuario = usuarioManager.getUsuario(donoId);
        if(!(usuario instanceof Dono)){
            throw new UsuarioNaoPodeCriarEmpresaException();
        }

        for (Empresa e : empresaList){
            if(e.getNome().equals(nome)){
                if(e.getDonoId() != donoId){
                    throw new EmpresaJaExisteException();
                }
                else if(e.getEndereco().equals(endereco)){
                    throw new EmpresaMesmoLocalException();
                }
            }
        }

        if(tipoEmpresa.equals("restaurante")){
            Restaurante novoRestaurante = new Restaurante(proximoId,donoId,nome,endereco,tipoCozinha);
            empresaList.add(novoRestaurante);
        }

        int fId = proximoId;
        proximoId++;
        return fId;
    }

    public String getEmpresaDoUsuario(int donoId) throws Exception{
        Usuario usuario = usuarioManager.getUsuario(donoId);
        if(!(usuario instanceof Dono)){
            throw new UsuarioNaoPodeCriarEmpresaException();
        }
        StringBuilder sb = new StringBuilder("{[");
        boolean first = true;

        for(Empresa e : empresaList){
            if(e.getDonoId() == donoId){
                if(!first) sb.append(", ");
                sb.append("[").append(e.getNome()).append(", ").append(e.getEndereco()).append("]");
                first = false;
            }
        }
        sb.append("]}");
        return sb.toString();
    }

    public int getEmpresaId(int donoId, String nome, int indice) throws Exception{
        if(nome == null || nome.isBlank()) throw new NomeInvalidoException();
        if (indice < 0) throw new IndiceInvalidoException();
        List<Empresa> filtro = new ArrayList<>();

        for(Empresa e: empresaList){
            if(e.getDonoId() == donoId && e.getNome().equals(nome)){
                filtro.add(e);
            }
        }

        if(filtro.isEmpty()) throw new EmpresaNaoExisteException();
        if(indice >= filtro.size()) throw new IndiceMaiorQueEsperadoException();

        return filtro.get(indice).getId();
    }

    public String getAtributoEmpresa(int empresaId, String atributo) throws Exception{
        Empresa findEmpresa = null;
        for(Empresa e : empresaList){
            if(e.getId() == empresaId){
                findEmpresa = e;
                break;
            }
        }
        if(findEmpresa == null) throw new EmpresaNaoCadastradaException();

        if(atributo == null || atributo.isBlank()){
            throw new AtributoInvalidoException();
        }

        switch(atributo){
            case "nome":
                return findEmpresa.getNome();
            case "endereco":
                return findEmpresa.getEndereco();
            case "dono":
                Usuario dono = usuarioManager.getUsuario(findEmpresa.getDonoId());
                return dono.getNome();
            case "tipoCozinha":
                if(findEmpresa instanceof Restaurante){
                    return ((Restaurante) findEmpresa).getTipoCozinha();
                }
                throw new AtributoInvalidoException();
            default:
                throw new AtributoInvalidoException();
        }
    }
}
