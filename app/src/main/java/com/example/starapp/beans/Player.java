package com.example.starapp.beans;

import java.util.Date;

public class Player {
    private int id;
    private String nom;
    private Date dateNaissance;
    private String nationalite;
    private int numero;
    private String img;
    private String poste;
    private  static  int comp;
    private double taille;
    private double poids;
    private float star;

    public String getImg() {
        return img;
    }

    public float getStar() {
        return star;
    }

    public void setStar(float star) {
        this.star = star;
    }

    public void setImg(String img) {
        this.img = img;
    }

    private String piedFort;
    private int anneesContrat;
    private double valeurMarchande;

    public Player(String nom, Date dateNaissance, String nationalite, int numero, String poste, double taille, double poids, String piedFort, int anneesContrat, double valeurMarchande,String img,float star) {
        this.id = ++comp;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.nationalite = nationalite;
        this.numero = numero;
        this.poste = poste;
        this.taille = taille;
        this.poids = poids;
        this.piedFort = piedFort;
        this.anneesContrat = anneesContrat;
        this.valeurMarchande = valeurMarchande;
        this.img=img;
        this.star=star;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public double getTaille() {
        return taille;
    }

    public void setTaille(double taille) {
        this.taille = taille;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public String getPiedFort() {
        return piedFort;
    }

    public void setPiedFort(String piedFort) {
        this.piedFort = piedFort;
    }

    public int getAnneesContrat() {
        return anneesContrat;
    }

    public void setAnneesContrat(int anneesContrat) {
        this.anneesContrat = anneesContrat;
    }

    public double getValeurMarchande() {
        return valeurMarchande;
    }

    public void setValeurMarchande(double valeurMarchande) {
        this.valeurMarchande = valeurMarchande;
    }



}
