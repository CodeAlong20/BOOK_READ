package com.bitvilltecnologies.bookword.DataHandler;

public class Model {
   private String title,note,image,description,author;

    public Model(){

    }

   // public Model(String mtitle,String mnote,String mimage){
      //  title=mtitle;
      //  note=mnote;
      //  image=mimage;
  //  }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
