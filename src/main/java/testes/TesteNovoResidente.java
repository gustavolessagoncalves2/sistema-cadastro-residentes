package testes;


import dao.ResidenteDAO;
import model.Residente;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gustavogoncalves
 */
public class TesteNovoResidente {
    public static void main(String[] args) {
        Residente residente = new Residente();
        residente.setNomeResidente("Nome de Residente Teste");
        residente.setCpfResidente("12345678901");
        residente.setRgResidente("987654321");
        residente.setCrmResidente("12345");
        residente.setEmailResidente("teste@exemplo.com");
        residente.setTelefoneResidente("11999999999");

        ResidenteDAO dao = new ResidenteDAO();
        dao.cadastrarResidente(residente);
    }
}
