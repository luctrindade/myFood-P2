package br.ufal.ic.p2.myfood.managers;

import br.ufal.ic.p2.myfood.modelos.Empresa.Empresa;
import br.ufal.ic.p2.myfood.modelos.Empresa.Farmacia;
import br.ufal.ic.p2.myfood.modelos.Empresa.Mercado;
import br.ufal.ic.p2.myfood.modelos.Empresa.Restaurante;
import br.ufal.ic.p2.myfood.modelos.Usuario.*;
import br.ufal.ic.p2.myfood.exceptions.*;

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

    private int gerarNovoId(){
        int fId = proximoId;
        proximoId++;
        return fId;
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

    public int criarEmpresas(String tipoEmpresa, int donoId, String nome,String endereco, String abre, String fecha, String tipoMercado) throws Exception{
        if(tipoEmpresa == null || tipoEmpresa.isBlank() || !tipoEmpresa.equals("mercado")) throw new TipoEmpresaInvalidoException();
        if(nome == null || nome.isBlank()) throw new NomeInvalidoException();
        if(endereco == null || endereco.isBlank()) throw new EnderecoEmpresaInvalidoException();

        if(tipoMercado == null || tipoMercado.isEmpty() || (!tipoMercado.equals("supermercado")
                && !tipoMercado.equals("minimercado") && !tipoMercado.equals("atacadista"))){
            throw new TipoMercadoInvalidoException();
        }

        validarHorario(abre,fecha);

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

        Mercado novo = new Mercado(gerarNovoId(),donoId,nome,endereco,abre,fecha,tipoMercado);
        empresaList.add(novo);
        return novo.getId();
    }

    public int criarEmpresas(String tipoEmpresa, int donoId, String nome,String endereco, Boolean aberto24, int numeroFuncionarios) throws Exception{
        if(tipoEmpresa == null || tipoEmpresa.isBlank() || !tipoEmpresa.equals("farmacia")) throw new TipoEmpresaInvalidoException();
        if(nome == null || nome.isBlank()) throw new NomeInvalidoException();
        if(endereco == null || endereco.isBlank()) throw new EnderecoEmpresaInvalidoException();

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

        Farmacia novo = new Farmacia(gerarNovoId(),donoId,nome,endereco,aberto24,numeroFuncionarios);
        empresaList.add(novo);
        return novo.getId();
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
            case "tipoMercado":
                if(findEmpresa instanceof Mercado){
                    return ((Mercado) findEmpresa).getTipoMercado();
                }
            case "abre":
                if(findEmpresa instanceof Mercado){
                    return ((Mercado) findEmpresa).getAbre();
                }
                throw new AtributoInvalidoException();
            case "fecha":
                if(findEmpresa instanceof Mercado){
                    return ((Mercado) findEmpresa).getFecha();
                }
                throw new AtributoInvalidoException();
            case "aberto24Horas":
                if(findEmpresa instanceof Farmacia){
                    return ((Farmacia) findEmpresa).getAberto24().toString();
                }
                throw new AtributoInvalidoException();
            case "numeroFuncionarios":
                if(findEmpresa instanceof Farmacia){
                    return String.valueOf(((Farmacia) findEmpresa).getNumeroFuncionarios());
                }
                throw new AtributoInvalidoException();
            default:
                throw new AtributoInvalidoException();
        }
    }

    private void validarHorario(String abre, String fecha) throws Exception{
        if(abre == null || fecha == null){
            throw new HorarioInvalidoException();
        }

        if(!abre.matches("^\\d{2}:\\d{2}$") || !fecha.matches("^\\d{2}:\\d{2}$")){
            throw new FormatoHoraInvalidoException();
        }

        try{
            java.time.LocalTime horaAbre = java.time.LocalTime.parse(abre);
            java.time.LocalTime horaFecha = java.time.LocalTime.parse(fecha);

            if(horaAbre.isAfter(horaFecha) || horaAbre.equals(horaFecha)){
                throw new HorarioInvalidoException();
            }
        } catch(java.time.format.DateTimeParseException e){
            throw new HorarioInvalidoException();
        }
    }

    public void alterarFuncionamento(int empresaId, String abre, String fecha) throws Exception{
        validarHorario(abre,fecha);

        Empresa fEmpresa = null;
        for(Empresa e : empresaList){
            if(e.getId() == empresaId){
                fEmpresa = e;
                break;
            }
        }

        if(!(fEmpresa instanceof Mercado)){
            throw new NaoEUmMercadoValidoException();
        }

        Mercado mercado = (Mercado) fEmpresa;
        mercado.setAbre(abre);
        mercado.setFecha(fecha);
    }
}
