package com.project;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Llibre",
        uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Llibre {

    public Llibre() {}


    public Llibre (String nom, String editorial ) {
        this.nom = nom;
        this.editorial = editorial;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long llibreId;

    @Column(name = "nom")
    private String nom;

    @Column(name = "editorial")
    private String editorial;

    @ManyToMany
    private Set<Autor> autors;


    @ManyToMany(mappedBy = "llibres")
    private Set<Biblioteca> biblioteques;


    @ManyToMany(mappedBy = "llibres")
    private Set<Persona> persones;











    public Long getLlibreId() {
        return llibreId;
    }

    public void setLlibreId(Long llibreId) {
        this.llibreId = llibreId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public Set<Autor> getAutors() {
        return autors;
    }

    public void setAutors(Set<Autor> Autors) {
        this.autors = Autors;
    }

    public Set<Biblioteca> getBiblioteques() {
        return biblioteques;
    }

    public void setBiblioteques(Set<Biblioteca> biblioteques) {
        this.biblioteques = biblioteques;
    }

    public Set<Persona> getPersones() {
        return persones;
    }

    public void setPersones(Set<Persona> persones) {
        this.persones = persones;
    }


    @Override
    public String toString() {
        return llibreId+": "+nom+", "+editorial;
    }

}
