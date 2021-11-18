package br.ufscar.dc.dsw.ExcellentVoyage.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Agencia")
@PrimaryKeyJoinColumn(name="idUsuario")
public class Agencia extends Usuario {

    @NotBlank(message = "{NotBlank.agnecia.cnpj}")
    @Size(min = 14, max = 14, message = "{Size.agencia.cnpj}")
    @Column(name = "cnpj", nullable = false, unique= true, length = 14)
    private String cnpj;

    @NotBlank(message = "{NotBlank.agnecia.descricao}")
    @Size(min = 18, max = 120, message = "{Size.agencia.descricao}")
    @Column(name = "descricao", nullable = false, length = 120)
    private String descricao;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}