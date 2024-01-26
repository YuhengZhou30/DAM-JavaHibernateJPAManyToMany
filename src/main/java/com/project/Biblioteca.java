package com.project;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Biblioteca {

    public Biblioteca() {}


    public Biblioteca (String nom, String ciutat ) {
        this.nom = nom;
        this.ciutat = ciutat;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bibliotecaId;

    @Column(name = "nom")
    private String nom;

    @Column(name = "ciutat")
    private String ciutat;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "bib_llibre",
            joinColumns = {@JoinColumn(referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "id")})

    private Set<Llibre> llibres;


    public Long getBibliotecaId() {
        return bibliotecaId;
    }

    public void setBibliotecaId(Long bibliotecaId) {
        this.bibliotecaId = bibliotecaId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCiutat() {
        return ciutat;
    }

    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }

    public Set<Llibre> getLlibres() {
        return llibres;
    }

    public void setLlibres(Set<Llibre> llibres) {
        this.llibres = llibres;
    }

    public List<Object[]> queryLlibres () {
        long id = this.getBibliotecaId();
        return Manager.queryTable("select distinct l.* from bib_llibre bl join Llibre l on bl.llibres_id=l.id  where bl.biblioteques_id="+id+";"  );
    }


    @Override
    public String toString() {


        String str = Manager.tableToString(queryLlibres()).replaceAll("\n", " | ");
        //return str;
        return bibliotecaId+": "+nom+", "+ciutat+", Llibres: ["+str+"]";
    }

}
