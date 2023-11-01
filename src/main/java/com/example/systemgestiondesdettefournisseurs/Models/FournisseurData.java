package com.example.systemgestiondesdettefournisseurs.Models;

public class FournisseurData {
   /* public int fournisseurID;*/
    public String nom;
    public String Adresse;
    public String Telephone;
    public String Categories;
    public String Email;

    public FournisseurData( String nom, String adresse, String telephone, String categories, String email) {
       /* this.fournisseurID = fournisseurID;*/
        this.nom = nom;
        this.Adresse = adresse;
        this.Telephone = telephone;
        this.Categories = categories;
        this.Email = email;
    }

    /*public int getFournisseurID() {
        return fournisseurID;
    }

    public void setFournisseurID(int fournisseurID) {
        this.fournisseurID = fournisseurID;
    }*/

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getCategories() {
        return Categories;
    }

    public void setCategories(String categories) {
        Categories = categories;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }




}