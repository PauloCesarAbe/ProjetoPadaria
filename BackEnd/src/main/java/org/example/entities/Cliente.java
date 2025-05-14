package org.example.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLI_ID")
    private Long cliId;

    @Column(name = "CLI_NOME")
    private String cliNome;

    @Column(name = "CLI_CPF", length = 11)
    private String cliCpf;

    @Column(name = "CLI_EMAIL")
    private String cliEmail;

    @Column(name = "CLI_TELEFONE", length = 14)
    private String cliTelefone;

    public Cliente() {
    }

    public Cliente(Long cliId, String cliNome, String cliCpf, String cliEmail, String cliTelefone) {
        this.cliId = cliId;
        this.cliNome = cliNome;
        this.cliCpf = cliCpf;
        this.cliEmail = cliEmail;
        this.cliTelefone = cliTelefone;
    }

    public Long getCliId() {
        return cliId;
    }

    public void setCliId(Long cliId) {
        this.cliId = cliId;
    }

    public String getCliNome() {
        return cliNome;
    }

    public void setCliNome(String cliNome) {
        this.cliNome = cliNome;
    }

    public String getCliCpf() {
        return cliCpf;
    }

    public void setCliCpf(String cliCpf) {
        this.cliCpf = cliCpf;
    }

    public String getCliEmail() {
        return cliEmail;
    }

    public void setCliEmail(String cliEmail) {
        this.cliEmail = cliEmail;
    }

    public String getCliTelefone() {
        return cliTelefone;
    }

    public void setCliTelefone(String cliTelefone) {
        this.cliTelefone = cliTelefone;
    }
}
