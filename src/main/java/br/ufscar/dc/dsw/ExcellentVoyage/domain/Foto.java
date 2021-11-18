package br.ufscar.dc.dsw.ExcellentVoyage.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "Foto")
public class Foto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idPacote")
    private PacoteTuristico pacoteTuristico;

    @Column(nullable = false)
    private String url;

    public Foto() {
    }

    public Foto(String url) {
        this.url = url;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PacoteTuristico getIdPacoteTuristico() {
        return this.pacoteTuristico;
    }

    public void setPacoteTuristico(PacoteTuristico pacoteTuristico) {
        this.pacoteTuristico = pacoteTuristico;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
