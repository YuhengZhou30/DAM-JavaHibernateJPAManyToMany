package com.project;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Autor {

    public Autor() {}


    public Autor (String nom) {
        this.nom = nom;
    }


    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long autorId;


    @Column(name = "nom")
    private String nom;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "aut_llibre",
            joinColumns = {@JoinColumn(referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "id")})
    private Set<Llibre> llibres;


    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Llibre> getLlibres() {
        return llibres;
    }

    public void setLlibres(Set<Llibre> llibres) {
        this.llibres = llibres;
    }

    public List<Object[]> queryLlibres () {
        long id = this.getAutorId();
        return Manager.queryTable("select distinct l.* from aut_llibre al join Llibre l on al.llibres_id=l.id  where al.Autor_id="+id+";"  );
    }

    @Override
    public String toString() {

        String str = Manager.tableToString(queryLlibres()).replaceAll("\n", " | ");
        return autorId+": "+nom+", Llibres: ["+str+"]";

    }

}
