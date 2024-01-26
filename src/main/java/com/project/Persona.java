package com.project;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Persona {


    public Persona() {}


    public Persona (String dni, String nom,String telefon ) {
        this.nom = nom;
        this.dni = dni;
        this.telefon = telefon;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long personaId;

    @Column(name = "dni")
    private String dni;
    @Column(name = "nom")
    private String nom;
    @Column(name = "telefon")
    private String telefon;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "per_llibre",
            joinColumns = {@JoinColumn(referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "id")})
    private Set<Llibre> llibres;

    public Long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Set<Llibre> getLlibres() {
        return llibres;
    }

    public void setLlibres(Set<Llibre> llibres) {
        this.llibres = llibres;
    }

    public List<Object[]> queryLlibres () {
        long id = this.getPersonaId();
        return Manager.queryTable("select distinct l.* from per_llibre pl join Llibre l on pl.llibres_id=l.id  where pl.persones_id="+id+";"  );
    }

    public List<Object[]> getTableColumns(String tableName) {
        return Manager.queryTable("PRAGMA table_info(" + tableName + ");");
    }

    @Override
    public String toString() {

        String str = Manager.tableToString(queryLlibres()).replaceAll("\n", " | ");
        return personaId+": "+nom+", "+telefon+", Llibres: ["+str+"]";
    }

}
