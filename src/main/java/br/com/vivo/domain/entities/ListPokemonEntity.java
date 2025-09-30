package br.com.vivo.domain.entities;

import java.util.Objects;

public class ListPokemonEntity {
    private String id;
    private String name;
    private String image;

    public ListPokemonEntity(String id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public ListPokemonEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ListPokemonEntity that = (ListPokemonEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, image);
    }
}
