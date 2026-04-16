package br.ufal.ic.p2.myfood.Modelos.Usuario;

import br.ufal.ic.p2.myfood.Modelos.Exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class UsuarioManager {
    List<Usuario> usuarioList;
    private int proximoID;
    public UsuarioManager(){
        carregarDados();
    }

    @SuppressWarnings("unchecked")
    private void carregarDados(){
        try (XMLDecoder decoder = new XMLDecoder(new FileInputStream("usuarios.xml"))){
            this.usuarioList = (List<Usuario>) decoder.readObject();
            this.proximoID = (int) decoder.readObject();

        } catch (Exception e){
            this.usuarioList = new ArrayList<>();
            this.proximoID = 1;
        }
    }

    private void validarDados(String nome, String email, String senha, String endereco){
        if(nome == null || nome.isBlank())
            throw new NomeInvalidoException();
        if(email == null || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") || email.isBlank() )
            throw new EmailInvalidoException();
        if(senha == null || senha.isBlank())
            throw new SenhaInvalidaException();
        if(endereco == null || endereco.isBlank())
            throw new EnderecoInvalidoException();
    }

    public void salvarDados(){
        try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream("usuarios.xml"))){
            encoder.writeObject(this.usuarioList);
            encoder.writeObject(this.proximoID);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf)
    throws NomeInvalidoException, EmailInvalidoException, SenhaInvalidaException, EnderecoInvalidoException, CpfInvalidoException, UsuarioJaExisteException {
        validarDados(nome,email,senha,endereco);
        if(cpf == null || cpf.isBlank() || cpf.length() != 14){
            throw new CpfInvalidoException();
        }
        for(Usuario usuario : this.usuarioList){
            if(usuario.getEmail().equals(email)){
                throw new UsuarioJaExisteException();
            }
            if(usuario instanceof Dono){
                if(((Dono) usuario).getCpf().equals(cpf))
                    throw new CpfInvalidoException();
            }
        }
        this.usuarioList.add(new Dono(proximoID,nome, email, senha, endereco,cpf));
        proximoID ++;
    }
    public void criarUsuario(String nome, String email, String senha, String endereco)
            throws NomeInvalidoException, EmailInvalidoException, SenhaInvalidaException, EnderecoInvalidoException, UsuarioJaExisteException {

        validarDados(nome,email,senha,endereco);
        for(Usuario usuario : this.usuarioList){
            if(usuario.getEmail().equals(email)){
                throw new UsuarioJaExisteException();
            }
        }
        this.usuarioList.add(new Cliente(proximoID,nome, email, senha, endereco));
        proximoID++;
    }

    public String getAtributoUsuario(int id, String atributo) throws Exception{
        Usuario usuario = null;
        for(Usuario u : usuarioList){
            if(u.getId() == id){
                usuario=u;
                break;
            }
        }
        if(usuario == null){
            throw new UsuarioNaoExisteException();
        }

        switch (atributo){
            case "nome":
                return usuario.getNome();
            case "email":
                return usuario.getEmail();
            case "endereco":
                return usuario.getEndereco();
            case "cpf":
                if(usuario instanceof Dono)
                    return ((Dono) usuario).getCpf();
                else
                    throw new Exception("Atributo invalido");

            case "senha":
                return usuario.getSenha();

            default:
                throw new Exception("Atributo Invalido");
        }
    }

    public int login(String email, String senha) throws Exception {
        for(Usuario usuario : usuarioList){
            if(usuario.getEmail().equals(email)){
                if(usuario.getSenha().equals(senha))
                    return usuario.getId();
                else {
                    throw new Exception("Login ou senha invalidos");
                }
            }
        }
        throw new Exception("Login ou senha invalidos");
    }

    public void zerarDados(){
        this.usuarioList.clear();
        this.proximoID = 1;

        File arquivo = new File("usuarios.xml");
        if(arquivo.exists()){
            arquivo.delete();
        }
    }
}
