package com.api.drone.model;

import com.api.drone.enums.Tipo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Node {

    private int x;
    private int y;

    private Tipo tipo;
    private boolean explorado;


    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if(!(obj instanceof Node)) return false;

        Node outro = (Node) obj;

        return this.x == outro.x && this.y == outro.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
